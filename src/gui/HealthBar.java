package gui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;


import javafx.scene.layout.GridPane;

public class HealthBar extends HBox {
    public HealthBar(int max_hp, int hp, int heartSize) {
        this.setSpacing(1);

        int heartsPerLine = 10;
        int totalHearts = (max_hp + 1) / 2;

        int rows = (totalHearts + heartsPerLine - 1) / heartsPerLine;

        GridPane gridPane = new GridPane();
        gridPane.setHgap(1);
        gridPane.setVgap(1);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < heartsPerLine; col++) {
                int heartIndex = row * heartsPerLine + col;
                if (heartIndex < totalHearts) {
                    ImageView heart;
                    int healthIndex = heartIndex * 2;
                    if (healthIndex + 1 == hp) {
                        heart = GameIcon.halfHeart(heartSize);
                    } else if (healthIndex < hp) {
                        heart = GameIcon.fullHeart(heartSize);
                    } else {
                        heart = GameIcon.emptyHeart(heartSize);
                    }
                    gridPane.add(heart, col, row);
                }
            }
        }

        this.getChildren().add(gridPane);
    }
}