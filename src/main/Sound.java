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

public class Sound {

    Clip clip;
    File soundFile;

    public Sound(){
        soundFile = new File("res/sound/Ain't that a kick in the head.wav");
    }

    public void setFile(){

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
            clip = AudioSystem.getClip();
            clip.open(ais);
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
}
