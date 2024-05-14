package system;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

public class AudioManager {
    private Hashtable<String, Media> audioData;
    private MediaPlayer loopPlayer;
    private static AudioManager instance;

    private AudioManager() {
        // Singleton
        this.audioData = new Hashtable<>();
    }

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        }
        return instance;
    }

    public void loadAudio() {
        // Load all audio from audio folder in resource
        addAudio("RoomScene", "audio/RoomScene.mp3");
        addAudio("FightScene", "audio/FightScene.mp3");
        addAudio("da_swing", "audio/da_swing.mp3");
        addAudio("ma_swing", "audio/ma_swing.mp3");
        addAudio("hit", "audio/hit.mp3");
        addAudio("walk", "audio/walk.mp3");
        addAudio("win", "audio/win.mp3");
    }

    public void addAudio(String key, String url) {
        Media audio = new Media(ClassLoader.getSystemResource(url).toString());
        audioData.put(key, audio);
    }

    public Media getAudio(String key) {
        return audioData.get(key);
    }

    public void playSingle(String key) {
        Media audio = getAudio(key);
        MediaPlayer mediaPlayer = new MediaPlayer(audio);
        mediaPlayer.setVolume(0.4);
        mediaPlayer.play();
    }

    public void playLoop(String key) {
        Media audio = getAudio(key);

        stopLoop();
        loopPlayer = new MediaPlayer(audio);
        loopPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        loopPlayer.setVolume(0.2);
        loopPlayer.play();
    }

    public void stopLoop() {
        if (loopPlayer != null) {
            loopPlayer.stop();
        }
    }
}
