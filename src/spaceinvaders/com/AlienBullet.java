package spaceinvaders.com;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class AlienBullet extends Bullet {
    private int dy;

    public AlienBullet(int x, int y, int dano) {
        super(x, y, dano);
        this.dy = 4; 
        initBullet(x, y);
    }

    private void initBullet(int x, int y) {
    	
        BufferedImage bulletSprite = new BufferedImage(5, 10, BufferedImage.TYPE_INT_ARGB);
        
        Graphics g = bulletSprite.getGraphics();
        
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, 5, 10);
        
        setImg(bulletSprite);
        
        setScale(1);
    }

    @Override
    public void mover() {
        setOrdenadas(getOrdenadas() + dy);
    }
}
