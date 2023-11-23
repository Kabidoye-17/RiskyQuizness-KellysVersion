package EPIC;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;


public class MusicPlayer {
    public static void playAudio(String filePath) {
        try {
            File audioFile = new File(filePath);
            if (!audioFile.exists()) {
                System.out.println("Audio file not found.");
                return;
            }

            javax.sound.sampled.AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();

            Thread.sleep(clip.getMicrosecondLength() / 1000);

            clip.close();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException ignored) {
        }
    }

}
