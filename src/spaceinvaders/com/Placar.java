package spaceinvaders.com;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Placar extends JFrame {
    private JPanel frame;

    public Placar() throws FileNotFoundException{
        // Configuração da janela
        setTitle("Placar");
        setSize(500, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Painel para conteúdo
        frame = new JPanel();
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

        Buscar();
    }

    public void Buscar() throws FileNotFoundException {
        File file = new File("assets/txt/file.txt.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            JLabel label = new JLabel(data);
            label.setSize(100, 50);
            label.setLocation(200, 20);
            frame.add(label);
        }
    }
}