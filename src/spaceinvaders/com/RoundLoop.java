package spaceinvaders.com;

import java.util.List;
import java.util.ArrayList;

public class RoundLoop {
    private Board board;
    private int round;
    private List<Alien> aliens;

    public RoundLoop(Board board) {
        this.board = board;
        this.round = 1; // começar do round 1
        initRound();
    }

    public void initRound() {
        aliens = new ArrayList<>();
        int posicaoInicialAliensX = 0;
        int posicaoInicialAliensY = 0;
        int espacamentoPX = 42;
        int espacamentoPY = 32;

        //faz a logica dos rounds
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 12; j++) {
                Alien alien;
                if (round == 1) {
                    alien = new AlienVerde(posicaoInicialAliensX + espacamentoPX * j, posicaoInicialAliensY + espacamentoPY * i);
                } else if (round == 2) {
                    alien = new AlienVermelho(posicaoInicialAliensX + espacamentoPX * j, posicaoInicialAliensY + espacamentoPY * i);
                } else if (round == 3) {
                    alien = new AlienAzul(posicaoInicialAliensX + espacamentoPX * j, posicaoInicialAliensY + espacamentoPY * i);
                } else if (round == 4) {
                    alien = new AlienRoxo(posicaoInicialAliensX + espacamentoPX * j, posicaoInicialAliensY + espacamentoPY * i);
                } else if (round == 5) {
                    alien = new AlienBoss(posicaoInicialAliensX + espacamentoPX * j, posicaoInicialAliensY + espacamentoPY * i);
                }
                else{
                    
                    alien = new AlienVerde(posicaoInicialAliensX + espacamentoPX * j, posicaoInicialAliensY + espacamentoPY * i);
                }

                if (alien != null) {
                    aliens.add(alien);
                }
            }
        }

        board.setAliens(aliens);//defini os aliens do round atual
     }

     public void nextRound() { //faz o proximo round 
        round++;
        initRound();// chamando a classe que inicia os rounds
    }

    public void checkRoundCompletion() { //verifica se o roud foi completo
        boolean allAliensDead = true;
        for (Alien alien : aliens) {
            if (alien.isVisible()) {
                allAliensDead = false;
                break;
            }
        }

        if (allAliensDead) {
            nextRound();
        }
    }
  }
