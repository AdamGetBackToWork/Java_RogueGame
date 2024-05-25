package main;

// import java.io.File;
// import java.net.URL;

// import javax.sound.sampled.AudioSystem;
// import javax.sound.sampled.AudioInputStream;
// import javax.sound.sampled.Clip;

// public class Sound {

//     Clip clip;
//     URL soundURL[] = new URL[5];
//     // File file01 = new File("res\\sound\\Ain't that a kick in the head.wav");

//     public Sound(){
//         soundURL[0] = getClass().getResource("res\\sound\\Ain't that a kick in the head.wav");
//     }

//     public void setFile(int i){

//         try {
//             AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
//             clip = AudioSystem.getClip();
//             clip.open(ais);
//         } catch (Exception e){
//             e.printStackTrace();
//         }

//     }

//     public void play(){
//         clip.start();
//     }

//     public void stop(){
//         clip.stop();
//     }

//     public void loop(){
//         clip.loop(Clip.LOOP_CONTINUOUSLY);
//     }
// }


import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sound {

    Clip clip;
    File soundFile;
    FloatControl fc;
    int volumeBar = 3;
    float volume;

    public Sound(){
        soundFile = new File("res/sound/Ain't that a kick in the head.wav");
    }

    public void setFile(){

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
            fc = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            controlVolume();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void play(){
        clip.start();
    }

    public void stop(){
        clip.stop();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void controlVolume(){

        switch(volumeBar){
            case 0: volume = -80f; break;
            case 1: volume = -20f; break;
            case 2: volume = -12f; break;
            case 3: volume = -5f; break;
            case 4: volume = 1f; break;
            case 5: volume = 6f; break;
        }
        fc.setValue(volume);
    }
}
