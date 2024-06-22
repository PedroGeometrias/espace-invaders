package spaceinvaders.com;

import java.awt.image.BufferedImage;

public abstract class Alien extends Sprite {

	// declaro o alien dando a posicao dele na tela
    public Alien(int x, int y) {
        initAlien(x, y);
    }

    // inicializando sprite o alien, como um recorte da sprite sheet na pos(0, 16)
    public abstract void initAlien(int x, int y);


    
}

