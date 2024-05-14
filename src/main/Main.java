package main;


import javafx.application.Application;
import javafx.stage.Stage;
import player.GrandDa;
import player.GrandMa;
import player.Player;
import scenes.roomscene.RoomScene;
import system.AudioManager;
import system.SceneManager;
import type.GameTime;

public class Main extends Application {

	public static Player player1 = new GrandMa();
	public static Player player2 = new GrandDa();
	public static boolean editMode = false;
	public static GameTime gameTime = GameTime.DayLight;
	@Override
	public void start(Stage primaryStage) {
		AudioManager.getInstance().loadAudio();
		SceneManager.setStage(primaryStage);
		SceneManager.moveScene(new RoomScene());
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void stop() {
		System.exit(0);
	}
}
