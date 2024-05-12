package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import main.Main;
import type.GameTime;

public class Room extends Pane {
    private String URL;
    private static final int DEFAULT_SIZE = 600;

    public Room() {
        super();
        if (Main.gameTime == GameTime.DayLight) {
            this.URL = "room/pretty_day_Room.png";
        } else if (Main.gameTime == GameTime.NightTime) {
            this.URL = "room/pretty_night_Room.png";
        }
        setDimensions(DEFAULT_SIZE, DEFAULT_SIZE);
        setBackgroundImage(this.URL);
    }

    public void setDimensions(double width, double height) {
        setPrefWidth(width);
        setPrefHeight(height);
    }

    public void setBackgroundImage(String imageUrl) {
        Image image = new Image(imageUrl);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(getPrefWidth());
        imageView.setFitHeight(getPrefHeight());
        getChildren().add(imageView);
    }

}
