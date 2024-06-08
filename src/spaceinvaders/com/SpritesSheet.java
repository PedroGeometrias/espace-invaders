package spaceinvaders.com;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SpritesSheet extends JPanel {
	// essa classe é muito basica glr, aqui eu criei uma abstração bem basica que gerencia os sprites, criando uma imagem nova com o path( endereço do
	// arquivo msm) 
    private Image image;

    public SpritesSheet(String path) {
        try {
            image = ImageIO.read(new File( path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // esse método é bem simples de entender, eu retorno um objeto, dw cuja o nome eu especifico dps, que tem as propriedades passadas nos parametros !
    public Sprite criarSprite(int abscissas, int ordenadas, int largura, int altura, int scale) {
        return new Sprite(image, abscissas, ordenadas, largura, altura, scale);
    }
    
}

