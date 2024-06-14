package spaceinvaders.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Score extends JFrame {
    private static final long serialVersionUID = 1L;

    // configuração basica da janela do menu
    public Score() {
        setTitle("Space Invaders");
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new FlowLayout());
        setVisible(true);
    }

    // método que cria e configura o botao de voltar
    public JButton createButtonOfretornar(String text) {
        JButton retornar = new JButton(text);
        retornar.setPreferredSize(new Dimension(100, 50));
        add(retornar);
        return retornar;
    }
}