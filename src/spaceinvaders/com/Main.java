package spaceinvaders.com;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame {

    private static final long serialVersionUID = 1L;
    
    // criando variaveis aqui para elas serem acessiveis para a classe inteira
    private Drawing graphics;
    private SpritesSheet sprites;
    private Sprite nave;
    private Sprite tiro;
    private ArrayList<Alien> aliens;
    private KeyListeners controles;

    public Main() {
    	// instanciando as variaveis no construtor, estou fazendo isso pois esse é o primeiro método que vai ser chamado por todo mundo
        graphics = new Drawing();
        sprites = new SpritesSheet("assets/art/PixelArtSpaceInavader.png");
        nave = sprites.criarSprite(0, 0, 16, 16, 2);
        tiro = sprites.criarSprite(0, 176, 16, 16, 1);
        //alien = new Alien(sprites, graphics, 0, 16, 16, 16, 2);
        controles = new KeyListeners(nave, tiro);
    }


    // janela do jogo do jogo
    private void setGameFrame() {
    	// propriedaes da janela do jogo
        setSize(new Dimension(Data.WIDTH * Data.SCALE, Data.HEIGHT * Data.SCALE));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Space Invaders");
        setVisible(true);



        // o tamanho do Graphics, que é um componente jpanel tem de ser o mesmo do tamanho da tela do jogo
        // pois a gnt vai desenhar na tela inteira
        graphics.setPreferredSize(new Dimension(Data.WIDTH * Data.SCALE, Data.HEIGHT * Data.SCALE));

        // posição inicial do player
        int initialX = (Data.WIDTH * Data.SCALE - nave.getScaledWidth()) / 2; // nave fica no centro da tela
        int initialY = Data.HEIGHT * Data.SCALE - nave.getScaledHeight() - 24; // nave fica na parte de baixo somando 24 pixeis ao contrario
        
         // aqui eu seto a posi com as variavei de posicçaõ
         nave.setAbscissas(initialX);
         nave.setOrdenadas(initialY);

         tiro.setAbscissas(nave.getAbscissas()+9);
         tiro.setOrdenadas(nave.getOrdenadas());
        
        aliens = new ArrayList<>();
        Alien alienT =  new Alien(sprites, graphics, 0, 16, 16, 16, 2);
        int larguraAliens = alienT.getScaledWidth();
        int alturaAliens = alienT.getScaledHeight();
        int espacoAliens = 20;
        int numeroAliens = 10;
        int ordenadas = 0;
        
        
        for(int y = 0; y < 1; y++ ){
            for(int x = 0; x < numeroAliens; x++){
                
                Alien alienVermelho = new Alien(sprites, graphics, 0, 32, 16, 16, 2);
                Alien alienV = new Alien(sprites, graphics, 0, 16, 16, 16, 2);
                int i = y * (espacoAliens + alturaAliens);
                int j = x * (espacoAliens + larguraAliens);
                alienV.setAbscissas(j);
                alienV.setOrdenadas(i);
                
                graphics.addSprite(alienV);
            }
        }
        
      
        // adiciono os controles setados na classe especifica deles 
        addKeyListener(controles);

        
        // adicion o sprite da nave na arraylist de spritew
        graphics.addSprite(nave);
        graphics.addSprite(tiro);
        
        // adiciono o JPanel dos graficos ao frame
        add(graphics);
        
        // aqui eu modifico o tamanho do frame aos componentes, no caso os graficos
        pack();
    }

    // classe que seta e constrói o menu
    private void setMenuFrame() {
        Menu menu = new Menu();
        JLabel titleOfMenu = menu.createTitle("Space Invaders");
        JButton buttonOfStart = menu.createButtonOfStart("New Game");

        buttonOfStart.addActionListener(e -> {
        	// chamo a janela do jogo ao clicar no botão
            setGameFrame();
            
            // discarto a janela do menu
            menu.dispose();
        });
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.setMenuFrame();

        while (true) {
            main.graphics.repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
