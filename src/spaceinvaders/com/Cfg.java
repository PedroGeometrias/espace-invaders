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
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel para conteúdo
        JPanel frame = new JPanel();
        frame.setLayout(null);
        getContentPane().add(frame, BorderLayout.CENTER);


        // Adicionando um botão
        JButton voltar = new JButton("voltar");
        voltar.setSize(100, 50);
        voltar.setLocation(1, 1);
        frame.add(voltar);
        voltar.addActionListener(e -> {
            dispose();
            Menu ex = new Menu();
            ex.setVisible(true);
        });

    }

}