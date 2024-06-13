package spaceinvaders.com;

import java.awt.Graphics;

import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Drawing extends JPanel {

    private static final long serialVersionUID = 1L;

    // declaro como list agr pq dai eu posso modificar dps, deixa o codigo mais flexivel, não mudar
    private List<Sprite> sprites;

    // digo que sprites é uma arraylist logo na criação do objeto
    public Drawing() {
        sprites = new ArrayList<>();
    }

    // adiciono um novo sprite 
    public void addSprite(Sprite sprite) {
        sprites.add(sprite);
    }

    
    // bem padrao pra varios jgs, esse é o metodo que realmente desenha na tela
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (Sprite sprite : sprites) {
            Image image = sprite.getImg();
            if (image != null) {
                g2d.drawImage(image,
                		sprite.getAbscissas(), 
                        sprite.getOrdenadas(), 
                        sprite.getAbscissas() + sprite.getScaledWidth(),
                        sprite.getOrdenadas() + sprite.getScaledHeight(),
                        0, 0, sprite.getLargura(), sprite.getAltura(),
                    this);
            }
        }
    }
}

