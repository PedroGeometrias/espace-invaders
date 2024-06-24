// Board.java
package spaceinvaders.com;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Board extends JPanel {

    // Setando alguns objetos
    private Dimension size;
    private List<Alien> aliens;
    private Player player;
    private List<Tiro> tiros;
    private RoundLoop gameLoop;
    
    // Setando o double buffer, isso serve pra diminuir o flickering quando a gente for adicionar o game loop
    private Image bufferImage;
    // Contexto gráfico que será adicionado ao buffer que está fora da tela
    private Graphics bufferGraphics;
    // Timer para os tiros, baseado na velocidade de tiro
    private Timer shootingTimer;

    // Adicionando logger
    private static final Logger logger = Logger.getLogger(Board.class.getName());

    // Inicializando a tela do jogo
    public Board() {
        boardInit();
    }

    // Sobrescrevendo o addNotify da toolkit do Java, e adicionando nele a chamada do método gameInit
    @Override
    public void addNotify() {
        super.addNotify();
        gameInit();
        gameLoop = new RoundLoop(this);
    }

    // Atributos básicos da tela do jogo
    public void boardInit() {
        // Configurando como focável pra essa tela capturar eventos do teclado
        setFocusable(true);
        size = new Dimension(Data.WIDTH * Data.SCALE, Data.HEIGHT * Data.SCALE);
        
        // Cor da tela é preta
        setBackground(Color.black);
    }

    // Lógica inicial do jogo
    private void gameInit() {
        // Array list que receberá cada alien novo
        aliens = new ArrayList<>();
        
        // Array list dos tiros
        tiros = new ArrayList<>();
        
        // Crio o player e digo que ele pode receber eventos do teclado
        player = new Player();
        addKeyListener(new Controles(player, this));
        
        // Setando o double buffering
        bufferImage = createImage(size.width, size.height);
        bufferGraphics = bufferImage.getGraphics();

        // O delay do timer é em milissegundos. 1 seg = 1000 milissegundos, então eu divido 1000 pela velocidade de tiro
        int delay = 1000 / player.getVelocidadeTiro();
        shootingTimer = new Timer(delay, new ActionListener() {
            @Override
            // Ação performada a cada delay é de criar um novo tiro e adicionar eles à array list, como foi feito com os aliens
            public void actionPerformed(ActionEvent e) {
                synchronized (tiros) {
                    Tiro newTiro = player.shoot();
                    tiros.add(newTiro);
                }
            }
        });
        // Iniciando o timer
        shootingTimer.start();
    }

    // Isso é feito pra desenhar a array list lá do RoundLoop
    public void setAliens(List<Alien> aliens) {
        this.aliens = aliens;
    }

    // Pintando com o double buffer
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Desenho no buffer fora da tela
        synchronized (tiros) {
            doDrawing(bufferGraphics);
        }
        
        // Trago o buffer fora da tela para a tela
        g.drawImage(bufferImage, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
        
        // Tenho de checar se todos os aliens estão vivos, e redesenhar caso não estejam
        gameLoop.checkRoundCompletion();
    }

    // Aqui eu desenho no buffer
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, size.width, size.height);
        drawAliens(g2d);
        drawPlayer(g2d);
        drawShots(g2d);
    }

    // Crio o desenho dos aliens
    private void drawAliens(Graphics2D g) {
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                BufferedImage img = alien.getScaledImg();
                int x = alien.getAbscissas();
                int y = alien.getOrdenadas();
                g.drawImage(img, x, y, this);
            }
            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    // Crio o desenho do player
    private void drawPlayer(Graphics2D g) {
        if (player.isVisible()) {
            BufferedImage img = player.getScaledImg();
            int x = player.getAbscissas();
            int y = player.getOrdenadas();
            g.drawImage(img, x, y, this);
        }

        if (player.isDying()) {
            player.die();
        }
    }

    // Crio o desenho das balas
    private void drawShots(Graphics2D g) {
        synchronized (tiros) {
            for (Tiro tiro : tiros) {
                if (tiro.isVisible()) {
                    BufferedImage img = tiro.getScaledImg();
                    int x = tiro.getAbscissas();
                    int y = tiro.getOrdenadas();
                    g.drawImage(img, x, y, this);
                }
            }
        }
    }

    // Para checar a colisão é meio complexo
    // Eu tava tendo um erro com a array list de tiros, porque várias threads estavam tentando modificar ela ao
    // mesmo tempo, então eu usei essa keyword chamada synchronized, que significa que só uma thread pode acessar e modificar essa
    // array list por vez
    private void checkCollisions() {
        // Array list que carrega as balas que vão ser removidas
        List<Tiro> balasDeletadas = new ArrayList<>();

        synchronized (tiros) {
            try {
                for (Tiro tiro : tiros) {
                    boolean encostou = false;

                    for (Alien alien : aliens) {
                        // Caso detectar colisão, quebra o loop
                        if (tiro.colidir(alien) && alien.isVisible()) {
                            alien.setVisible(false);
                            tiro.setVisible(false);
                            encostou = true;
                            break;
                        }
                    }

                    // Adiciono bala que encostou no alien na array de balas que vão ser deletadas 
                    if (encostou || !tiro.isVisible()) {
                        balasDeletadas.add(tiro);
                    }
                }

                // Removo todas as balas que encostaram em aliens 
                tiros.removeAll(balasDeletadas);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error during collision check", e);
            }
        }
    }

    // Lógica do jogo inteira é declarada aqui
    public void updateGame() {
    	// Movimentação do player
    	player.move();
        // Lista de balas que vão ser deletadas, caso elas saiam da tela
        List<Tiro> balasDeletadas = new ArrayList<>();

        synchronized (tiros) {
            try {
                for (Tiro tiro : tiros) {
                    if (tiro.isVisible()) {
                        tiro.mover();
                        // Todas as balas que saem da tela, devem ser adicionadas na lista de balas que serão deletadas 
                        if (tiro.getOrdenadas() < 0 || tiro.getOrdenadas() > Data.HEIGHT * Data.SCALE) {
                            tiro.setVisible(false);
                            balasDeletadas.add(tiro);
                        }
                    } else {
                        // Se a bala não estiver visível, ela tem de ser deletada
                        balasDeletadas.add(tiro);
                    }
                }

                // Removo todas as balas da lista de balas que tem de ser deletadas 
                tiros.removeAll(balasDeletadas);

                checkCollisions();
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error during game update", e);
            }
        }
    }
}
