package spaceinvaders.com;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // criando variaveis aqui para elas serem acessiveis para a classe inteira
    private Drawing graphics;
    private SpritesSheet sprites;
    private Sprite nave;
    private ArrayList<Alien> aliens;
    private KeyListeners controles;
    private Board board;

    public Main() {
    	board = new Board();
    }
	
	// janela do jogo do jogo
	private void setGameFrame() {
		setSize(new Dimension(Data.WIDTH * Data.SCALE, Data.HEIGHT * Data.SCALE));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Space Invaders");
		add(board);
		setVisible(true);
		board.update();
	}

	// classe que seta e constrói o menu
	private void setMenuFrame() {
		Menu menu = new Menu();
		JLabel titleOfMenu = menu.createTitle("Space Invaders");
		JButton buttonOfStart = menu.createButtonOfStart("New Game");

		buttonOfStart.addActionListener(e -> {
			// chamo a janela do jogo ao clicar no botão
			setGameFrame();

			// discarto a janela do menu
			menu.dispose();
		});
	}

	public static void main(String[] args) {
		Main main = new Main();
		main.setMenuFrame();

		while (true) {
			main.board.update();
			try {
				Thread.sleep(16);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
