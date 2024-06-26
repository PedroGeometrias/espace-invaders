package spaceinvaders.com;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Credits {
    // JTextArea é uma área de texto com várias linhas onde o texto pode ser manipulado
    // Se usássemos JLabel, teríamos que usar HTML para controlar a renderização, o que seria mais trabalhoso
    private JTextArea creditosArea;

    public Credits() {
        // Inicializa a JTextArea com 5 linhas e 20 colunas
        this.creditosArea = new JTextArea(5, 20);
        this.creditosArea.setEditable(false);  // Impede a edição do texto pelo usuário
        this.creditosArea.setOpaque(false);  // Torna a JTextArea transparente
        this.creditosArea.setWrapStyleWord(true);  // Habilita quebra de linha por palavra
        this.creditosArea.setLineWrap(true);  // Habilita a quebra de linha automática
        loadCredits("assets/txt/creditos.txt");  // Carrega os créditos do arquivo especificado
    }

    // Carrega os créditos de um arquivo de texto
    // BufferedReader é usado para ler caracteres em um buffer de memória, melhorando a eficiência
    private void loadCredits(String filePath) {
        try (BufferedReader leitor = new BufferedReader(new FileReader(filePath))) {
            // Lê o conteúdo do arquivo e o coloca na JTextArea
            this.creditosArea.read(leitor, null);
        } catch (IOException e) {
            // Se ocorrer um erro ao ler o arquivo, imprime o stack trace
            e.printStackTrace();
            // Define um texto padrão caso o arquivo de créditos não exista
            this.creditosArea.setText("Pedro Haro\nLucas Tiepo\nVitor\nRafael Lucena\nIzabelle Rondon");
        }
    }

    // Exibe os créditos em um JFrame
    public void showCredits(JFrame parentFrame, Font customFont) {
        // Cria um novo JFrame para exibir os créditos
        JFrame creditosFrame = new JFrame("Creditos");
        creditosFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Fecha o frame ao clicar no botão de fechar
        creditosFrame.setSize(300, 150);  // Define o tamanho do frame
        creditosFrame.setLocationRelativeTo(parentFrame);  // Centraliza o frame em relação ao frame pai
        creditosFrame.getContentPane().setBackground(Color.black);  // Define a cor de fundo do frame

        // Define a fonte customizada para a JTextArea
        this.creditosArea.setFont(customFont);

        // Adiciona a JTextArea ao frame
        creditosFrame.add(this.creditosArea);
        // Torna o frame visível
        creditosFrame.setVisible(true);
    }

    // Método para salvar os créditos no arquivo de texto
    public void saveCredits(String newCredits, String filePath) {
        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(filePath))) {
            // Escreve o novo conteúdo dos créditos no arquivo
            escritor.write(newCredits);
            // Atualiza a JTextArea com o novo conteúdo
            this.creditosArea.setText(newCredits);
        } catch (IOException e) {
            // Se ocorrer um erro ao salvar o arquivo, imprime o stack trace
            e.printStackTrace();
        }
    }
}
