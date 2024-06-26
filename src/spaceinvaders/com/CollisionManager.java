package spaceinvaders.com;

import java.util.Iterator;
import java.util.List;

public class CollisionManager {

	// calsse que gerencia as colisoes gerais do programa
    public static void checkCollisions(Player player, List<Alien> aliens, BulletManager bulletManager) {
        List<Tiro> playerBullets = bulletManager.getPlayerBullets();
        List<Bullet> alienBullets = bulletManager.getAlienBullets();
        List<BossBullet> bossBullets = bulletManager.getBossBullets();
        List<SpecialBossBullet> specialBossBullets = bulletManager.getSpecialBossBullets();

        // cada metodo de colisao recebe uma lista atrelada a sua bala, o bjeto da colisao, e o bulletManager
        checkPlayerBullets(playerBullets, aliens, bulletManager);
        checkAlienBullets(alienBullets, player, bulletManager);
        checkBossBullets(bossBullets, player, bulletManager);
        checkSpecialBossBullets(specialBossBullets, player, bulletManager);
    }

    
    private static void checkPlayerBullets(List<Tiro> playerBullets, List<Alien> aliens, BulletManager bulletManager) {
        Iterator<Tiro> iterator = playerBullets.iterator();
        while (iterator.hasNext()) {
            Tiro tiro = iterator.next();
            boolean hit = false;
            for (Alien alien : aliens) {
            	// caso colisao aconteca, eu dou dano e deixo a bala invisivel, mesma coisa para todas as balas
                if (tiro.colidir(alien) && alien.isVisible()) {
                    alien.receberDano(tiro.getDano());
                    tiro.setVisible(false);
                    hit = true;
                    break;
                }
            }
            // caso a bala esteja
            if (hit || !tiro.isVisible()) {
                iterator.remove();
                bulletManager.removeBullet(tiro);
            }
        }
    }
    
    // msm co9isa que o alien
    private static void checkAlienBullets(List<Bullet> alienBullets, Player player, BulletManager bulletManager) {
        Iterator<Bullet> iterator = alienBullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (bullet.isVisible() && bullet.colidir(player.getAbscissas(), player.getOrdenadas(),
                player.getImg().getWidth() * player.getScale(), player.getImg().getHeight() * player.getScale())) {
                player.receberDano(bullet.getDano());
                bullet.setVisible(false);
                iterator.remove();
                bulletManager.removeBullet(bullet);
            }
        }
    }

    private static void checkBossBullets(List<BossBullet> bossBullets, Player player, BulletManager bulletManager) {
        Iterator<BossBullet> iterator = bossBullets.iterator();
        while (iterator.hasNext()) {
            BossBullet bullet = iterator.next();
            if (bullet.isVisible() && bullet.colidir(player.getAbscissas(), player.getOrdenadas(),
                    player.getImg().getWidth() * player.getScale(), player.getImg().getHeight() * player.getScale())) {
                player.receberDano(bullet.getDano());
                bullet.setVisible(false);
                iterator.remove();
                bulletManager.removeBullet(bullet);
            }
        }
    }

    // msm coisa que o alie
    private static void checkSpecialBossBullets(List<SpecialBossBullet> specialBossBullets, Player player, BulletManager bulletManager) {
        Iterator<SpecialBossBullet> iterator = specialBossBullets.iterator();
        while (iterator.hasNext()) {
            SpecialBossBullet bullet = iterator.next();
            if (bullet.isVisible() && bullet.colidir(player.getAbscissas(), player.getOrdenadas(),
                    player.getImg().getWidth() * player.getScale(), player.getImg().getHeight() * player.getScale())) {
               // hit kill 
                player.setVisible(false);
                bullet.setVisible(false);
                iterator.remove();
                bulletManager.removeBullet(bullet);
            }
        }
    }
}
