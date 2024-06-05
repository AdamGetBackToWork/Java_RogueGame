package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashMap;


// klasa odpowiedzialna za zapis statystyk na zakończeniu rozgrywki do pliku
public class GameLog {
    private HashMap<String, String> gamestats = new HashMap<>();
    private GameBoard gb;
    private LocalTime startTime, endTime;

    public GameLog(GameBoard gb){
        this.gb = gb;
        this.startTime = LocalTime.now();
    }

    public void saveGameStats(){
        gamestats.put("HP", Integer.toString(gb.player.HP));
        //gamestats.put("Kills", gb.);
        endTime = LocalTime.now();
        Duration gametime = Duration.between(startTime, endTime);
        gamestats.put("Game time", gametime.toString());
    }

    public void saveGameLog() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(".\\gamelog.txt"))) {
            writer.write(gamestats.toString());
        } catch (IOException e) {
            System.out.println("Cannot write gamelog to a file!");
        }
    }
}
