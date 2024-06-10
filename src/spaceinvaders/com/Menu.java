package spaceinvaders.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Menu extends JFrame {
    private static final long serialVersionUID = 1L;

    // configuração basica da janela do menu
    public Menu() {
        setTitle("Space Invaders");
        setSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new FlowLayout());
        setVisible(true);
    }

    // Vitor quando for criar mais componentes, siga essa lógica, crie eles aqui, chame esse método lá na Main.setMenu(), fica muito 
    // mais organizado
    
    
    // método que cria e configura o titulo do jogo
    public JLabel createTitle(String title) {
        JLabel label = new JLabel(title);
        label.setFont(getFont());
        label.setForeground(Color.green);
        add(label);
        return label;
    }

    // método que cria e configura o botao de novo jogo (ou qualquer outra String que vcs preferirem)
    public JButton createButtonOfStart(String text) {
        JButton start = new JButton(text);
        start.setPreferredSize(new Dimension(100, 50));
        add(start);
        return start;
    }
}
