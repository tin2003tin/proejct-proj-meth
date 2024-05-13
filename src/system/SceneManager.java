// SceneManager.java
package system;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import main.Main;
import player.Player;
import type.interfaces.SceneLogic;

public class SceneManager {
    private static Stage mainStage;
    private static Thread gameThread;

    public static void setStage(Stage stage) {
        mainStage = stage;
    }

    public static void moveScene(GameScene gameScene) {

        if (SceneManager.gameThread != null) {
            SceneManager.gameThread.interrupt();
        }

        Scene scene = new Scene(gameScene, 1000, 700);
        mainStage.setScene(scene);
        mainStage.setTitle("GrandGame");
        mainStage.show();

        SceneManager.startSceneScript(gameScene.getScript());
    }

    public static void startSceneScript(SceneLogic script) {
        SceneManager.gameThread = new Thread(() -> {
            while (true) {
                try {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            script.update();
                        }
                    });
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        SceneManager.gameThread.start();
    }

    public static void addPlayerToScene(Pane gameScene, int PX1, int PY1, int PX2, int PY2) {
        Player player1 = Main.player1;
        Player player2 = Main.player2;
        player1.getPlayerAnimation().getModel().setLocation(PX1,PY1);
        player2.getPlayerAnimation().getModel().setLocation(PX2,PY2);
        gameScene.getChildren().addAll(player1.getPlayerAnimation().getModel(),player2.getPlayerAnimation().getModel());
    }
}
