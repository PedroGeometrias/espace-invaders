package spaceinvaders.com;

import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.CardLayout;

public class SpaceInvaders extends JFrame implements Runnable {
    private Board board;
    private Menu menu;
    private Thread gameThread;
    private boolean isRunning;
    private CardLayout cardLayout;

    public SpaceInvaders() {
        initGame();
    }

    public void initGame() {
        cardLayout = new CardLayout();
        setLayout(cardLayout);

        menu = new Menu(this);
        board = new Board();

        add(menu, "Menu");
        add(board, "Game");

        setTitle("Space Invaders");
        setSize(Data.WIDTH * Data.SCALE, Data.HEIGHT * Data.SCALE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public void showGame() {
        cardLayout.show(getContentPane(), "Game");
        board.requestFocusInWindow(); // Garantir que o foco esteja no painel do jogo
        startGame();
    }

    public void showMenu() {
        cardLayout.show(getContentPane(), "Menu");
        menu.requestFocusInWindow(); // Garantir que o foco esteja no menu
        stopGame();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            SpaceInvaders game = new SpaceInvaders();
            game.setVisible(true);
        });
    }

    public void startGame() {
        if (gameThread == null || !isRunning) {
            gameThread = new Thread(this);
            gameThread.start();
            isRunning = true;
        }
    }

    public void stopGame() {
        isRunning = false;
        try {
            if (gameThread != null) {
                gameThread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            board.repaint();
            board.updateGame();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public Board getBoard() {
        return board;
    }
}
