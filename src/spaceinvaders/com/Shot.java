package spaceinvaders.com;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Shot extends Sprite {
    private int posX;
    private int posY;
    private int velocidade;

    public Shot(BufferedImage img, int abscissas, int ordenadas, int largura, int altura, int scale, int posX, int posY, int velocidade) {
        super(img, abscissas, ordenadas, largura, altura, scale);
        this.posX = posX;
        this.posY = posY;
        this.velocidade = velocidade;
    }
}
