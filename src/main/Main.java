package main;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import player.GrandDa;
import player.GrandMa;
import player.Player;
import scene.SceneManager;
import type.GameTime;

public class Main extends Application {

	public static Player player1 = new GrandMa();
	public static Player player2 = new GrandDa();
	public static boolean editMode = false;
	public static GameTime gameTime = GameTime.DayLight;
	@Override
	public void start(Stage primaryStage) {
		SceneManager.setStage(primaryStage);
		SceneManager.goToRoomScene();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
