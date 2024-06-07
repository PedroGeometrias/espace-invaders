package spaceinvaders.com;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public Main(JFrame frame, Drawing graphics, Sprite nave, KeyListeners controles) {

		// setando o tamanho da janela do jogo
		frame.setSize(new Dimension(Data.WIDTH * Data.SCALE, Data.HEIGHT * Data.SCALE));

		// operação de fechamento fecha de verdade a janela
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		// pra deixar a janela no centro da tela
		frame.setLocationRelativeTo(null);

		// seta o container primario como o painel
	    frame.setContentPane(panel);

	    // janela fica visivel
	    frame.setVisible(true);
	    
	    // user n pode modificar o tamanho ( não mudar pois pode causar bugs visuais)
		frame.setResizable(false);
		
		setFrame(frame, graphics ,nave ,controles);
		

	}
	
	private void setFrame(JFrame frame, Drawing graphics, Sprite nave, KeyListeners controles){
			// aqui estou setando o titulo por enquanto, mas dps vou substituir pelo fps do jg
			frame.setTitle("Space Invaders");
			graphics.setPreferredSize(new Dimension(Data.WIDTH* Data.SCALE, Data.HEIGHT * Data.SCALE));
					// setando posicao inicial do player como a mais em baixo e no centro
		int initialX = (Data.WIDTH * Data.SCALE - nave.getScaledWidth()) / 2;
	    int initialY = Data.HEIGHT * Data.SCALE - nave.getScaledHeight() - 24; // esse numero ai veio mais por tentativa e erro
			nave.setAbscissas(initialX);
			nave.setOrdenadas(initialY);
			frame.addKeyListener(controles);
		graphics.addSprite(nave);

        frame.add(graphics);
        frame.pack();
        frame.setVisible(true);

	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		

		// classe que desenha os graficos
		Drawing graphics = new Drawing();


	    // criando os sprites
		SpritesSheet sprites = new SpritesSheet("assets/art/navesBasico.png");
		Sprite nave = sprites.criarSprite(0, 0, 16, 16, 2);
		



		// criando meu objeto que representa os controles
		KeyListeners controles = new KeyListeners(nave);
		
		Menu m = new Menu(frame);

		m.buttons(frame, graphics, nave, controles);

        // game loop basico estremamente basico
        while(true) {
        	graphics.repaint();
        	try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
}
