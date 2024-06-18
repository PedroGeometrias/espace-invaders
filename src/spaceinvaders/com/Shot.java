package spaceinvaders.com;

import java.awt.Graphics2D;

public class Shot {
    private int posX;
    private int posY;
    private int velocidade;

    public Shot(int posX, int posY, int velocidade) {
        this.posX = posX;
        this.posY = posY;
        this.velocidade = velocidade;
    }

    public void atirar(Graphics2D g2d) {
        int y = posY;
        while (y > 0) {
            y -= velocidade;
            g2d.drawRect(posX + 13, y - 8, 6, 16);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
