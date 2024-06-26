package spaceinvaders.com;

import java.awt.image.BufferedImage;

public class Tiro extends Sprite {
    private int dy;
    private int dano; // Adiciona o atributo dano

    public Tiro(int x, int y) {
        initTiro(x, y);
        this.dy = -2; // Movimentação para cima
        this.dano = 10; // Define um valor padrão para o dano
    }

    private void initTiro(int x, int y) {
        BufferedImage tiroSprite = Data.spriteSheet.recortarSprite(0, 112, 16, 16);
        setImg(tiroSprite);
        setScale(1);
        setAbscissas(x);
        setOrdenadas(y);
        setVisible(true);
    }

    public void mover() {
        setOrdenadas(getOrdenadas() + dy);
    }

    // Adiciona o método getDano()
    public int getDano() {
        return dano;
    }

    // Adiciona o método setDano() para permitir ajuste de dano, se necessário
    public void setDano(int dano) {
        this.dano = dano;
    }
}
