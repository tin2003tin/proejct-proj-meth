package scene;

import animation.EditFleid;
import gui.ControlGrid;
import gui.PlayerModel;
import gui.Room;
import inter.Moveable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import main.Main;
import player.Player;
import type.GameTime;
import type.PlayerDirection;

public class RoomScene extends PlayerOnScene {

    private static final int DEFAULT_SCENE_WIDTH = 1000;
    private static final int DEFAULT_SCENE_HEIGHT = 700;

    private static final double scaleFactor = 0.75;
    private static final int ACTUAL_ROOM_SIZE = 600;

    private static final double ROOM_SIZE = ACTUAL_ROOM_SIZE * scaleFactor;


    public RoomScene() {
        super(Main.player1,Main.player2);
        setPrefWidth(DEFAULT_SCENE_WIDTH);
        setPrefHeight(DEFAULT_SCENE_HEIGHT);
        this.initscene();
        this.setPlayerListener();
    }

    private void initscene() {
        this.getChildren().clear();
        this.setBackground();
        this.initRooms();
        this.initControlGrid();
        SceneManager.addPlayerToScene(this, 150, 270, RoomScene.DEFAULT_SCENE_WIDTH - 350, 270);
        super.getPlayer1().setPlayerSpeed(0.5);
        super.getPlayer2().setPlayerSpeed(0.5);
        super.getPlayer1().getPlayerAnimation().setAnimantionDelay(25);
        super.getPlayer2().getPlayerAnimation().setAnimantionDelay(25);
        this.initEditButton();
        this.initMoveThread();
    }

    private void setBackground() {
        Image image = new Image("res/background/room.jpg");
        ImageView view = new ImageView(image);
        view.setFitWidth(DEFAULT_SCENE_WIDTH);
        view.setFitHeight(DEFAULT_SCENE_HEIGHT);
        getChildren().addAll(view);
    }

    private void initRooms() {
        HBox hBox = new HBox();
        Room room1 = createRoom();
        Room room2 = createRoom();
        hBox.setSpacing(10);
        hBox.getChildren().addAll(room1, room2);
        this.getChildren().add(hBox);
    }

    private void initControlGrid() {
        ControlGrid controlGrid = new ControlGrid();
        this.getChildren().add(controlGrid);
    }

    private Room createRoom() {
        Room room = new Room();
        room.setDimensions(ROOM_SIZE, ROOM_SIZE);
        room.setLayoutX((DEFAULT_SCENE_HEIGHT - ROOM_SIZE) / 2.0);
        room.setLayoutY((DEFAULT_SCENE_HEIGHT - ROOM_SIZE) / 2.0);

        room.setScaleX(scaleFactor);
        room.setScaleY(scaleFactor);

        return room;
    }

    private void initEditButton() {
       this.getChildren().add(new EditFleid());
    }

    @Override
    void setPlayerListener() {
        this.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            Moveable player1 = (Moveable) super.getPlayer1();
            Moveable player2 = (Moveable) super.getPlayer2();
            if (keyCode == KeyCode.W || keyCode == KeyCode.S || keyCode == KeyCode.A || keyCode == KeyCode.D) {
                player1.move(keyCode);
            }
            if (keyCode == KeyCode.P || keyCode == KeyCode.SEMICOLON || keyCode == KeyCode.L || keyCode == KeyCode.QUOTE) {
                player2.move(keyCode);
            }

        });

        this.setOnKeyReleased(event -> {
            KeyCode keyCode = event.getCode();
            Moveable player1 = (Moveable) super.getPlayer1();
            Moveable player2 = (Moveable) super.getPlayer2();
            if (keyCode == KeyCode.W || keyCode == KeyCode.S || keyCode == KeyCode.A || keyCode == KeyCode.D) {
                ((Moveable) player1).stop(keyCode);
            } else if (keyCode == KeyCode.P || keyCode == KeyCode.SEMICOLON || keyCode == KeyCode.L || keyCode == KeyCode.QUOTE) {
                ((Moveable) player2).stop(keyCode);
            }
        });

        this.requestFocus();
    }

    @Override
    public void movePlayer(Player player) {
        double next_move = player.getPlayerAnimation().getModel().getMoveDistanceX() * player.getPlayerSpeed();
        PlayerModel playerModel = player.getPlayerAnimation().getModel();
        if (playerModel.isMovingUp() && canPlayerMove(player, PlayerDirection.UP,next_move)) {
            playerModel.setLocation(playerModel.getX(), playerModel.getY() - playerModel.getMoveDistanceX() * player.getPlayerSpeed());
        }
        if (playerModel.isMovingDown() && canPlayerMove(player, PlayerDirection.DOWN,next_move))  {
            playerModel.setLocation(playerModel.getX(), playerModel.getY() + playerModel.getMoveDistanceX() * player.getPlayerSpeed());
        }
        if (playerModel.isMovingLeft() && canPlayerMove(player, PlayerDirection.LEFT,next_move)) {
            playerModel.setLocation(playerModel.getX() - playerModel.getMoveDistanceX() * player.getPlayerSpeed(), playerModel.getY());
        }
        if (playerModel.isMovingRight() && canPlayerMove(player, PlayerDirection.RIGHT,next_move)) {
            playerModel.setLocation(playerModel.getX() + playerModel.getMoveDistanceX() * player.getPlayerSpeed(), playerModel.getY());
        }
        checkSleep();
        checkgameStart();
    }

    @Override
    void attackPlayer(Player player, Player opposite) {

    }

    @Override
    void endedScene() {

    }

    public void checkSleep() {
        double player1_X = getPlayer1().getPlayerAnimation().getModel().getX();
        double player1_y = getPlayer1().getPlayerAnimation().getModel().getY();
        double player2_X = getPlayer2().getPlayerAnimation().getModel().getX();
        double player2_y = getPlayer2().getPlayerAnimation().getModel().getY();
        if (player1_X >= 220 && player2_X >= 680 ) {
            if (player1_y < 230 && player2_y < 230) {
                if (Main.gameTime == GameTime.NightTime) {
                    Main.gameTime = GameTime.DayLight;
                } else {
                    Main.gameTime = GameTime.NightTime;
                }
                super.stopMoveThread();
                getPlayer1().setDefault();
                getPlayer2().setDefault();
                SceneManager.goToRoomScene();
            }
        }
    }
    private void checkgameStart() {
        double player1_X = getPlayer1().getPlayerAnimation().getModel().getX();
        double player1_y = getPlayer1().getPlayerAnimation().getModel().getY();
        double player2_X = getPlayer2().getPlayerAnimation().getModel().getX();
        double player2_y = getPlayer2().getPlayerAnimation().getModel().getY();
        if (player1_X >= 80 && player1_X <= 180 && player2_X >= 540 && player2_X <= 640 ) {
                if (player1_y >= 310 && player2_y >= 310) {
                    super.stopMoveThread();
                    SceneManager.goToFightScene();
                }
        }
    }


    @Override
    boolean canPlayerMove(Player player, PlayerDirection playerDirection, double next_move) {
        double hitblock = player.getPlayerAnimation().getHitblock();
        double player_X = player.getPlayerAnimation().getModel().getX();
        double player_y = player.getPlayerAnimation().getModel().getY();

        switch (playerDirection) {
            case UP -> {
                if (player_y + hitblock - next_move  < 160) {
                    return false;
                }
                if (player_X + hitblock < 240 && player_X - hitblock > 120 &&   player_y - next_move < 210) {
                    return  false;
                }

                if (player_X + hitblock < 240 + 480 && player_X - hitblock > 120+ 490  &&   player_y - next_move < 210) {
                    return  false;
                }

            }
            case DOWN ->  {
                if (player_y + hitblock + next_move > 330) {
                    return false;
                }

            }
            case RIGHT -> {
                if (player_X + next_move > DEFAULT_SCENE_WIDTH-170) {
                    return false;
                }
                if (player_X - next_move < 230 && player_X + next_move > 230 ) {
                    return  false;
                }

                if (player_X - next_move < 370 && player_X + next_move > 370 ) {
                    return  false;
                }
                if (player_X -next_move < 105 && player_X + next_move > 100 && player_y < 220) {
                    return  false;
                }

                if (player_X -next_move < 580 && player_X + next_move > 575 && player_y < 210) {
                    return  false;
                }


                if (player_X - next_move < 690 && player_X + next_move > 690 ) {
                    return  false;
                }
            }
            case LEFT ->  {
                if (player_X - next_move < 50) {
                    return  false;
                }

                if (player_X + next_move > 500 && player_X - next_move < 500 ) {
                    return  false;
                }
            }
        }
        return true;
    }
}
