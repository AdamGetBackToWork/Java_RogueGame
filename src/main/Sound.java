// Klasa do obslugi dzwieku, do zaczytywania go i zmiany glosnosci

package main;

// importy javy
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

// cialo klasy
public class Sound {

    // przygotowanie zmiennych
        Clip clip;
        File soundFile;
        
        
        // Float Control do kontrolowania zmiany poziomu muzyki 
        FloatControl fc;
        int volumeBar = 3;
        float volume;

    // konstruktor klasy
    public Sound() {
        soundFile = new File("res/sound/jazzmusic.wav");
    }

    // metoda do okreslania pliku zrodlowego z muzyka
    public void setFile() {

        // calkiem standardowy sposob odtworzenia muzyki
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            controlVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // metoda do puszczenia muzyki
    public void play() {
        clip.start();
    }

    // metoda do zatrzymania muzyki
    public void stop() {
        clip.stop();
    }

    // metoda do zapetlenia muzyki
    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    // metoda do kontrolowania poziomu glosnosci muzyki
    public void controlVolume() {

        switch (volumeBar) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;
        }
        fc.setValue(volume);
    }
}
