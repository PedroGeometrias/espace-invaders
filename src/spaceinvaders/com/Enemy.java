package spaceinvaders.com;

public class Enemy  {
    
    private int vida;
    private int moeda;
    private int dano;
    private float velocidadeTiro;
    
    public Enemy(){
        this.vida = Data.VIDA_INICIAL;
        this.moeda = Data.QUANTIDADE_MOEDAS_DROP_ALIEN;
        this.velocidadeTiro = Data.VELOCIDADE_TIROS_SEG_INICIAL;
        this.dano = Data.DANO_INICIAL;

    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getMoeda() {
        return moeda;
    }

    public void setMoeda(int moeda) {
        this.moeda = moeda;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    public float getVelocidadeTiro() {
        return velocidadeTiro;
    }

    public void setVelocidadeTiro(float velocidadeTiro) {
        this.velocidadeTiro = velocidadeTiro;
    }
    

    
}
