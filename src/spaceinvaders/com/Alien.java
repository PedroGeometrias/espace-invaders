package spaceinvaders.com;

import java.awt.image.BufferedImage;

// era obrigatorio ter extendido os Sprite, basicamente o erro bobo que eu falei
public class Alien extends Sprite {
	
	// objeto do inmigo, usa esse cara junto com os getters e setters pra acessar os atributos setados lá
    private Enemy enemy;
    private SpritesSheet sprites;
    private Drawing graph;

    
    public Alien(SpritesSheet sprites, Drawing graph, int abscissas, int ordenadas, int largura, int altura, int scale) {
    	// completando o super (construtor) da classe sprite, passo um bufFerImage no primeiro sprite, que é o subImage da spriteSheet
    	// aquele "." siginifica concatenar métodosa, basicamente
        super(sprites.criarSprite(abscissas, ordenadas, largura, altura, scale).getImg(), abscissas, ordenadas, largura, altura, scale);
        this.enemy = new Enemy();
        this.sprites = sprites;
        this.graph = graph;
        
        // como essa classe inteira representa um sprite, pois ela extende Sprite, eu adiciono ela a array list de sprites
        graph.addSprite(this);
    }

    
    // getters e setters, igualados a classe inimigo
    public BufferedImage createAlien() {
        return sprites.criarSprite(0, 16, 16, 16, 2).getImg();
    }

    public int getVida() {
        return enemy.getVida();
    }

    public void setVida(int vida) {
        enemy.setVida(vida);
    }

    public int getMoeda() {
        return enemy.getMoeda();
    }

    public void setMoeda(int moeda) {
        enemy.setMoeda(moeda);
    }

    public int getDano() {
        return enemy.getDano();
    }

    public void setDano(int dano) {
        enemy.setDano(dano);
    }

    public float getVelocidadeTiro() {
        return enemy.getVelocidadeTiro();
    }

    public void setVelocidadeTiro(float velocidadeTiro) {
        enemy.setVelocidadeTiro(velocidadeTiro);
    }
}
