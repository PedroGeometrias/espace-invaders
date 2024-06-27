package spaceinvaders.com;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SpriteSheet{
    private BufferedImage img;

    public SpriteSheet(String path) {
        try {
            img = ImageIO.read(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage recortarSprite(int abscissas, int ordenadas, int largura, int altura) {
        return img.getSubimage(abscissas, ordenadas, largura, altura);
    }
}
