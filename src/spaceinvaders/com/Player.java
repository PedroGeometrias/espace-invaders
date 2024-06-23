package spaceinvaders.com;

import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;

public class Player extends Sprite {
	private int dx;
	
	private boolean pressionando;

// mesma coisa que o alien
    public Player() {
        iniciarSpriteJogador();
    }

    public void iniciarSpriteJogador() {
        BufferedImage naveSprite = Data.spriteSheet.recortarSprite(0, 0, 16, 16);
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
        int y = screenHeight - (spriteHeight * Data.SPRITE_SCALE) - 40;

        setAbscissas(x);
        setOrdenadas(y);
        setVisible(true);
    }

    public void move() {
        setAbscissas(getAbscissas() + dx);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_L) {
            dx = Data.VELOCIDADE_INICIAL;
        }
        if (key == KeyEvent.VK_H) {
            dx = -Data.VELOCIDADE_INICIAL;
        }
        if(key == KeyEvent.VK_SPACE) {
        	pressionando = true;
        } else {
        	pressionando = false;
        }
    }

    public boolean getPressionando(){
    return this.pressionando;	
    }
public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_L || key == KeyEvent.VK_H) {
            dx = 0;
        }
    }
}

