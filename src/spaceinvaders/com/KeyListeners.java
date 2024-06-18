package spaceinvaders.com;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListeners implements KeyListener{
// esse é o cara onde a gnt seta os controles
	private Sprite nave;
	private Sprite tiro;
	
	public KeyListeners(Sprite nave, Sprite tiro) {
		this.nave = nave;
		this.tiro = tiro;
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
            if(tiro.getOrdenadas() == nave.getOrdenadas()){
				new Thread(() -> {   
				tiro.setAbscissas(nave.getAbscissas()+9);   
				while (tiro.getOrdenadas() > 0) {
					tiro.setOrdenadas(tiro.getOrdenadas() - Data.VELOCIDADE_TIROS_SEG_INICIAL);
					try {
						Thread.sleep(50);
					} catch (InterruptedException i) {
						i.printStackTrace();
					}
				}
				tiro.setOrdenadas(nave.getOrdenadas());
				}).start();
			}
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
