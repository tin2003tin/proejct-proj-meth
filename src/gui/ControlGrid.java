package gui;import javafx.geometry.Insets;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.HashMap;

public class ControlGrid extends Pane {

    public ControlGrid() {
        super();


        GridPane player1con = createPlayerControls("W", "A", "S", "D", "V", "Attack");
        GridPane player2con = createPlayerControls("P", ";", "L", "'", "M", "Attack");

        player1con.setLayoutX(860);
        player1con.setLayoutY(550);
        player2con.setLayoutX(720);
        player2con.setLayoutY(550);

        getChildren().addAll(player1con, player2con);
    }

    private GridPane createPlayerControls(String key1, String key2, String key3, String key4, String key5, String attackLabel) {
        GridPane gridPane = new GridPane();

        gridPane.setPadding(new Insets(10));
        gridPane.setAlignment(Pos.CENTER);

        Circle circle1 = addKeyWithCircleBackground(gridPane, key1, 1, 0);
        Circle circle2 = addKeyWithCircleBackground(gridPane, key2, 0, 1);
        Circle circle3 = addKeyWithCircleBackground(gridPane, key3, 1, 1);
        Circle circle4 = addKeyWithCircleBackground(gridPane, key4, 2, 1);
        Circle circle5 = addKeyWithCircleBackground(gridPane, key5, 0, 4);



        Text attackText = new Text(attackLabel);
        attackText.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        gridPane.add(attackText, 1, 2, 2, 1);

        return gridPane;
    }

    private Circle addKeyWithCircleBackground(GridPane gridPane, String key, int col, int row) {
        Text keyText = createKeyText(key);
        Circle circleBackground = new Circle(20, Color.WHITE);
        circleBackground.setStroke(Color.BLACK);
        circleBackground.setStrokeWidth(2);
        double offsetX = 5;
        double offsetY = -5;
        circleBackground.setTranslateX(offsetX);
        circleBackground.setTranslateY(offsetY);

        Pane keyPane = new Pane();
        keyPane.getChildren().addAll(circleBackground, keyText);
        gridPane.add(keyPane, col, row);

        return circleBackground;
    }

    private Text createKeyText(String key) {
        Text text = new Text(key);
        text.setFont(Font.font("Arial", 20));
        return text;
    }

}

