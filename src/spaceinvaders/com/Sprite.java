package spaceinvaders.com;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Sprite {
	// essa classe representa um sprite, e sua propriedaades
	// esse cara representa as imagens
	private BufferedImage img;
    // tenta imaginar o spritesheet como um plano cartesiano, a gnt vai escolher as cordenadas começando do canto superior esquerdo,
	// e dps com a largura e altura escolher o quanto a gnt que cortar,  o scale define o tamanho que a imagem vai aparecer na tela
	private int posicaoAtualNoX, posicaoAtualNoY, ordenadas, abscissas, largura, altura, scale; 

	public Sprite(BufferedImage img, int abscissas, int ordenadas, int largura, int altura, int scale) { 
		this.abscissas = abscissas;
		this.ordenadas = ordenadas;
		this.posicaoAtualNoX = posicaoAtualNoX;
		this.posicaoAtualNoY = posicaoAtualNoY;
		this.largura = largura;
		this.altura = altura;
		this.scale = scale;
		this.img = img;
	}
	
	// setando getters e setters 
	public BufferedImage getImg() {
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
	
	public int getPosicaoAtualNoX() {
		posicaoAtualNoX = abscissas;
		return posicaoAtualNoX;
	}

	public void setPosicaoAtualNoX(int posicaoAtualNoX) {
		this.posicaoAtualNoX = getAbscissas();
	}

	// esses dois são responsáveis por atualizar a posição do player
	public void setOrdenadas(int ordenadas) {
        this.ordenadas = ordenadas;
    }

    public void setAbscissas(int abscissas) {
        this.abscissas = abscissas;
    }
    
    

	// esses dois caras são bem importantas na hora de renderizar o sprite na tela com o tamanho que a gnt vai querer
	public int getScaledWidth() {
        return largura * scale;
    }

    public int getScaledHeight() {
        return altura * scale;
    }
}