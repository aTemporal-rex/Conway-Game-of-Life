import javax.sound.sampled.*;
import java.io.File;

public class Music {
    private static Clip clip;
    private static long clipTimePosition;
    protected static boolean musicPlaying = true; // Allows tracking of the music state

    public static void playMusic(String filePath) {
        try {
            File musicPath = new File(filePath);

            // If the music file exists, lower the volume and start playing it
            if (musicPath.exists()) {
                AudioInputStream audio = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audio);

                // Volume control
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-25.0f); // Decrease volume by some decibel amount

                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

                musicPlaying = true;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void pauseMusic() {
        clipTimePosition = clip.getMicrosecondPosition();
        clip.stop();

        musicPlaying = false;
    }

    public static void resumeMusic() {
        clip.setMicrosecondPosition(clipTimePosition);
        clip.start();

        musicPlaying = true;
    }
}
