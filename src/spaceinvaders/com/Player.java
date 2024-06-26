package spaceinvaders.com;

import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;

public class Player extends Sprite {
    private int dx;
    private SpriteSheet spriteSheet;
    private int velocidadeNave;
    private int velocidadeTiro;
    private int vida;

    public Player() {
        this.spriteSheet = Data.spriteSheet;
        this.velocidadeNave = Data.VELOCIDADE_INICIAL;
        this.setVelocidadeTiro(Data.VELOCIDADE_TIROS_SEG_INICIAL);
        this.vida = 100; 
        iniciarSpriteJogador();
    }

    public void iniciarSpriteJogador() {
        BufferedImage naveSprite = spriteSheet.recortarSprite(0, 0, 16, 16);
        setImg(naveSprite);
        setScale(2); 
        int screenWidth = Data.WIDTH * Data.SCALE;
        int screenHeight = Data.HEIGHT * Data.SCALE;
        int spriteWidth = naveSprite.getWidth();
        int spriteHeight = naveSprite.getHeight();
        int x = (screenWidth - spriteWidth * getScale()) / 2;
        int y = screenHeight - (spriteHeight * getScale()) - 40;
        setAbscissas(x);
        setOrdenadas(y);
        setVisible(true);
    }

    public void move() {
        int newX = getAbscissas() + dx;
        int screenWidth = Data.WIDTH * Data.SCALE;
        int spriteWidth = getImg().getWidth() * getScale();
        if (newX >= 0 && newX + spriteWidth <= screenWidth) {
            setAbscissas(newX);
        } else {
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

    public Tiro shoot() {
        int playerX = getAbscissas();
        int playerY = getOrdenadas();
        int spriteWidth = getImg().getWidth() * getScale();
        return new Tiro(playerX + (spriteWidth / 2), playerY - 20);
    }

    public int getVelocidadeTiro() {
        return velocidadeTiro;
    }

    public void setVelocidadeTiro(int velocidadeTiro) {
        this.velocidadeTiro = velocidadeTiro;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida <= 0) {
            setVisible(false);
            // Lógica para o fim do jogo ou reinício
        }
    }
}
