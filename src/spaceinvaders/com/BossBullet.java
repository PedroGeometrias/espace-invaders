package spaceinvaders.com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class BossBullet extends Bullet {
    private int dx;
    private int dy;

    public BossBullet(int x, int y, int dano) {
        super(x, y, dano);
        this.dx = 4;
        this.dy = 4; 
        initBullet(x, y);
    }

    private void initBullet(int x, int y) {
        BufferedImage bulletSprite = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bulletSprite.getGraphics();
        g.setColor(Color.RED);
        g.fillRect(0, 0, 10, 10);
        setImg(bulletSprite);
        setScale(1);
    }

    // bola que quica
    @Override
    public void mover() {
        setAbscissas(getAbscissas() + dx);
        setOrdenadas(getOrdenadas() + dy);

        if (getAbscissas() <= 0 || getAbscissas() >= Data.WIDTH * Data.SCALE - getImg().getWidth()) {
            dx = -dx;
        }
        if (getOrdenadas() <= 0 || getOrdenadas() >= Data.HEIGHT * Data.SCALE - getImg().getHeight()) {
            dy = -dy;
        }
    }
}
