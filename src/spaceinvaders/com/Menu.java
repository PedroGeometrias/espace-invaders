package spaceinvaders.com;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu extends JFrame {
    final int WIDTH = 200;
    final int HEIGHT = 200;
    JFrame frame;

    // construtor da classe Menu
    public Menu(JFrame frame) {
        this.frame = frame;
        setTitle("Space Invaders"); 

        setSize(new Dimension(800, 600)); // define o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        setLocationRelativeTo(null); 
        setResizable(false); 
        
        getContentPane().setBackground(Color.BLACK); // define a cor de fundo da janela

        setLayout(new FlowLayout()); // define o layout como FlowLayout, que basicamente é como os elementos da jenela tem de se comportar 

        title(); // chama o método para adicionar o title, que deve ser chamado automaticamentegT 

        setVisible(true); // torna a janela visível
    }

    // método que adiciona o título
    private void title() {
        JLabel title = new JLabel("Space Invaders");
        title.setFont(getFont());
        title.setForeground(Color.green);
        this.add(title);
    }

    // método que adiciona os botões
    public void buttons(JFrame f, Drawing graphics, Sprite nave, KeyListeners controles) {
        JButton start = new JButton("Start");
        start.setPreferredSize(new Dimension(100, 50));
        start.addActionListener(e -> {
            new Main(f, graphics, nave, controles);
            this.dispose();
        });
        this.add(start);
    }

    // método para criar um novo JFrame
    public JFrame createFrame() {
        return new JFrame();
    }
}
