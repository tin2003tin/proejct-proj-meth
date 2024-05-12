package scene;


import gui.HealthBar;
import gui.PlayerModel;
import inter.Moveable;
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
import type.PlayerAction;
import type.PlayerDirection;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class FightScene extends PlayerOnScene {
    private static final int DEFAULT_SCENE_WIDTH = 1000;
    private static final int DEFAULT_SCENE_HEIGHT = 700;
    private static final int DEFAULT_BG_WIDTH = 1000;
    private static final int DEFAULT_BG_HEIGHT = 700;

    private HealthBar healthBar1;
    private  HealthBar healthBar2;

    private static final int GROUND_HEIGHT = FightScene.DEFAULT_BG_HEIGHT - 200;


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
        super(Main.player1,Main.player2);
        this.setPrefWidth(DEFAULT_SCENE_WIDTH);
        this.setPrefHeight(DEFAULT_SCENE_HEIGHT);
        this.setFocusTraversable(true);

        this.initscene();
        this.setPlayerListener();
        this.initMoveThread();

    }

    private void initscene() {
        this.getChildren().clear();
        this.setBackground();
        this.initChangeBGButton();
        SceneManager.addPlayerToScene(this,50,GROUND_HEIGHT,FightScene.DEFAULT_SCENE_WIDTH - 200,GROUND_HEIGHT);
        super.getPlayer1().setDefault();
        super.getPlayer2().setDefault();
        this.inithealthBars();
        this.initChangeSceneButton();
    }

    private void setBackground() {
        String randomImagePath = getRandomImagePath();
        Image image = new Image(randomImagePath);
        ImageView view = new ImageView(image);
        view.setFitWidth(DEFAULT_BG_WIDTH);
        view.setFitHeight(DEFAULT_BG_HEIGHT);

        double offsetX = (DEFAULT_SCENE_WIDTH - DEFAULT_BG_WIDTH) / 2.0;
        double offsetY = (DEFAULT_SCENE_HEIGHT - DEFAULT_BG_HEIGHT) / 2.0;
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
            super.stopMoveThread();
            getPlayer1().setDefault();
            getPlayer2().setDefault();
            SceneManager.goToRoomScene();
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

    private void inithealthBars() {
        this.getChildren().removeAll(this.healthBar1,healthBar2);
        this.healthBar1 = new HealthBar(super.getPlayer1().getMax_health(),super.getPlayer1().getHealth(),35);
        this.healthBar1.setLayoutX(30);
        this.healthBar1.setLayoutY(100);
        this.healthBar2 = new HealthBar(super.getPlayer2().getMax_health(),super.getPlayer2().getHealth(),35);
        this.healthBar2.setLayoutX(DEFAULT_BG_WIDTH-380);
        this.healthBar2.setLayoutY(100);
        this.getChildren().addAll(this.healthBar1,this.healthBar2);
    }

    @Override
    void setPlayerListener() {
        this.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            Moveable player1 = (Moveable) super.getPlayer1();
            Moveable player2 = (Moveable) super.getPlayer2();
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
            Moveable player1 = (Moveable) super.getPlayer1();
            Moveable player2 = (Moveable) super.getPlayer2();
            if (keyCode == keyCode.W || keyCode == KeyCode.A || keyCode == KeyCode.D) {
                ((Moveable) player1).stop(keyCode);
            } else if ( keyCode == KeyCode.L || keyCode == KeyCode.QUOTE || keyCode == keyCode.P) {
                ((Moveable) player2).stop(keyCode);
            }
        });

        this.requestFocus();
    }

    @Override
    public void movePlayer(Player player) {
        PlayerModel playerModel = player.getPlayerAnimation().getModel();
            double next_move =  player.getPlayerAnimation().getModel().getMoveDistanceX() * player.getPlayerSpeed();
            if (playerModel.isMovingLeft() && canPlayerMove(player, PlayerDirection.LEFT,next_move) ) {
                playerModel.setLocation(playerModel.getX() - playerModel.getMoveDistanceX() * player.getPlayerSpeed(), playerModel.getY());
            }
            if (playerModel.isMovingRight() && canPlayerMove(player, PlayerDirection.RIGHT,next_move)) {
                playerModel.setLocation(playerModel.getX() + playerModel.getMoveDistanceX() * player.getPlayerSpeed(), playerModel.getY());
            }
            if (player.getPlayerAnimation().getModel().isOnGround()) {
                if (playerModel.isJump()) {
                    playerModel.setOnGround(false);
                    playerModel.setInitialSpeed(player.getJumpPower() * playerModel.getMoveDistanceY());
                }
            } else {
                if (playerModel.getY() <= GROUND_HEIGHT) {
                    playerModel.setY(playerModel.getY() - (playerModel.getInitialSpeed()));
                    playerModel.decreaseSpeed(1);
                } else {
                    playerModel.setY(GROUND_HEIGHT);
                    playerModel.setOnGround(true);

                }
            }
            if (playerModel.getForcePX() > 0) {
                if (canPlayerMove(player, PlayerDirection.RIGHT,playerModel.getForcePX())) {
                    playerModel.setX(playerModel.getX() + playerModel.getForcePX());
                } else {
                    playerModel.setForcePX(0);
                }
                playerModel.setForcePX(playerModel.getForcePX() - 4);
            }
            if (playerModel.getForceNX() > 0) {
                if (canPlayerMove(player, PlayerDirection.LEFT,playerModel.getForceNX())) {
                    playerModel.setX(playerModel.getX() - playerModel.getForceNX());
                } else {
                    playerModel.setForceNX(0);
                }
                playerModel.setForceNX(playerModel.getForceNX() - 4);
            }
    }

    @Override
    void attackPlayer(Player player, Player opposite) {
        PlayerModel playerModel = player.getPlayerAnimation().getModel();
        PlayerModel oppositeModel = opposite.getPlayerAnimation().getModel();

        if (playerModel.isAttackLeft()) {
            double distanceX = playerModel.getX() - oppositeModel.getX();
            double distanceY =  oppositeModel.getY() - playerModel.getY();
            if (distanceX > 30 && distanceX < 150 && distanceY > -100 && distanceY < 100) {

                oppositeModel.setForceNX(10 * player.getKnockback());
                opposite.setHealth((int) (opposite.getHealth() - player.getAttack()));
                this.inithealthBars();
            }
            playerModel.setAttackLeft(false);
        } else if (playerModel.isAttackRight())
        {
            double distanceX = oppositeModel.getX() - playerModel.getX();
            double distanceY =  oppositeModel.getY() - playerModel.getY();

            if (distanceX > 30 && distanceX < 150 && distanceY > -100 && distanceY <= 100) {

                oppositeModel.setForcePX(10 * player.getKnockback());
                opposite.setHealth((int) (opposite.getHealth() - player.getAttack()));
                this.inithealthBars();
            }
            playerModel.setAttackRight(false);
        }
    }

    @Override
    boolean canPlayerMove(Player player, PlayerDirection playerDirection,double next_move) {
        double hitblock = player.getPlayerAnimation().getHitblock();
        double player_X = player.getPlayerAnimation().getModel().getX();
        double player_y = player.getPlayerAnimation().getModel().getY();
        switch (playerDirection) {
            case UP -> {

            }
            case DOWN ->  {
                return  true;
            }
            case RIGHT -> {
                if (player_X + next_move > DEFAULT_SCENE_WIDTH - 180) {
                    return  false;
                }
            }
            case LEFT ->  {
                if (player_X- next_move < 10) {
                    return  false;
                }
            }
        }
        return true;
    }

    @Override
    void endedScene() {
        String sentence = "";
        if (getPlayer1().getHealth() == 0 ) {
            if (getPlayer1() instanceof GrandMa) {
                sentence += "Granddaughter is Win";
            } else {
                sentence += "Grandmother is Win";
            }
            getPlayer1().getPlayerAnimation().getModel().setY(1000);
        } else {
            if (getPlayer2() instanceof GrandMa) {
                sentence += "Granddaughter is Win";
            } else {
                sentence += "Grandmother is Win";
            }
            getPlayer2().getPlayerAnimation().getModel().setY(1000);
        }
        String finalSentence = sentence;
        Platform.runLater(() -> {
            Text text  = new Text("GAMEOVER");
            Text win = new Text(finalSentence);
            text.setFont(Font.font("Arial", FontWeight.BOLD, 100));
            text.setFill(Color.RED);
            text.setLayoutY(300);
            text.setLayoutX(DEFAULT_SCENE_WIDTH/2 - 300);
            win.setFont(Font.font("Arial", FontWeight.BOLD, 60));
            win.setFill(Color.WHITE);
            win.setLayoutY(370);
            win.setLayoutX(DEFAULT_SCENE_WIDTH/2 - 310);
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
            super.stopMoveThread();
            getPlayer1().setDefault();
            getPlayer2().setDefault();
            SceneManager.goToRoomScene();
        });
        changeSceneButton.setLayoutY(400);
        changeSceneButton.setLayoutX(DEFAULT_SCENE_WIDTH/2 - 120);
        changeSceneButton.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.ENTER || event.getCode() == KeyCode.SPACE) {
                event.consume();
            }
        });
        getChildren().add(changeSceneButton);
    }
}
