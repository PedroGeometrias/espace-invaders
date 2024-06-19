package spaceinvaders.com;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Random;

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
    private Sprite tiroAlien;
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

        Random rand = new Random();
        int randAlien = rand.nextInt(2);
        
        if(randAlien == 0){
            Alien boss = new Alien(sprites, graphics, 16, 16, 80, 80, 2);
            tiroAlien = sprites.criarSprite(16, 176, 16, 16, 3);

            boss.setAbscissas(initialX - 63);
            boss.setOrdenadas(10);
            
            tiroAlien.setAbscissas(boss.getAbscissas()+56);
            tiroAlien.setOrdenadas(boss.getOrdenadas()+122);
            
            graphics.addSprite(boss);
            graphics.addSprite(tiroAlien);

            if(tiroAlien.getOrdenadas() == boss.getOrdenadas()+122){
				new Thread(() -> {   
                    tiroAlien.setAbscissas(boss.getAbscissas()+56); 

				    while (tiroAlien.getOrdenadas() < 500) {
                        tiroAlien.setOrdenadas(tiroAlien.getOrdenadas() + (Data.VELOCIDADE_TIROS_SEG_INICIAL * 2));
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException i) {
                            i.printStackTrace();
                        }
                    }
                    tiroAlien.setOrdenadas(boss.getOrdenadas()+122);
                    tiroAlien.setScale(0);
				}).start();
			}
        } 
        else {
            for(int y = 0; y < 1; y++ ){
                for(int x = 0; x < numeroAliens; x++){

                    randAlien = rand.nextInt(4);
                    int i, j;


                        switch (randAlien) {
                            case 0:
                                Alien alienVermelho = new Alien(sprites, graphics, 0, 32, 16, 16, 2);
                                tiroAlien = sprites.criarSprite(16, 176, 16, 16, 1);
                                
                                i = y * (espacoAliens + alturaAliens);
                                j = x * (espacoAliens + larguraAliens);
                                alienVermelho.setAbscissas(j);
                                alienVermelho.setOrdenadas(i);
                                
                                tiroAlien.setAbscissas(alienVermelho.getAbscissas()+9);
                                tiroAlien.setOrdenadas(alienVermelho.getOrdenadas()+16);
                                
                                graphics.addSprite(alienVermelho);
                                graphics.addSprite(tiroAlien);
                                break;

                            case 1:
                                Alien alienVerde = new Alien(sprites, graphics, 0, 16, 16, 16, 2);
                                tiroAlien = sprites.criarSprite(16, 176, 16, 16, 1);

                                i = y * (espacoAliens + alturaAliens);
                                j = x * (espacoAliens + larguraAliens);
                                alienVerde.setAbscissas(j);
                                alienVerde.setOrdenadas(i);
                                
                                tiroAlien.setAbscissas(alienVerde.getAbscissas()+9);
                                tiroAlien.setOrdenadas(alienVerde.getOrdenadas()+16);
                                
                                graphics.addSprite(alienVerde);
                                graphics.addSprite(tiroAlien);
                                break;

                            case 2:
                                Alien alienAzul = new Alien(sprites, graphics, 0, 48, 16, 16, 2);
                                tiroAlien = sprites.criarSprite(16, 176, 16, 16, 1);

                                i = y * (espacoAliens + alturaAliens);
                                j = x * (espacoAliens + larguraAliens);
                                alienAzul.setAbscissas(j);
                                alienAzul.setOrdenadas(i);
                                
                                tiroAlien.setAbscissas(alienAzul.getAbscissas()+9);
                                tiroAlien.setOrdenadas(alienAzul.getOrdenadas()+16);
                                
                                graphics.addSprite(alienAzul);
                                graphics.addSprite(tiroAlien);
                                break;

                            case 3:
                                Alien alienRosa = new Alien(sprites, graphics, 0, 64, 16, 16, 2);
                                tiroAlien = sprites.criarSprite(16, 176, 16, 16, 1);

                                i = y * (espacoAliens + alturaAliens);
                                j = x * (espacoAliens + larguraAliens);
                                alienRosa.setAbscissas(j);
                                alienRosa.setOrdenadas(i);
                                
                                tiroAlien.setAbscissas(alienRosa.getAbscissas()+9);
                                tiroAlien.setOrdenadas(alienRosa.getOrdenadas()+16);
                                
                                graphics.addSprite(alienRosa);
                                graphics.addSprite(tiroAlien);
                                break;
                    }
                }
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
