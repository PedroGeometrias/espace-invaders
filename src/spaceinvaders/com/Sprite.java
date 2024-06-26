package spaceinvaders.com;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Sprite {
    private BufferedImage img;
    private int ordenadas, abscissas, scale;
    private boolean visible, isDying;

    public Sprite() {
    }

    public void die() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getOrdenadas() {
        return ordenadas;
    }

    public int getAbscissas() {
        return abscissas;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setOrdenadas(int ordenadas) {
        this.ordenadas = ordenadas;
    }

    public void setAbscissas(int abscissas) {
        this.abscissas = abscissas;
    }

    public boolean isDying() {
        return isDying;
    }

    public void setDying(boolean dying) {
        this.isDying = dying;
    }

    public BufferedImage getScaledImg() {
        int width = img.getWidth() * scale;
        int height = img.getHeight() * scale;
        BufferedImage scaledImg = new BufferedImage(width, height, img.getType());
        Graphics2D g2d = scaledImg.createGraphics();
        g2d.drawImage(img, 0, 0, width, height, null);
        g2d.dispose();
        return scaledImg;
    }

    public boolean colidir(Sprite sprite) {
        int ladoEsquerdo = this.abscissas;
        int ladoDireito = this.abscissas + this.img.getWidth() * this.scale;
        int topo = this.ordenadas;
        int parteMaisEmbaixo = this.ordenadas + this.img.getHeight() * this.scale;

        int esquerdoSprite = sprite.abscissas;
        int direitaSprite = sprite.abscissas + sprite.img.getWidth() * sprite.scale;
        int topoSprite = sprite.ordenadas;
        int parteMaisEmbaixoSprite = sprite.ordenadas + sprite.img.getHeight() * sprite.scale;

        return ladoDireito > esquerdoSprite && ladoEsquerdo < direitaSprite && parteMaisEmbaixo > topoSprite && topo < parteMaisEmbaixoSprite;
    }

    public boolean colidir(int x, int y, int width, int height) {
        int ladoEsquerdo = this.abscissas;
        int ladoDireito = this.abscissas + this.img.getWidth() * this.scale;
        int topo = this.ordenadas;
        int parteMaisEmbaixo = this.ordenadas + this.img.getHeight() * this.scale;

        int esquerdoSprite = x;
        int direitaSprite = x + width;
        int topoSprite = y;
        int parteMaisEmbaixoSprite = y + height;

        return ladoDireito > esquerdoSprite && ladoEsquerdo < direitaSprite && parteMaisEmbaixo > topoSprite && topo < parteMaisEmbaixoSprite;
    }
}
