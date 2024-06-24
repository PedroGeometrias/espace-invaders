package spaceinvaders.com;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Sprite {
	// essa é a classe mais importante, aqui a gnt define atributo dos sprites, que são buffers
	
	// declaro o objeto da imagem base de todo mundo, que é um buffer
    private BufferedImage img;
    
    // reutilizei os nomes antigos pois já estava no codigo antigo, ou seja vai ser muito mais facil de vcs reutilizarem oq vcs 
    // ja tinham feito
    private int ordenadas, abscissas, scale;
    
    // declarei booleana para a morte, quem for fazer o player, alien e a bala vai usar esses caras
    private boolean visible, isDying;

    // escala inicial é setada na instancia, mas pode ser mudada dps
    public Sprite() {
    }

    // getters e setters
    public void die() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public BufferedImage getImg() {
        return img;
    }

    public void setImg(BufferedImage img) {
        this.img = img;
    }

    public int getOrdenadas() {
        return ordenadas;
    }

    public int getAbscissas() {
        return abscissas;
    }

    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    public void setOrdenadas(int ordenadas) {
        this.ordenadas = ordenadas;
    }

    public void setAbscissas(int abscissas) {
        this.abscissas = abscissas;
    }

    public boolean isDying() {
        return isDying;
    }

    public void setDying(boolean dying) {
        this.isDying = dying;
    }

    // basicamente retorno a imagem com a escala nova se necessesário
    public BufferedImage getScaledImg() {
    	// largura é a largura * nv escala
        int width = img.getWidth() * scale;
    	// altura é a altura * nv escala
        int height = img.getHeight() * scale;
        // buffer que quarda o novo sprite
        BufferedImage scaledImg = new BufferedImage(width, height, img.getType());
        Graphics2D g2d = scaledImg.createGraphics();
        //desenho e jogo fora
        g2d.drawImage(img, 0, 0, width, height, null);
        g2d.dispose();
        
        // entrego esse cara pra dps ele ser desenhadop no paintComponent
        return scaledImg;
    }
    
    // metodo da colisao, eu retorno verdadeiro se o sprite do parametro (alien ou player) colidir com o sprite do tiro
    public boolean colidir(Sprite sprite) {
    	// espaco ocupado pela bala
        int ladoEsquerdo = this.abscissas;
        int ladoDireito = this.abscissas + this.img.getWidth() * this.scale;
        int topo = this.ordenadas;
        int parteMaiEmbaixo = this.ordenadas + this.img.getHeight() * this.scale;

        // espaco ocupado pelo alien
        int esquerdoSprite = sprite.abscissas;
        int direitaSprite = sprite.abscissas + sprite.img.getWidth() * sprite.scale;
        int topoSprite = sprite.ordenadas;
        int parteMaisEmbaixoSprite = sprite.ordenadas + sprite.img.getHeight() * sprite.scale;

        return ladoDireito > esquerdoSprite && ladoEsquerdo < direitaSprite && parteMaiEmbaixo > topoSprite && topo < parteMaisEmbaixoSprite;
    }
}
