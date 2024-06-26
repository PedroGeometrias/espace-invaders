package spaceinvaders.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Menu extends JPanel implements KeyListener {
	
	// botoes sao criados no java usando JButton
    private JButton[] buttons;
    
    // indec da array de botoes
    private int currentSelection;
    
    // a fonte que a gente usa é DinaRemaster, a mesma do meu terminal
    private Font customFont;
    
    // onde o game loop e a main estao
    private SpaceInvaders game;
    
    private Credits credits;

    public Menu(SpaceInvaders game) {
    	
    	this.credits = new Credits();
        this.game = game;
        initMenu();
    }

    private void initMenu() {
    	// caso fonte n exista eu uso a padrao
        try {
        	// crio uma fonte e registro ela
            customFont = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/DinaRemasterII.ttc")).deriveFont(24f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            customFont = new Font("Monospaced", Font.PLAIN, 24); 
        }
        
        // layout grid
        setLayout(new GridLayout(3, 0));
        setBackground(Color.BLACK);

        // crio a array de botoes
        buttons = new JButton[3];
        buttons[0] = createButton("Start Game");
        buttons[1] = createButton("Credits");
        buttons[2] = createButton("Exit");

        for (JButton button : buttons) {
            add(button);
        }

        currentSelection = 0;
        // seto ja o primeiro botao pra cor certa de selecao, se n quando o app for aberto pela primeira vez a cor seria preta
        buttons[currentSelection].setBackground(Color.green);

        // focusable = true pra poder escutar por inputs
        setFocusable(true);
        
        // adiciono o key listener
        addKeyListener(this);
    }

    // crio botoes
    private JButton createButton(String text) {
    	// botao tem o texto passado la quando eu criei a array de botoes
        JButton button = new JButton(text);
        
        // fonte cuztomizada
        button.setFont(customFont);
        button.setBackground(Color.BLACK);
        
        // n mostra aquela borda feia de GUIS do java
        button.setBorderPainted(false);
        
        // n quero pintar tudo, quero deixar alguns pixeis de fora
        button.setOpaque(true);
        return button;
    }

    // mudo a cor quando seleciono
    private void updateSelection(int direction) {
        buttons[currentSelection].setBackground(Color.black);
        // vou para cima ou para baixo, porem n fujo da tela
        currentSelection = (currentSelection + direction + buttons.length) % buttons.length;
        buttons[currentSelection].setBackground(Color.green);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // No implementation needed
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_J) {
            updateSelection(1); // controlar para baixo 
        }
        if (key == KeyEvent.VK_K) {
            updateSelection(-1); // controlar para cima 
        }
        if (key == KeyEvent.VK_ENTER) {
            selectButton(currentSelection); // aqui vc seleciona
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // No implementation needed
    }

    // logica do que os botoes fazem
    private void selectButton(int index) {
        switch (index) {
            case 0:
                game.showGame();
                break;
            case 1:
                credits.showCredits((JFrame) SwingUtilities.getWindowAncestor(this), customFont); 
                break;
            case 2:
                System.exit(0);
                break;
        }
    }
}
