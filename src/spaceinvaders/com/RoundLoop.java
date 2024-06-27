package spaceinvaders.com;

import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class RoundLoop {
	
	RoundManager load;
	// criando objetos Board, que seram usados nessa classe
    private Board board;
    
    // contador de rounds
    private int round;
    
    // lista de aliens
    private List<Alien> aliens;

    public RoundLoop(Board board) {
        this.board = board;
        this.load = new RoundManager();
        // sempre os rounds comecam no 1
        this.round = 1; 
        initRound();
    }

    // iniciador de roundes
	public void initRound() {
        aliens = new ArrayList<>();
        int posicaoInicialAliensX = 0;
        int posicaoInicialAliensY = 0;
        int espacamentoPX = 42;
        int espacamentoPY = 32;

        // se o round for igual a 5, ta na hora do boss 
        if (round == 5) {
            // spawno o boss ( pego a largura vezes o scale, divido por 2 que me da a metade e somo pela largura do sprite
        	// do boss, que é 80)
            Alien boss = new AlienBoss((Data.WIDTH * Data.SCALE) / 2  - 80, 0);;
            aliens.add(boss);
        } else {
        	// caso o round n seja o quinto, eu spawno os aliens normais
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 12; j++) {
                    Alien alien;
                    if(!(load.loadRound() <= 0)) {
                    switch (round % 5) {
                        case 1:
                            alien = new AlienVerde(posicaoInicialAliensX + espacamentoPX * j, posicaoInicialAliensY + espacamentoPY * i);
                            break;
                        case 2:
                            alien = new AlienVermelho(posicaoInicialAliensX + espacamentoPX * j, posicaoInicialAliensY + espacamentoPY * i);
                            break;
                        case 3:
                            alien = new AlienAzul(posicaoInicialAliensX + espacamentoPX * j, posicaoInicialAliensY + espacamentoPY * i);
                            break;
                        case 4:
                            alien = new AlienRoxo(posicaoInicialAliensX + espacamentoPX * j, posicaoInicialAliensY + espacamentoPY * i);
                            break;
                        default:
                            alien = new AlienVerde(posicaoInicialAliensX + espacamentoPX * j, posicaoInicialAliensY + espacamentoPY * i);
                            break;
                    }
                    
                    aliens.add(alien);
                    }
                    else {
                    	round = load.loadRound();
                    }
                }
            }
        }

        board.setAliens(aliens); 
    }

    public void nextRound() {
        round++;
        load.saveRound(round);
        initRound(); 
    }

    public void checkRoundCompletion() { 
        boolean allAliensDead = true;
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                allAliensDead = false;
                break;
            }
        }

        if (allAliensDead) {
        	board.clearBulletsOnRoundCompletion();
            nextRound();
        }
    }
}
