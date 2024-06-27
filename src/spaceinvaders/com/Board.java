package spaceinvaders.com;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Board extends JPanel {

	// seto objetos:
	// dimensao desse componente
    private Dimension size;
    
    // lista de aliens dos rounds
    private List<Alien> aliens;

    // lista temporaria de aliens que o boss spawna, usada no loop do boss
    private List<Alien> aliensToAdd;

    // classe do player
    private Player player;
    
    // classe que é o ponto central de todas as balas, onde a gnt organiza elas 
    private BulletManager bulletManager;
    
    // a logica de round do nosso jg
    private RoundLoop gameLoop;
    
    // gero uma posicao de spawn randomico dos aliens no round do boss
    private Random random;
    
    // tempo de tiro do player, baseado na variavel la do data
    private Timer shootingTimer;
    
    // tempo de tiro do alien
    private Timer alienShootingTimer;
    
    // um timer para gerar os aliens spawnados pelo boss
    private Timer bossSpawningTimer;
    
    // objeto statico dessa classe
    private static Board board;

    // setando o double buffer
    private Image bufferImage;
    private Graphics bufferGraphics;

    public Board() {
        boardInit();
    }

    // esse metodo é chamado automaticamente quando o componente (Board) e adicionado la no JFrame setado no space invaders
    // tem um monte de gnt na internete falando que n é bomo usar esse metodo mas eu n sei como fazer de outro jeito
   @Override
    public void addNotify() {
    	System.out.printf("board foi adicionado corretamente\n");
        super.addNotify();
        // quando esse metodo comear, eu quero que a logica do jgo seja a primeira coisa a rodar, ent eu seto o jogo
        // e comeco os rpunds pra spawnar os aliens
        gameInit();
        gameLoop = new RoundLoop(this);
    }

    // crio variaveis basicas da janela desse j0ogo
    public void boardInit() {
    	// preciso setar como o foco pra poder escutar por eventos do tevclado
        setFocusable(true);
        size = new Dimension(Data.WIDTH * Data.SCALE, Data.HEIGHT * Data.SCALE);
        setBackground(Color.black);
    }

    // game init é o ponto inicial da logica do jogo
    private void gameInit() {
    	// CopyOnWriteArrayList é uma lista que é thread safe, n preciso sincronizar, eu posso iterar por essa lista
    	// eqnt modificacoes acontecem tbm, muito util pq tava bugando muito td mundo ao msm tempo mexendo nas listas das 
    	// balas
        aliens = new CopyOnWriteArrayList<>();
        aliensToAdd = new CopyOnWriteArrayList<>();
        
        // bulletManager é mais pra comer classe, e pq ia ficar muito feio setar todas as listas nesse metodo
        bulletManager = new BulletManager();
       
        player = new Player();
        
        // vcs ja sabem como o random funciona
        random = new Random();
        
        // conteiner e player escutam por eventos agr
        addKeyListener(new Controles(player, this));
        
        // double buffer é bem simples, createImage cria um buffer de imagem fora da tela
        bufferImage = createImage(size.width, size.height);
        
        // dai eu pego esse buffer fora da tela e adiciono um contexto grafico, ou seja, possibilito o de drawComponent nele
        bufferGraphics = bufferImage.getGraphics();

        // timer funciona em mili segundo, divdo pela velocidade de tiro, e tenho quantos tiros por segundo eu du
        int playerShootingDelay = 1000 / player.getVelocidadeTiro();
        
        // pego o delay, e a cada tick do delay executo uma acao que no caso é
        // e -> { criar e setar tiro }
        shootingTimer = new Timer(playerShootingDelay, e -> {
        	// sincronizado pra n acessar a lista enquanto outras coisas querem acessar a lista ao mesmo tempo so que 
        	// em threads diferentes
            synchronized (bulletManager.getPlayerBullets()) {
            	// crio um novo tiro usando o metodo do player que retorna um objeto
                Tiro newTiro = player.shoot();
                
                // adiciono ele na lista 
                bulletManager.addPlayerBullet(newTiro);
            }
        });
        // necessario pra startar o timer
        shootingTimer.start();

        // mesma coisa pro alien
        int alienShootingDelay = 1000; 
        alienShootingTimer = new Timer(alienShootingDelay, e -> {
            synchronized (bulletManager.getAlienBullets()) {
                for (Alien alien : aliens) {
                	// diferenca é que o alien tem que estar vivo, e ele tem 30% de chance de atirar
                	// random.nextInt funciona retornando umn valor pseudo randomico dentro de um limite
                	// passado no paramentro, menor que outro
                    if (alien.isVisible() && random.nextInt(100) < 40) {
                    	// msm coisa que o player, adiciono todas as balas dos inimigos que sao normais aqui
                        Bullet newBullet = alien.shoot();
                        bulletManager.addAlienBullet(newBullet);
                    }
                }
            }
        });
        alienShootingTimer.start();

        // o boss tem 3 ataques diferente
        // atq 1 : spawna um inimigo com as caracteristicas do alien roxo
        // atq 2 : atira uma bala que da hit kill
        // atq 3 : uma bala que fica quicando, ela é o tiro normal do boss 
        int bossAttakingDelay = 1000;
        bossSpawningTimer = new Timer(bossAttakingDelay, e -> {
        	// logica de apawn dos aliens
            synchronized (aliens) {
                for (Alien alien : aliens) {
                	// se o alien for um boss e ele estiver vivo
                    if (alien instanceof AlienBoss && alien.isVisible()) {
                    	// eu crio um objeto alien boss do tipo alien, pra poder acessar os metodos
                    	// do boss para todo alien gerado
                        AlienBoss boss = (AlienBoss) alien;
                        // gero um numero pseudo randomico entre 0 e 3, e somo mais um para n ter chance desse valor 
                        // sser zero
                        int numberOfSpawns = random.nextInt(3) + 1; 
                        // e gero conto conto ate o valor gerado, cada contada tem a chance de 20% de spawnar um alien
                        // nv
                        for (int i = 0; i < numberOfSpawns; i++) {
                        	// chance de 20%
                            if (random.nextInt(100) < 20) {
                            	// crio alien com o metodo do boss, passando um x e y aleatorios, so que n
                            	// posso gerar um alien na metade da tela onde o player esta, por isso divido por 2
                                AlienRoxo newAlien = boss.spawnAlienRoxo(random.nextInt(Data.WIDTH * Data.SCALE),
                                        random.nextInt(Data.HEIGHT * Data.SCALE / 2));
                                // adiciono esses aliens na lista temporararia
                                aliensToAdd.add(newAlien);
                            }
                        }
                        // chance de 40% de gerar uma bala que da hit kill
                        if (random.nextInt(100) < 40) { 
                            SpecialBossBullet specialBullet = boss.shootSpecial();
                            bulletManager.addAlienBullet(specialBullet);
                        }
                    }
                }
            }
            
            synchronized (aliens) {
            	// adiciono os aliens da temporararia ( que o boss gerou) na geral
                aliens.addAll(aliensToAdd);
                // limpo a temporaria
                aliensToAdd.clear();
            }
        });
        bossSpawningTimer.start();
    }

    // igualo a lista de aliens do RoundLopp
    public void setAliens(List<Alien> aliens) {
        this.aliens = aliens;
    }

    // paintComponent é chamado usando o metodo repaint(), esse metodo recebe um contexto grafico
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        synchronized (bulletManager.getPlayerBullets()) {
        	// desenho no buffer que esta fora da tela
            doDrawing(bufferGraphics);
        }
        // desenho o buffer fora da tela na tela
        g.drawImage(bufferImage, 0, 0, this);
        
        // sincronizo o desenho de buffers e o desenho do buffer na tela
        Toolkit.getDefaultToolkit().sync();
        
        // caso todos os aliens estejam mortos, eu limpo os tiros da tela, e vou pro proximo round
        gameLoop.checkRoundCompletion();
    }

    // serve pra desenhar no buffer fora da tela
    private void doDrawing(Graphics g) {
    	// casto o contexto grafico geral em um contecto grafico 2d, e boto o resutado na var g2d
        Graphics2D g2d = (Graphics2D) g;
        // pinto o componente de preto
        g2d.setColor(Color.black);
        // preencho a tela com a cor preta
        g2d.fillRect(0, 0, size.width, size.height);
        
        // faço os desenhos
        drawAliens(g2d);
        drawPlayer(g2d);
        drawShots(g2d);
        drawAlienBullets(g2d);
        drawBossBullets(g2d);
        drawSpecialBossBullets(g2d);
    }

    // logica de desenhar os aliens
    private void drawAliens(Graphics2D g) {
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                BufferedImage img = alien.getScaledImg();
                int x = alien.getAbscissas();
                int y = alien.getOrdenadas();
                // se os aliens estiverem vivos, eu densenho eles nesse componente na posicao correta
                g.drawImage(img, x, y, this);
            }
            // se o alien estiver morto, ele fica invisivel e sem colissao
            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    // logica do player, mesma coisa que o alien
    private void drawPlayer(Graphics2D g) {
        if (player.isVisible()) {
            BufferedImage img = player.getScaledImg();
            int x = player.getAbscissas();
            int y = player.getOrdenadas();
            g.drawImage(img, x, y, this);
        } else {
        	// a diferenca é que eu renderizo uma mensagem de morte
            gameOver(g);
        }

        if (player.isDying()) {
            player.die();
        }
    }

    // desenho os tiros
    private void drawShots(Graphics2D g) {
        synchronized (bulletManager.getPlayerBullets()) {
            for (Tiro tiro : bulletManager.getPlayerBullets()) {
                if (tiro.isVisible()) {
                    BufferedImage img = tiro.getScaledImg();
                    int x = tiro.getAbscissas();
                    int y = tiro.getOrdenadas();
                    g.drawImage(img, x, y, this);
                }
            }
        }
    }

    // tiros dos aliesn
    private void drawAlienBullets(Graphics2D g) {
        synchronized (bulletManager.getAlienBullets()) {
            for (Bullet bullet : bulletManager.getAlienBullets()) {
                if (bullet.isVisible()) {
                    BufferedImage img = bullet.getScaledImg();
                    int x = bullet.getAbscissas();
                    int y = bullet.getOrdenadas();
                    g.drawImage(img, x, y, this);
                }
            }
        }
    }

    // tiros do boss
    private void drawBossBullets(Graphics2D g) {
        synchronized (bulletManager.getBossBullets()) {
            for (BossBullet bullet : bulletManager.getBossBullets()) {
                if (bullet.isVisible()) {
                    BufferedImage img = bullet.getScaledImg();
                    int x = bullet.getAbscissas();
                    int y = bullet.getOrdenadas();
                    g.drawImage(img, x, y, this);
                }
            }
        }
    }

    // tiro do boss que da hit kill
    private void drawSpecialBossBullets(Graphics2D g) {
        synchronized (bulletManager.getSpecialBossBullets()) {
            for (SpecialBossBullet bullet : bulletManager.getSpecialBossBullets()) {
                if (bullet.isVisible()) {
                    bullet.desenhar(g);
                }
            }
        }
    }

    // update na logica do jogo
    public void updateGame() {
    	// movimentacao do player
        player.move();
        
        // movimentacao das balas
        updatePlayerBullets();
        updateAlienBullets();
        updateBossBullets();
        updateSpecialBossBullets();
        
        // checo a colisao
        CollisionManager.checkCollisions(player, aliens, bulletManager);
        
        // movimentaca do boss
        updateAliens();

        // desenho no buffer a msg, e acabo com a logica do jogo
        if (!player.isVisible()) {
            gameOver(bufferGraphics);
        }
    }

    // atualizo a posicao das balas do player
    private void updatePlayerBullets() {
        synchronized (bulletManager.getPlayerBullets()) {
        	// iterator é usado para navegar listas
            Iterator<Tiro> iterator = bulletManager.getPlayerBullets().iterator();
            while (iterator.hasNext()) {
                Tiro tiro = iterator.next();
                if (tiro.isVisible()) {
                	// mover soma o y+= 5 se n me engano
                    tiro.mover();
                    // se o tiro do player sair da tela, eu removo ele
                    if (tiro.getOrdenadas() < 0 || tiro.getOrdenadas() > Data.HEIGHT * Data.SCALE) {
                        tiro.setVisible(false);
                        iterator.remove();
                    }
                } else {
                    iterator.remove();
                }
            }
        }
    }

    // atualizo as balas dos aliens 
    private void updateAlienBullets() {
    	
        synchronized (bulletManager.getAlienBullets()) {
            Iterator<Bullet> iterator = bulletManager.getAlienBullets().iterator();
            while (iterator.hasNext()) {
                Bullet bullet = iterator.next();
                if (bullet.isVisible()) {
                    bullet.mover();
                } else {
                    iterator.remove();
                }
            }
        }
    }

    // atualizo as balas do boss
    private void updateBossBullets() {
        synchronized (bulletManager.getBossBullets()) {
            Iterator<BossBullet> iterator = bulletManager.getBossBullets().iterator();
            while (iterator.hasNext()) {
                BossBullet bullet = iterator.next();
                if (bullet.isVisible()) {
                    bullet.mover();
                } else {
                    iterator.remove();
                }
            }
        }
    }

    // atualizo a bala especial do boss
    private void updateSpecialBossBullets() {
        synchronized (bulletManager.getSpecialBossBullets()) {
            Iterator<SpecialBossBullet> iterator = bulletManager.getSpecialBossBullets().iterator();
            while (iterator.hasNext()) {
                SpecialBossBullet bullet = iterator.next();
                if (bullet.isVisible()) {
                    bullet.mover();
                    if (bullet.getOrdenadas() > Data.HEIGHT * Data.SCALE) {
                        bullet.setVisible(false);
                        iterator.remove();
                    }
                } else {
                    iterator.remove();
                }
            }
        }
    }

    // atualizo a movimetaca do boss
    private void updateAliens() {
        for (Alien alien : aliens) {
            if (alien instanceof AlienBoss) {
                ((AlienBoss) alien).move();
            }
        }
    }

    // renderizo a msg de game over
    private void gameOver(Graphics g) {
        String message = "Game Over";
        g.setColor(Color.RED);
        g.drawString(message, (Data.WIDTH * Data.SCALE) / 2, (Data.HEIGHT * Data.SCALE) / 2);

        shootingTimer.stop();
        alienShootingTimer.stop();
        bossSpawningTimer.stop();
    }

    public void clearBulletsOnRoundCompletion() {
        bulletManager.clearBullets();
    }
}
