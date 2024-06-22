package spaceinvaders.com;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class SpaceInvaders extends JFrame implements Runnable {
	private Board board;
	private RoundLoop roundLoop;
	public SpaceInvaders() {
		initGame();
	}

	public void initGame() {

    board = new Board();
    add(board);
    roundLoop = new RoundLoop(board); // inicialize o game loop

    setTitle("Space Invaders");
    setSize(Data.WIDTH * Data.SCALE, Data.HEIGHT * Data.SCALE);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    setLocationRelativeTo(null);
	
	}

	public static void main(String[] args) { EventQueue.invokeLater(() -> {
        JFrame ex = new Menu();
        ex.setVisible(true);
    });
	}

	@Override
	public void run() {
		
	}
}
