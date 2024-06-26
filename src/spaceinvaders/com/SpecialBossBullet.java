package spaceinvaders.com;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class SpecialBossBullet extends Bullet {
    private int dy;
    private int width = 10; // Largura do retângulo
    private int height = 20; // Altura do retângulo

    public SpecialBossBullet(int x, int y, int dano) {
        super(x, y, dano);
        this.dy = 5; // Inicializa dy para mover verticalmente para baixo
        initBullet();
    }

    private void initBullet() {
        // Inicializa a imagem como um retângulo azul
        BufferedImage bulletSprite = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bulletSprite.createGraphics();
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, width, height);
        setImg(bulletSprite);
        setScale(1);
    }

    @Override
    public void mover() {
        int y = getOrdenadas();

        // Move a bala
        setOrdenadas(y + dy);

        // Deleta a bala quando atingir o final da tela
        if (y >= Data.HEIGHT * Data.SCALE) {
            setVisible(false);
        }
    }

    public void desenhar(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(getAbscissas(), getOrdenadas(), width, height); // Define um retângulo azul
    }
}
