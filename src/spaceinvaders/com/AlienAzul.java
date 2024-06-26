package spaceinvaders.com;
import java.awt.image.BufferedImage;

public class AlienAzul extends Alien {

    public AlienAzul(int x, int y) {
        super(x, y);
        setVida(50);
        setDano(20);
    }

    @Override
    public void initAlien(int x, int y) {
        BufferedImage alienSprite = Data.spriteSheet.recortarSprite(0, 48, 16, 16);
        
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
