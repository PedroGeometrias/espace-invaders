package spaceinvaders.com;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Menu extends JFrame{
        final int WIDTH = 200;
        final int HEIGHT = 200;
        JFrame frame;
        public Menu(JFrame frame) {
            frame = new JFrame();
            setTitle("Space Invaders");
            setSize(new Dimension(800, 600));
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
            getContentPane().setBackground(Color.BLACK);
            title();
        }
private void title(){
    JLabel title = new JLabel("Space Invaders");
    title.setBounds(50, 50, 200, 200);
    title.setFont(getFont());
    title.setForeground(Color.green);
    this.add(title);
}

public void buttons(JFrame f, Drawing graphics, Sprite nave, KeyListeners controles){
    JButton start = new JButton();
    start.setText("Start");
    start.setBounds(50, 100, 100, 50);
    this.add(start);
    start.addActionListener(e -> {
        new Main(f, graphics, nave, controles);
        
        this.dispose();
    });
}

public JFrame createFrame(){
    return new JFrame();
}

}