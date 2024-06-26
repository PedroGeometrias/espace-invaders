package spaceinvaders.com;

import java.awt.image.BufferedImage;

public class AlienBoss extends Alien {
    private int speed;

    public AlienBoss(int x, int y) {
        super(x, y);
        setVida(500); 
        setDano(20); 
        this.speed = 2;
    }

    @Override
    public void initAlien(int x, int y) {
        BufferedImage alienSprite = Data.spriteSheet.recortarSprite(15, 32, 80, 80); 
        setImg(alienSprite);
        setScale(2);
        setAbscissas(x);
        setOrdenadas(y);
        setVisible(true);
    }

    // Método para criar balas do Boss que quicam
    public BossBullet shoot() {
        int bossX = getAbscissas();
        int bossY = getOrdenadas();
        int spriteWidth = getImg().getWidth() * getScale();
        return new BossBullet(bossX + (spriteWidth / 2), bossY + getImg().getHeight() * getScale(), getDano());
    }

    // Método para criar tiros especiais
    public SpecialBossBullet shootSpecial() {
        int bossX = getAbscissas();
        int bossY = getOrdenadas();
        int spriteWidth = getImg().getWidth() * getScale();
        return new SpecialBossBullet(bossX + (spriteWidth / 2), bossY + getImg().getHeight() * getScale(), getDano());
    }

    // Método para spawnar AlienRoxo
    public AlienRoxo spawnAlienRoxo(int x, int y) {
        return new AlienRoxo(x, y);
    }

    // Método para movimentar o Boss
    public void move() {
        setAbscissas(getAbscissas() + speed);

        // Verificar se o Boss atingiu as bordas e inverter a direção
        if (getAbscissas() <= 0 || getAbscissas() >= Data.WIDTH * Data.SCALE - getImg().getWidth() * getScale()) {
        	// inverto a velocidade
            speed = -speed;
        }
    }
}
