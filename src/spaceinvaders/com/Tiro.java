package spaceinvaders.com;

import java.awt.image.BufferedImage;

public class Tiro extends Sprite {
	int dy;

	// msm coisa que o alien, preguica de escrever comentario
	public Tiro(int x, int y) {
		initTiro(x, y);
		dy = y;
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
		setOrdenadas(dy--);
	}
	
}
