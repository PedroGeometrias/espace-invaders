package spaceinvaders.com;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Board extends JPanel {

    // setando alguns objetos
    private Dimension size;
    private List<Alien> aliens;
    private Player player;
    private Tiro tiro;
    private RoundLoop gameLoop;  // Adicionado campo gameLoop

    // setando o double buffer, isso server pra diminuir o flockering quando a gnt for adicionar o game loop
    private Image bufferImage;
    
    // contexto grafico que sera adicionado ao buffer que esta fora da tela
    private Graphics bufferGraphics;

    // inicializando a tela do jogo
    public Board() {
        boardInit();
    }

    // subscrevendo o addNotify da tool kit do java, e adicionando nele a chamada do método gameinit
    @Override
    public void addNotify() {
        super.addNotify();
        gameInit();
        gameLoop = new RoundLoop(this);  // Inicializando o game loop após o jogo ser inicializado
    }

    // atributos basicos da tela do jogo
    public void boardInit() {
        // seto o como focavel pra essa telar capturar eventos do teclado
        setFocusable(true);
        size = new Dimension(Data.WIDTH * Data.SCALE, Data.HEIGHT * Data.SCALE);
        
        // cor da tela é preta
        setBackground(Color.black);
    }

    // lógica inicial do jogo, como posicionamento dos aliens e instanciamento de objetos
    private void gameInit() {
        // array list que receberá cada alien novo
        aliens = new ArrayList<>();

        // crio o novo player
        player = new Player();

        // definindo o posi da bala com relacao ao player
        int playerX = player.getAbscissas();
        int playerY = player.getOrdenadas();
        int spriteWidth = player.getImg().getWidth();
        tiro = new Tiro(playerX + (spriteWidth / 2), playerY - 20);

        // crio um buffer fora da tela com o tamanho da tela
        bufferImage = createImage(size.width, size.height);
        
        // adiciono contexto grafico ao buffer
        bufferGraphics = bufferImage.getGraphics();
    }

    // Método para definir os aliens do round atual
    public void setAliens(List<Alien> aliens) {
        this.aliens = aliens;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // desenhando no buffer fora da tela, ao inves de desenhar no contexto grafico fornecido pelo java
        doDrawing(bufferGraphics);
        // desenho na tela
        g.drawImage(bufferImage, 0, 0, this);
        Toolkit.getDefaultToolkit().sync();
        
        gameLoop.checkRoundCompletion();  // Verificando a conclusão do round
    }

    private void doDrawing(Graphics g) {
        // casto g para para graficos 2d, e coloco o resultado no g2d
        Graphics2D g2d = (Graphics2D) g;

        // reseto o buffer pintando de preto
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, size.width, size.height);

        // desenho na tela 
        drawAliens(g2d);
        drawPlayer(g2d);
        drawShot(g2d);
    }

    private void drawAliens(Graphics2D g) {
        // atravesso a array de aliens, pintando cada um deles
        for (Alien alien : aliens) {
            // se o alien for visivel eu pinto
            if (alien.isVisible()) {
                BufferedImage img = alien.getScaledImg();
                int x = alien.getAbscissas();
                int y = alien.getOrdenadas();
                g.drawImage(img, x, y, this);
            }
            // se ele estiver morto eu deixo ele morto
            if (alien.isDying()) {
                alien.die();
            }
        }
    }

    // mesma coisa q o alien porem eu não preciso atravessar nenhuma array
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

    // mesma coisa que o player, mas a bala n pode estar morta
    private void drawShot(Graphics2D g) {
        if (tiro.isVisible()) {
            BufferedImage img = tiro.getScaledImg();
            int x = tiro.getAbscissas();
            int y = tiro.getOrdenadas();
            g.drawImage(img, x, y, this);
        }
    }
}
