package scenes.roomscene;

import animation.EditFleid;
import gui.ControlGrid;
import gui.Room;
import scenes.roomscene.RoomLogic;
import system.GameScene;
import system.SceneManager;
import type.constant.Settings;
import type.interfaces.Moveable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import main.Main;

public class RoomScene extends GameScene {

    private static final double scaleFactor = 0.75;
    private static final int ACTUAL_ROOM_SIZE = 600;

    private static final double ROOM_SIZE = ACTUAL_ROOM_SIZE * scaleFactor;

    public RoomScene() {
        super(new RoomLogic());
    }

     public void initscene() {
        this.getChildren().clear();
        this.setBackground();
        this.initRooms();
        this.initControlGrid();
        SceneManager.addPlayerToScene(this, 150, 270, Settings.DEFAULT_SCENE_WIDTH - 350, 270);
        Main.player1.setPlayerSpeed(0.5);
        Main.player2.setPlayerSpeed(0.5);
        Main.player1.getPlayerAnimation().setAnimantionDelay(25);
        Main.player2.getPlayerAnimation().setAnimantionDelay(25);
        this.initEditButton();
    }

    private void setBackground() {
        Image image = new Image("/background/room.jpg");
        ImageView view = new ImageView(image);
        view.setFitWidth(Settings.DEFAULT_SCENE_WIDTH);
        view.setFitHeight(Settings.DEFAULT_SCENE_HEIGHT);
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
        room.setLayoutX((Settings.DEFAULT_SCENE_HEIGHT - ROOM_SIZE) / 2.0);
        room.setLayoutY((Settings.DEFAULT_SCENE_HEIGHT - ROOM_SIZE) / 2.0);

        room.setScaleX(scaleFactor);
        room.setScaleY(scaleFactor);

        return room;
    }

    private void initEditButton() {
       this.getChildren().add(new EditFleid());
    }

    @Override
    public void setPlayerListener() {
        this.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            Moveable player1 = (Moveable) Main.player1;
            Moveable player2 = (Moveable) Main.player2;
            if (keyCode == KeyCode.W || keyCode == KeyCode.S || keyCode == KeyCode.A || keyCode == KeyCode.D) {
                player1.move(keyCode);
            }
            if (keyCode == KeyCode.P || keyCode == KeyCode.SEMICOLON || keyCode == KeyCode.L || keyCode == KeyCode.QUOTE) {
                player2.move(keyCode);
            }

        });

        this.setOnKeyReleased(event -> {
            KeyCode keyCode = event.getCode();
            Moveable player1 = (Moveable) Main.player1;
            Moveable player2 = (Moveable) Main.player2;
            if (keyCode == KeyCode.W || keyCode == KeyCode.S || keyCode == KeyCode.A || keyCode == KeyCode.D) {
                ((Moveable) player1).stop(keyCode);
            } else if (keyCode == KeyCode.P || keyCode == KeyCode.SEMICOLON || keyCode == KeyCode.L || keyCode == KeyCode.QUOTE) {
                ((Moveable) player2).stop(keyCode);
            }
        });

        this.requestFocus();
    }
}