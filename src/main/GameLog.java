package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

// klasa odpowiedzialna za zapis statystyk na zakończeniu rozgrywki do pliku
public class GameLog {
    // kolekcja przechowująca statystyki
    private HashMap<String, String> gamestats = new HashMap<>();  
    
    private GameBoard gb;
    private LocalTime startTime, endTime;

    // konstruktor
    public GameLog(GameBoard gb) {
        this.gb = gb;
        this.startTime = LocalTime.now();
    }

    // metoda odpowiedzialna za dodanie poszczególnych akutalnych statystyk gry do kolekcji
    public void saveGameStats() {
        gamestats.put("Date", LocalDate.now().toString());
        String gameS = (gb.player.HP!=0) ? "Win" : "Lose"; 
        gamestats.put("Game status", gameS);
        endTime = LocalTime.now();
        Duration gametime = Duration.between(startTime, endTime);
        float gametimef = (float) Math.floor(gametime.getSeconds());
        gamestats.put("Game time [s]", String.valueOf((int) gametimef));
        gamestats.put("HP", Integer.toString(gb.player.HP));
    }

    // metoda odpowiedzialna za zapis do pliku
    public void saveGameLog() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("gamelog.txt"))) {
            writer.write(gamestats.toString());
            writer.write("\n\n");
            writer.write("=== Game Stats === \n");
            for (Map.Entry<String, String> entry : gamestats.entrySet()) {
                writer.write(entry.getKey() + " : " + entry.getValue());
                writer.newLine();
            }
            
        //obsługa wyjątku - wyskakujące okno dialogowe
        } catch (IOException e) {
            System.out.println("Cannot write gamelog to a file!");
            JOptionPane.showMessageDialog(null,
                    "An error occurred while writing to the file: " + e.getMessage(),
                    "File Write Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
