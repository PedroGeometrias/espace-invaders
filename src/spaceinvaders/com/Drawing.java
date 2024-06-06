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

    	// chamando o paintComponent da super classe
        super.paintComponent(g);

        // convertendo g para o contexto de Graficos 2d, bem simples 
        Graphics2D g2d = (Graphics2D) g;
        
        // um fir each que navega pela minha lista desenhando as imagens especificadas pelos paramentros
        for (Sprite sprite : sprites) {
            Image image = sprite.getImg();
            if (image != null) {
                g2d.drawImage(image,
                    0, 0, sprite.getScaledWidth(), sprite.getScaledHeight(),
                    sprite.getAbscissas(), sprite.getOrdenadas(), sprite.getAbscissas() + sprite.getLargura(), sprite.getOrdenadas() + sprite.getAltura(),
                    this);
            }
        }
        // redesenha
        repaint();
    }
}
