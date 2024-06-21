package spaceinvaders.com;

import java.awt.image.BufferedImage;

public class Alien extends Sprite {

	// declaro o alien dando a posicao dele na tela
    public Alien(int x, int y) {
        initAlien(x, y);
    }

    // inicializando sprite o alien, como um recorte da sprite sheet na pos(0, 16)
    private void initAlien(int x, int y) {
        BufferedImage alienSprite = Data.spriteSheet.recortarSprite(0, 16, 16, 16);
        
        // seto o sprite desse alien como o recorte
        setImg(alienSprite);
        
        // agr ta muito mais facil de dizer o tamanho, é só atualizar uma vez nesse método
        setScale(2); 
        
        // pisicao passada no parametro
        setAbscissas(x);
        setOrdenadas(y);
        
        // sempre lembrar de setar a visibilidade no momento depois da criacao como true, puta dor de cabeça esse ngc
        setVisible(true);
    }
}

