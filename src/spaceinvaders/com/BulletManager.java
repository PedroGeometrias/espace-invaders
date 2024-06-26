package spaceinvaders.com;

import java.util.ArrayList;
import java.util.List;

public class BulletManager {
	// bala normal do player
    private final List<Tiro> tiros;
    
    // bala do alien normal
    private final List<Bullet> alienBullets;
    
    // bala do boss que quica
    private final List<BossBullet> bossBullets;
    
    // bala do boss que da hit kill
    private final List<SpecialBossBullet> specialBossBullets;
    
    // objetos sao instanciados ja no contrutor
    public BulletManager() {
        tiros = new ArrayList<>();
        alienBullets = new ArrayList<>();
        bossBullets = new ArrayList<>();
        specialBossBullets = new ArrayList<>();
    }

    // adicionando as balas do player na lista, sinchronizadamente para n ter varias threads e coisas acessando essa lista
    // ao msm tempo
    public synchronized void addPlayerBullet(Tiro tiro) {
        tiros.add(tiro);
    }

    // adicionando as balas dos aliens nas listas, dnv de maneira sincronizada, tem muito lugar nesse projeto querendo acessar
    // essas listas
    public synchronized void addAlienBullet(Bullet bullet) {
    	// insntanceof é um operador binario que retorna true nesse caso : (objeto) instanceof (tipo) 
        if (bullet instanceof AlienBullet) {
        	// caso bullet seja alien, eu adiciono na lista de balas do alien
            alienBullets.add(bullet);
        } else if (bullet instanceof BossBullet) {
        	// caso bullet seja bala do boss, eu adiciono na lista de balas do boss 
            bossBullets.add((BossBullet) bullet);
        } else if (bullet instanceof SpecialBossBullet) {
        	// caso bullet seja especial bala do boss, eu adiciono na lista de balas especiais do boss 
            specialBossBullets.add((SpecialBossBullet) bullet);
        }
    }

    // esses metodos servem para igualar as listas dessa classe com as que as outras classes estao usando
    // basicamente os getters so que do tipo lista, eles sao sincronizados para so uma thread poder acessar eles por
    // vez
    public synchronized List<Tiro> getPlayerBullets() {
        return new ArrayList<>(tiros);
    }

    public synchronized List<Bullet> getAlienBullets() {
        return new ArrayList<>(alienBullets);
    }

    public synchronized List<BossBullet> getBossBullets() {
        return new ArrayList<>(bossBullets);
    }

    public synchronized List<SpecialBossBullet> getSpecialBossBullets() {
        return new ArrayList<>(specialBossBullets);
    }

    // remove os tiros do player no final de cada round
    public synchronized void removeBullet(Tiro tiro) {
        tiros.remove(tiro);
    }

    // remove os turos dos aliens e do boss no final dos rounds
    public synchronized void removeBullet(Bullet bullet) {
        if (bullet instanceof AlienBullet) {
            alienBullets.remove(bullet);
        } else if (bullet instanceof BossBullet) {
            bossBullets.remove(bullet);
        } else if (bullet instanceof SpecialBossBullet) {
            specialBossBullets.remove(bullet);
        }
    }

    // como se fosse um free la do c, basicamente eu limpo as listas
    public synchronized void clearBullets() {
        tiros.clear();
        alienBullets.clear();
        bossBullets.clear();
        specialBossBullets.clear();
    }
}
