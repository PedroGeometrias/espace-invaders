package spaceinvaders.com;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public Main(int width, int height, int space, JFrame frame) {

		// setando o tamanho da janela do jogo
		frame.setSize(new Dimension(width * space, height * space));

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

	}
	
	public static void main(String[] args) {
		// setando o tamanho, que será controlado principalmente pelo space
		int width = 100;
		int height = 100;
		int space = 7;

		JFrame frame = new JFrame();
		Main game = new Main(width, height, space, frame);
		
		// aqui estou setando o titulo por enquanto, mas dps vou substituir pelo fps do jg
		frame.setTitle("Space Invaders");

		// classe que desenha os graficos
		Drawing graphics = new Drawing();
	    graphics.setPreferredSize(new Dimension(width * space, height * space));

	    // criando os sprites
		SpritesSheet sprites = new SpritesSheet("assets/art/navesBasico.png");
		Sprite nave = sprites.criarSprite(0, 0, 16, 16, 2);
		graphics.addSprite(nave);

        frame.add(graphics);
        frame.pack();
        frame.setVisible(true);

	}
}
