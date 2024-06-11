// Klasa do obslugi dzwieku, do zaczytywania go i zmiany glosnosci

package main;

// importy javy
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

// cialo klasy
public class InGameSound {

    // przygotowanie zmiennych
    Clip clip;
    File soundFile;

    private String gunshotspath = "res/sound/gunshotgame.wav";
    private String hittakenpath = "res/sound/hittakengame.wav";


    // konstruktor klasy
    public InGameSound(String type) {
        if (type == "gunshots") {
            soundFile = new File(gunshotspath);
        }
        else if (type == "hittaken") {
            soundFile = new File(hittakenpath);
        }
    }

    // metoda do okreslania pliku zrodlowego z muzyka
    public void setFile() {

        // calkiem standardowy sposob odtworzenia muzyki
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
            // fc = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            // controlVolume();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // metoda do puszczenia dzwieku z automatycznym resetem
    public void play() {
        clip.setFramePosition(0);
        clip.start();

    }
}
