package main;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];
    public Sound(){
        soundURL[0] = getClass().getResource("/sound/Slower-Tempo-2020-03-22_-_A_Bit_Of_Hope_-_David_Fesliyan (1).wav");
        soundURL[1] = getClass().getResource("/sound/key.wav");
        soundURL[2] = getClass().getResource("/sound/door.wav");
        soundURL[3] = getClass().getResource("/sound/powerup.wav");
        soundURL[4] = getClass().getResource("/sound/medieval-fanfare-6826.wav");
    }
    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);

        }catch(Exception e){

        }

    }

    public void play(){
        clip.start();

    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);

    }

    public void stop(){
        clip.stop();

    }


}
