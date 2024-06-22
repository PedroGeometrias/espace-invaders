package spaceinvaders.com;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cfg extends JFrame {

    public Cfg() {
        // Configuração da janela
        setTitle("Configurações");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel frame = new JPanel();
        getContentPane().add(frame, BorderLayout.CENTER);

        // Adicionando um titulo
        JLabel label = new JLabel("Configurações");
        frame.add(label);

        // Adicionando voltar
        JButton voltar = new JButton("voltar");
        voltar.setSize(100, 50);
        voltar.setLocation(200, 300);
        frame.add(voltar);
        voltar.addActionListener(e -> {
            dispose();
            Menu ex = new Menu();
            ex.setVisible(true);
        });

    }

}