// SceneManager.java
package scene;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import player.Player;
import type.GameTime;

public class SceneManager {
    private static Stage mainStage;

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static void goToRoomScene() {
        RoomScene roomScene = new RoomScene();
        Scene scene = new Scene(roomScene, 1000, 700);
        mainStage.setScene(scene);
        mainStage.setTitle("GrandGame");
        mainStage.show();
    }

    public static void goToFightScene() {
        FightScene fightScene = new FightScene();
        Scene scene = new Scene(fightScene, 1000, 700);
        mainStage.setScene(scene);
        mainStage.setTitle("GrandGame");
        mainStage.show();
    }

    public static void addPlayerToScene(Pane gameScene, int PX1, int PY1, int PX2, int PY2) {
        Player player1 = Main.player1;
        Player player2 = Main.player2;
        player1.getPlayerAnimation().getModel().setLocation(PX1,PY1);
        player2.getPlayerAnimation().getModel().setLocation(PX2,PY2);
        if (gameScene instanceof PlayerOnScene) {
            ((PlayerOnScene) gameScene).setPlayer1(player1);
            ((PlayerOnScene) gameScene).setPlayer2(player2);
        }
        gameScene.getChildren().addAll(player1.getPlayerAnimation().getModel(),player2.getPlayerAnimation().getModel());
    }
}
