package spaceinvaders.com;

import java.awt.image.BufferedImage;

public abstract class Alien extends Sprite {
    private int vida;
    private int dano;

    public Alien(int x, int y) {
        initAlien(x, y);
    }

    public abstract void initAlien(int x, int y);

    // Getters e Setters para vida e dano
    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    // Método para aplicar dano
    public void receberDano(int dano) {
        this.vida -= dano;
        if (this.vida <= 0) {
            setVisible(false);
        }
    }

    // Método para criar uma bala genérica
    public Bullet shoot() {
        int alienX = getAbscissas();
        int alienY = getOrdenadas();
        int spriteWidth = getImg().getWidth() * getScale();
        return new AlienBullet(alienX + (spriteWidth / 2), alienY + getImg().getHeight() * getScale(), this.dano);
    }
}
