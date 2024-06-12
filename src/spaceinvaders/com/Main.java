package spaceinvaders.com;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main extends JFrame {
	
	// dei uma fatorada nessa classe, ta muito mais organizada
    private static final long serialVersionUID = 1L;
    
    // alguns objteos que são visiveis a toda a classe
    Drawing graphics;
    SpritesSheet sprites;
    Sprite nave;
    KeyListeners controles;

    public Main() {
    	// instanciando esses objetos que devem ser visiveis a toda a classe
        graphics = new Drawing();
        sprites = new SpritesSheet("assets/art/navesBasico.png");
        nave = sprites.criarSprite(0, 0, 16, 16, 2);
        controles = new KeyListeners(nave);
    }

    // método que inicializa a janela
    private void setGameFrame() {
    	// declarando algumas configurações da janela do jogo
        setSize(new Dimension(Data.WIDTH * Data.SCALE, Data.HEIGHT * Data.SCALE));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        // no futuro a gnt pode usar o titulo pra mostrar o fps
        setTitle("Space Invaders");
        setVisible(true);
        
        // desenhando na tela do jogo inteira
        graphics.setPreferredSize(new Dimension(Data.WIDTH * Data.SCALE, Data.HEIGHT * Data.SCALE));

        // setando a posicao inicial do jogador
        int initialX = (Data.WIDTH * Data.SCALE - nave.getScaledWidth()) / 2;
        int initialY = Data.HEIGHT * Data.SCALE - nave.getScaledHeight() - 24;
        nave.setAbscissas(initialX);
        nave.setOrdenadas(initialY);
        
        // janela agora aceita controles
        addKeyListener(controles);
        
        //adicionando o sprite da nave (player) na lista de sprite disponiveis
        graphics.addSprite(nave);

        // adicionando a abilidade de desenhar na tela do jogo
        add(graphics);
        
        // pack ajusta o tamanho da janela ao "preferredSize" e aos componentes junto com seus layouts
        pack();
        
    }

    // setando o menu, a configuracao dele e de seus componentes tem de ser feita na classe "Menu"
    private void setMenuFrame() {
    	// criando um objeto da classe menu, para poder setar paramentros e aspectos dos componentes criados e configurados na
    	// classe menu
        Menu menu = new Menu();
        JLabel titleOfMenu = menu.createTitle("Space Invaders");
        JButton buttonOfStart = menu.createButtonOfStart("New Game");
        
        // quando o botão de start for clicado, a janela do jogo vai abrir, eu pesquisei e a gnt podia usar um CardPanel, mas
        // to com preguica
        buttonOfStart.addActionListener(e -> {
            setGameFrame();
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
