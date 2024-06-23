package spaceinvaders.com;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controles implements KeyListener {
    private Player player;
    private Board board;
    public Controles(Player player, Board b) {
        this.player = player;
        board = b;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No implementation needed
    }

    @Override
    public void keyPressed(KeyEvent e) {
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        player.keyReleased(e);
    }
}