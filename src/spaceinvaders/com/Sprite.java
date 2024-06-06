package spaceinvaders.com;

import java.awt.Image;

public class Sprite {
	// essa classe representa um sprite, e sua propriedaades
	// esse cara representa as imagens
	private Image img;
    // tenta imaginar o spritesheet como um plano cartesiano, a gnt vai escolher as cordenadas começando do canto superior esquerdo,
	// e dps com a largura e altura escolher o quanto a gnt que cortar,  o scale define o tamanho que a imagem vai aparecer na tela
	private int ordenadas, abscissas, largura, altura, scale; 

	public Sprite(Image img, int abscissas, int ordenadas, int largura, int altura, int scale) { 
		this.abscissas = abscissas;
		this.ordenadas = ordenadas;
		this.largura = largura;
		this.altura = altura;
		this.scale = scale;
		this.img = img;
	}
	
	// setando getters, não botei os setters pq essa classe só vai setar os dados uma vez, quando for instanciada
	public Image getImg() {
		return img;
	}


	public int getOrdenadas() {
		return ordenadas;
	}

	public int getAbscissas() {
		return abscissas;
	}

	public int getLargura() {
		return largura;
	}

	public int getAltura() {
		return altura;
	}

	public int getScale() {
		return scale;
	}

	// esses dois caras são bem importantas na hora de renderizar o sprite na tela com o tamanho que a gnt vai querer
	public int getScaledWidth() {
        return largura * scale;
    }

    public int getScaledHeight() {
        return altura * scale;
    }
}
