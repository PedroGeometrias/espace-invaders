package spaceinvaders.com;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListeners implements KeyListener{
// esse é o cara onde a gnt seta os controles
	private Sprite nave;
	private Drawing graphics;
	
	public KeyListeners(Sprite nave, Drawing graphics) {
		this.nave = nave;
		this.graphics = graphics;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		// mover pra direita e esquerda é igual o nvim
		if(key == KeyEvent.VK_L) {
			nave.setAbscissas(nave.getAbscissas() + Data.VELOCIDADE_INICIAL); // l move pra direita
		}
		if (key == KeyEvent.VK_H) {
			nave.setAbscissas(nave.getAbscissas() - Data.VELOCIDADE_INICIAL); // h move pra esquerda 
        }
		if (key == KeyEvent.VK_SPACE) {
            Shot tiro = new Shot(nave.getAbscissas(), nave.getOrdenadas(), 8);
            //new Thread(() -> {
                Graphics2D g2d = (Graphics2D) graphics.getGraphics();
                tiro.atirar(g2d);
            //}).start();
        }
	}

	@Override
	public void keyReleased(KeyEvent e) {
// TODO Auto-generated method stub
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_L) {
			nave.setAbscissas(nave.getAbscissas());
		}
		if(key == KeyEvent.VK_H) {
			nave.setAbscissas(nave.getAbscissas());
		}
		
	}
	
	

}
