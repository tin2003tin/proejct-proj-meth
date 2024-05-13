package scenes.fightscene;


import gui.HealthBar;
import gui.PlayerModel;
import scenes.roomscene.RoomScene;
import system.GameScene;
import system.SceneManager;
import type.constant.Settings;
import type.interfaces.Moveable;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import main.Main;
import player.GrandMa;
import player.Player;
import type.GameTime;
import type.PlayerDirection;

import java.util.Random;

public class FightScene extends GameScene {

    private HealthBar healthBar1;
    private  HealthBar healthBar2;

    private static final String[] BACKGROUND_NIGHT_TIME_IMAGES = {
            "background/bg_1_n.png",
            "background/bg_2_n.jpg",
            "background/bg_3_n.png"
    };

    private static final String[] BACKGROUND_DAY_TIME_IMAGES = {
            "background/bg_2_d.jpg",
            "background/bg_1_d.png",
    };

    public FightScene() {
        super(new FightLogic());
        this.setFocusTraversable(true);
        if (this.getScript() instanceof FightLogic script) {
            script.setView(this);
        }
    }

    public void initscene() {
        this.getChildren().clear();
        this.setBackground();
        this.initChangeBGButton();
        SceneManager.addPlayerToScene(this,50,Settings.GROUND_HEIGHT, Settings.DEFAULT_SCENE_WIDTH - 200,Settings.GROUND_HEIGHT);
        Main.player1.setDefault();
        Main.player2.setDefault();
        this.inithealthBars();
        this.initChangeSceneButton();
    }

    private void setBackground() {
        String randomImagePath = getRandomImagePath();
        Image image = new Image(randomImagePath);
        ImageView view = new ImageView(image);
        view.setFitWidth(Settings.DEFAULT_BG_WIDTH);
        view.setFitHeight(Settings.DEFAULT_BG_HEIGHT);

        double offsetX = (Settings.DEFAULT_SCENE_WIDTH - Settings.DEFAULT_BG_WIDTH) / 2.0;
        double offsetY = (Settings.DEFAULT_SCENE_HEIGHT - Settings.DEFAULT_BG_HEIGHT) / 2.0;
        view.setLayoutX(offsetX);
        view.setLayoutY(offsetY);

        getChildren().addAll(view);
    }

    private String getRandomImagePath() {
        Random random = new Random();
        if (Main.gameTime == GameTime.DayLight) {
            int index = random.nextInt(BACKGROUND_DAY_TIME_IMAGES.length);
            return BACKGROUND_DAY_TIME_IMAGES[index];
        } else {
            int index = random.nextInt(BACKGROUND_NIGHT_TIME_IMAGES.length);
            return BACKGROUND_NIGHT_TIME_IMAGES[index];
        }
    }

    private void initChangeBGButton() {
        Button changeBackgroundButton = new Button("Change Background");
        changeBackgroundButton.setOnAction(event -> initscene());
        changeBackgroundButton.setLayoutX(20);
        changeBackgroundButton.setLayoutY(20);
        changeBackgroundButton.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
                event.consume();
            }
        });
        getChildren().add(changeBackgroundButton);
    }

    private void initChangeSceneButton() {
        Button changeSceneButton = new Button("Back");
        changeSceneButton.setOnAction(event -> {
            Main.player1.setDefault();
            Main.player2.setDefault();
            SceneManager.moveScene(new RoomScene());
        });
        changeSceneButton.setLayoutY(20);
        changeSceneButton.setLayoutX(150);
        changeSceneButton.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
                event.consume();
            }
        });
        getChildren().add(changeSceneButton);
    }

    public void inithealthBars() {
        this.getChildren().removeAll(this.healthBar1,healthBar2);
        this.healthBar1 = new HealthBar(Main.player1.getMax_health(),Main.player1.getHealth(),35);
        this.healthBar1.setLayoutX(30);
        this.healthBar1.setLayoutY(100);
        this.healthBar2 = new HealthBar(Main.player2.getMax_health(),Main.player2.getHealth(),35);
        this.healthBar2.setLayoutX(Settings.DEFAULT_BG_WIDTH-380);
        this.healthBar2.setLayoutY(100);
        this.getChildren().addAll(this.healthBar1,this.healthBar2);
    }

    @Override
    public void setPlayerListener() {
        this.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            Moveable player1 = (Moveable) Main.player1;
            Moveable player2 = (Moveable) Main.player2;
            // Calling Visual Animation To Trigger
            if (keyCode == KeyCode.A ) {
                player1.move(keyCode);
            }
            if (keyCode == KeyCode.D) {
                player1.move(keyCode);
            }
            if (keyCode == KeyCode.L || keyCode == KeyCode.QUOTE) {
                player2.move(keyCode);
            }

            if (keyCode == KeyCode.W ) {
                player1.jump(keyCode);
            }

            if (keyCode == KeyCode.P ) {
                player2.jump(keyCode);
            }

            if (keyCode == KeyCode.V) {
                if (!((Player)player1).getPlayerAnimation().getModel().isRightAttacking() && !((Player)player1).getPlayerAnimation().getModel().isLeftAttacking() ) {
                    ((Player) player1).getPlayerAnimation().getModel().setMoveDistanceX(((Player) player1).getPlayerAnimation().getModel().getMoveDistanceX()/2);
                    player1.attack(keyCode);
                }
            }

            if (keyCode == KeyCode.M) {
                if (!((Player)player2).getPlayerAnimation().getModel().isRightAttacking() && !((Player)player2).getPlayerAnimation().getModel().isLeftAttacking() ) {
                    ((Player) player2).getPlayerAnimation().getModel().setMoveDistanceX(((Player) player2).getPlayerAnimation().getModel().getMoveDistanceX()/2);
                    player2.attack(keyCode);
                }
            }

        });

        this.setOnKeyReleased(event -> {
            KeyCode keyCode = event.getCode();
            Moveable player1 = (Moveable) Main.player1;
            Moveable player2 = (Moveable) Main.player2;
            if (keyCode == KeyCode.W || keyCode == KeyCode.A || keyCode == KeyCode.D) {
                ((Moveable) player1).stop(keyCode);
            } else if ( keyCode == KeyCode.L || keyCode == KeyCode.QUOTE || keyCode == keyCode.P) {
                ((Moveable) player2).stop(keyCode);
            }
        });

        this.requestFocus();
    }



    void endedScene() {
        String sentence = "";
        if (Main.player1.getHealth() == 0 ) {
            if (Main.player1 instanceof GrandMa) {
                sentence += "Granddaughter is Win";
            } else {
                sentence += "Grandmother is Win";
            }
            Main.player1.getPlayerAnimation().getModel().setY(1000);
        } else {
            if (Main.player2 instanceof GrandMa) {
                sentence += "Granddaughter is Win";
            } else {
                sentence += "Grandmother is Win";
            }
            Main.player2.getPlayerAnimation().getModel().setY(1000);
        }
        String finalSentence = sentence;
        Platform.runLater(() -> {
            Text text  = new Text("GAMEOVER");
            Text win = new Text(finalSentence);
            text.setFont(Font.font("Arial", FontWeight.BOLD, 100));
            text.setFill(Color.RED);
            text.setLayoutY(300);
            text.setLayoutX(Settings.DEFAULT_SCENE_WIDTH/2 - 300);
            win.setFont(Font.font("Arial", FontWeight.BOLD, 60));
            win.setFill(Color.WHITE);
            win.setLayoutY(370);
            win.setLayoutX(Settings.DEFAULT_SCENE_WIDTH/2 - 310);
            this.getChildren().addAll(text,win);
            this.initBackButton();
        });
    }


    private void initBackButton() {
        Button changeSceneButton = new Button("Back");
        changeSceneButton.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        changeSceneButton.setPrefHeight(50);
        changeSceneButton.setPrefWidth(200);
        changeSceneButton.setOnAction(event -> {
            Main.player1.setDefault();
            Main.player2.setDefault();
            SceneManager.moveScene(new RoomScene());
        });
        changeSceneButton.setLayoutY(400);
        changeSceneButton.setLayoutX(Settings.DEFAULT_SCENE_WIDTH/2 - 120);
        changeSceneButton.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
                event.consume();
            }
        });
        getChildren().add(changeSceneButton);
    }
}
