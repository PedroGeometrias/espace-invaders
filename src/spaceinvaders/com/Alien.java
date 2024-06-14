package spaceinvaders.com;

public class Alien extends Enemy{
    Enemy inimigo;
    SpritesSheet sprites;
    Drawing graphics;

    int vida;
    int dano;
    float velocidadeTiro;
    Sprite alien;
    Sprite alien1;

    public Alien(){
       // this.inimigo=inimigo;
        this.vida = inimigo.getVida();     
        this.dano = inimigo.getDano();
        this.velocidadeTiro = inimigo.getVelocidadeTiro();
        this.graphics = new Drawing();
        this.sprites = new SpritesSheet("assets/art/navesBasico.png");
    }

    //mais vida menos dano
    public Sprite alien1 (){
        setMoeda(getMoeda() + 1);
        vida -= 50;
        setDano(getDano());

        alien1 = sprites.criarSprite(0, 16, 16, 16, 2);
        alien1.setAbscissas(40);
        alien1.setOrdenadas(40);
        graphics.addSprite(alien1);
        
        return alien1;
      
    }
}
