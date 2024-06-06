package spaceinvaders.com;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListeners implements KeyListener{
// esse é o cara onde a gnt seta os controles
	private Sprite nave;
	
	public KeyListeners(Sprite nave) {
		this.nave = nave;
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
			nave.setAbscissas(nave.getAbscissas() + Data.VELOCIDADE); // l move pra direita
		}
		if (key == KeyEvent.VK_H) {
			nave.setAbscissas(nave.getAbscissas() - Data.VELOCIDADE); // h move pra esquerda 
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
