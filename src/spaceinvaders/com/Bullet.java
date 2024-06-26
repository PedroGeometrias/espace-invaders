package spaceinvaders.com;

import java.awt.image.BufferedImage;

public abstract class Bullet extends Sprite {
    private int dano;

    public Bullet(int x, int y, int dano) {
        this.dano = dano;
        setAbscissas(x);
        setOrdenadas(y);
        setVisible(true);
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public abstract void mover();
}
