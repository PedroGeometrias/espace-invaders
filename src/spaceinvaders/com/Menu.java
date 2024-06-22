package spaceinvaders.com;
import java.io.FileNotFoundException;

import javax.swing.*;

public class Menu extends JFrame {

    public Menu() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Space Invaders Menu");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLayout(null);
            frame.setLocationRelativeTo(null);

            //componentes
            //titulo
            JLabel label = new JLabel("Space Invaders");
            label.setSize(200, 50);
            label.setLocation(200, 50);
            frame.add(label);
            frame.setVisible(true);

            //botao de start
            JButton startButton = new JButton("Start Game");
            startButton.setSize(100, 50);
            startButton.setLocation(200, 200);
            frame.add(startButton);
            startButton.addActionListener(e -> {
                frame.dispose();
                JFrame ex = new SpaceInvaders();
                ex.setVisible(true);
            });

            //botao de exit
            JButton exitButton = new JButton("Exit");
            exitButton.setSize(100, 50);
            exitButton.setLocation(200, 100);
            frame.add(exitButton);
            exitButton.addActionListener(e -> {
                frame.dispose();
            });

            //botao de config
            JButton configButton = new JButton("configurações");
            configButton.setSize(100, 50);
            configButton.setLocation(200, 300);
            frame.add(configButton);
            configButton.addActionListener(e -> {
                frame.dispose();
                JFrame cfg = new Cfg();
                cfg.setVisible(true);
            });
            //botao de config
            JButton placarButton = new JButton("Placar");
            placarButton.setSize(100, 50);
            placarButton.setLocation(200, 400);
            frame.add(placarButton);
            placarButton.addActionListener(e -> {
                try {
                    frame.dispose();
                    JFrame plc = new Placar();
                    plc.setVisible(true);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
            });
        });
    }
}