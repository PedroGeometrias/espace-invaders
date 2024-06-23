package spaceinvaders.com;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class SpaceInvaders extends JFrame implements Runnable {
	private Board board;
	    private Thread gameThread;
	    private boolean isRunning;
	public SpaceInvaders() {
		initGame();
	}

	public void initGame() {

    board = new Board();
    add(board);

    setTitle("Space Invaders");
    setSize(Data.WIDTH * Data.SCALE, Data.HEIGHT * Data.SCALE);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    setLocationRelativeTo(null);
	
	}

	public static void main(String[] args) {
    	// começo a thread
        EventQueue.invokeLater(() -> {
        	// crio um objeto que inicia o jogo
            JFrame iniciar = new SpaceInvaders();
            iniciar.setVisible(true);
            ((SpaceInvaders) iniciar).startGame();
        });
    }
	  // método que controla o inicio da thread
    public void startGame() {
    	// se a thread existir, e n estiver rodando, ela pode ser estarda
        if (gameThread == null || !isRunning) {
        	// instancio a thread, ela recebe essa classe
            gameThread = new Thread(this);
            // inicio a htrad
            gameThread.start();
            // booleano que controla o game loop é setado como true, por padrao ele é false
            isRunning = true;
        }
    }
	@Override
	public void run() {
        while (isRunning) {
            board.repaint();    
            board.updateGame();
            try {
                Thread.sleep(16); // forco 60 fps 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
