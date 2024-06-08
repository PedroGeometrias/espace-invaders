package spaceinvaders.com;

public class Enemy extends Sprite {
    public Enemy(Sprite sprite) {
        super(sprite.getImg(), sprite.getAbscissas(), sprite.getOrdenadas(), sprite.getLargura(), sprite.getAltura(), sprite.getScale());
    }
}
