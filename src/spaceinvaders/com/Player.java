package spaceinvaders.com;

import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;

public class Player extends Sprite {
    private int dx;
    private SpriteSheet spriteSheet;
    private int velocidadeNave;
    private int velocidadeTiro;
    private boolean pressionando;

    // mesma coisa que o alien
    public Player() {
        this.spriteSheet = Data.spriteSheet;
        this.velocidadeNave = Data.VELOCIDADE_INICIAL;
        this.setVelocidadeTiro(Data.VELOCIDADE_TIROS_SEG_INICIAL);
        iniciarSpriteJogador();
    }

    public void iniciarSpriteJogador() {
        BufferedImage naveSprite = spriteSheet.recortarSprite(0, 0, 16, 16);
        setImg(naveSprite);
        setScale(2); 
        // unica diferencia é que a posição da nave tem de ser na parte de baixo e no meio
        // aqui eu seto isso
        int screenWidth = Data.WIDTH * Data.SCALE;
        int screenHeight = Data.HEIGHT * Data.SCALE;
        int spriteWidth = naveSprite.getWidth();
        int spriteHeight = naveSprite.getHeight();
        // metade do x
        int x = (screenWidth - spriteWidth) / 2;
        // parte mais em baixo
        int y = screenHeight - (spriteHeight * getScale()) - 40;

        setAbscissas(x);
        setOrdenadas(y);
        setVisible(true);
    }

    // Atualizo meu movimento com base na velocidade e checo os limites da tela
    public void move() {
    	// velocidade de mov
        int newX = getAbscissas() + dx;
        
        // limites
        int screenWidth = Data.WIDTH * Data.SCALE;
        int spriteWidth = getImg().getWidth() * getScale();

        // Verifica os limites da tela
        if (newX >= 0 && newX + spriteWidth <= screenWidth) {
            setAbscissas(newX);
        } else {
            // Se atingir o limite, para o movimento
            dx = 0;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_L) {
            dx = velocidadeNave; 
        }
        if (key == KeyEvent.VK_H) {
            dx = -velocidadeNave;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_L || key == KeyEvent.VK_H) {
            dx = 0;
        }
    }

    // eu crio um tiro novo la no timer
    public Tiro shoot() {
        int playerX = getAbscissas();
        int playerY = getOrdenadas();
        int spriteWidth = getImg().getWidth();
        return new Tiro(playerX + (spriteWidth / 2), playerY - 20);
    }

    public int getVelocidadeTiro() {
        return velocidadeTiro;
    }

    public void setVelocidadeTiro(int velocidadeTiro) {
        this.velocidadeTiro = velocidadeTiro;
    }
}
