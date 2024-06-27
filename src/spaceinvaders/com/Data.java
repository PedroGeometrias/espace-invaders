package spaceinvaders.com;

public interface Data {
    public final SpriteSheet spriteSheet = new SpriteSheet("assets/art/PixelArtSpaceInavader.png");

    // atributos da tela
    final int WIDTH = 100;
    final int HEIGHT = 100;
    final int SCALE = 5; // Fator de escala para a tela

    // atributos do jogador e alien
    final int VELOCIDADE_INICIAL = 5;
    final int DANO_INICIAL = 50;
    final int VIDA_INICIAL = 50;
    final int QUANTIDADE_MOEDAS_INICIAL = 0;
    final int VELOCIDADE_TIROS_SEG_INICIAL =50;
    final int QUANTIDADE_MOEDAS_DROP_ALIEN = 0;
    final int NUMBER_OF_ALIENS_TO_DESTROY = 24;

    // talvez use no futuro
    final int GO_DOWN = 15;
    final int CHANCE = 5;
    final int DELAY = 17;
}
