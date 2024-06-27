package spaceinvaders.com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class RoundManager {
    private static final String FILE_PATH = "round.txt";

    // Salva o valor do round no arquivo
    public void saveRound(int round) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(String.valueOf(round));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lê o valor do round do arquivo
    public int loadRound() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
