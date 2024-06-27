package spaceinvaders.com;

import java.awt.image.BufferedImage;

public class AlienRoxo extends Alien {

    public AlienRoxo(int x, int y) {
        super(x, y);
        setVida(2); // Ajuste conforme necessário
        setDano(100); // Ajuste conforme necessário
        initAlien(x, y);
    }

    @Override
    public void initAlien(int x, int y) {
        BufferedImage alienSprite = Data.spriteSheet.recortarSprite(0, 64, 16, 16); 
        setImg(alienSprite);
        setScale(2);
        setAbscissas(x);
        setOrdenadas(y);
        setVisible(true);
    }

}
