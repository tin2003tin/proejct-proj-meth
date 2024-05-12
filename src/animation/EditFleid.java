package animation;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import main.Main;
import player.GrandDa;
import player.GrandMa;

public class EditFleid extends Pane {

    public EditFleid() {
        Button button = new Button("Normal Mode");
        button.setOnAction(event -> {
            if (!Main.editMode) {
                button.setText("Edit Mode");
                createEditGrandMaGrid();
                createEditGrandDaGrid();
                Main.editMode = true;
            } else {
                button.setText("Normal Mode");
                removeEditGrid();
                Main.editMode = false;
            }
        });
        button.setLayoutX(50);
        button.setLayoutY(520);
        this.getChildren().add(button);
    }
    private void createEditGrandMaGrid() {
        GridPane editGrandMaGrid = new GridPane();
        editGrandMaGrid.setPrefSize(600, 200);

        // Create labels for properties
        Text grandMaLabel = new Text("GrandMa");
        grandMaLabel.setFill(Color.GREEN);
        editGrandMaGrid.add(grandMaLabel, 0, 0);

        // MAX_HEALTH
        Text maxHealthLabel = new Text("Max_Health");
        editGrandMaGrid.add(maxHealthLabel, 0, 1);
        TextField maxHealthField = new TextField();
        maxHealthField.setText(String.valueOf(GrandMa.MAX_HEALTH));
        maxHealthField.setMaxWidth(40);
        maxHealthField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int newMaxHealth = Integer.parseInt(newValue);
                GrandMa.MAX_HEALTH = newMaxHealth;
                maxHealthLabel.setFill(Color.GREEN);
            } catch (NumberFormatException e) {
                maxHealthLabel.setFill(Color.RED);
            }
        });
        editGrandMaGrid.add(maxHealthField, 1, 1);

        // SPEED
        Text speedLabel = new Text("Speed");
        editGrandMaGrid.add(speedLabel, 0, 2);
        TextField speedField = new TextField();
        speedField.setText(String.valueOf(GrandMa.SPEED));
        speedField.setMaxWidth(40);
        speedField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                double newSpeed = Double.parseDouble(newValue);
                GrandMa.SPEED = newSpeed;
                speedLabel.setFill(Color.GREEN);
            } catch (NumberFormatException e) {
                speedLabel.setFill(Color.RED);
            }
        });
        editGrandMaGrid.add(speedField, 1, 2);

        // ATTACK
        Text attackLabel = new Text("Attack");
        editGrandMaGrid.add(attackLabel, 0, 3);
        TextField attackField = new TextField();
        attackField.setText(String.valueOf(GrandMa.ATTACK));
        attackField.setMaxWidth(40);
        attackField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                double newAttack = Double.parseDouble(newValue);
                GrandMa.ATTACK = newAttack;
                attackLabel.setFill(Color.GREEN);
            } catch (NumberFormatException e) {
                attackLabel.setFill(Color.RED);
            }
        });
        editGrandMaGrid.add(attackField, 1, 3);

        // JUMP_POWER
        Text jumpPowerLabel = new Text("Jump Power");
        editGrandMaGrid.add(jumpPowerLabel, 3, 1);
        TextField jumpPowerField = new TextField();
        jumpPowerField.setText(String.valueOf(GrandMa.JUMP_POWER));
        jumpPowerField.setMaxWidth(40);
        jumpPowerField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                double newJumpPower = Double.parseDouble(newValue);
                GrandMa.JUMP_POWER = newJumpPower;
                jumpPowerLabel.setFill(Color.GREEN);
            } catch (NumberFormatException e) {
                jumpPowerLabel.setFill(Color.RED);
            }
        });
        editGrandMaGrid.add(jumpPowerField, 4, 1);

        // KNOCKBACK
        Text knockbackLabel = new Text("Knockback");
        editGrandMaGrid.add(knockbackLabel, 3, 2);
        TextField knockbackField = new TextField();
        knockbackField.setText(String.valueOf(GrandMa.KNOCKBACK));
        knockbackField.setMaxWidth(40);
        knockbackField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                double newKnockback = Double.parseDouble(newValue);
                GrandMa.KNOCKBACK = newKnockback;
                knockbackLabel.setFill(Color.GREEN);
            } catch (NumberFormatException e) {
                knockbackLabel.setFill(Color.RED);
            }
        });
        editGrandMaGrid.add(knockbackField, 4, 2);
        //Animation Delay
        Text animnationDelayLabel = new Text("Animation Delay");
        editGrandMaGrid.add(animnationDelayLabel, 3, 3);
        TextField animnationDelayField = new TextField();
        animnationDelayField.setText(String.valueOf(GrandMa.ANIMANTION_DELAY));
        animnationDelayField.setMaxWidth(40);
        animnationDelayField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int newAnimnationDelay = Integer.parseInt(newValue);
                GrandMa.ANIMANTION_DELAY = newAnimnationDelay;
                animnationDelayLabel.setFill(Color.GREEN);
            } catch (NumberFormatException e) {
                animnationDelayLabel.setFill(Color.RED);
            }
        });
        editGrandMaGrid.add(animnationDelayField, 4, 3);


        editGrandMaGrid.setHgap(5);
        editGrandMaGrid.setVgap(5);
        editGrandMaGrid.setLayoutX(150);
        editGrandMaGrid.setLayoutY(520);

        this.getChildren().add(editGrandMaGrid);
    }

    private void createEditGrandDaGrid() {
        GridPane editGrandDaGrid = new GridPane();
        editGrandDaGrid.setPrefSize(600, 200);

        // Create labels for properties
        Text grandMaLabel = new Text("GrandDa");
        grandMaLabel.setFill(Color.GREEN);
        editGrandDaGrid.add(grandMaLabel, 0, 0);

        // MAX_HEALTH
        Text maxHealthLabel = new Text("Max_Health");
        editGrandDaGrid.add(maxHealthLabel, 0, 1);
        TextField maxHealthField = new TextField();
        maxHealthField.setText(String.valueOf(GrandDa.MAX_HEALTH));
        maxHealthField.setMaxWidth(40);
        maxHealthField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int newMaxHealth = Integer.parseInt(newValue);
                GrandDa.MAX_HEALTH = newMaxHealth;
                maxHealthLabel.setFill(Color.GREEN);
            } catch (NumberFormatException e) {
                maxHealthLabel.setFill(Color.RED);
            }
        });
        editGrandDaGrid.add(maxHealthField, 1, 1);

        // SPEED
        Text speedLabel = new Text("Speed");
        editGrandDaGrid.add(speedLabel, 0, 2);
        TextField speedField = new TextField();
        speedField.setText(String.valueOf(GrandDa.SPEED));
        speedField.setMaxWidth(40);
        speedField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                double newSpeed = Double.parseDouble(newValue);
                GrandDa.SPEED = newSpeed;
                speedLabel.setFill(Color.GREEN);
            } catch (NumberFormatException e) {
                speedLabel.setFill(Color.RED);
            }
        });
        editGrandDaGrid.add(speedField, 1, 2);

        // ATTACK
        Text attackLabel = new Text("Attack");
        editGrandDaGrid.add(attackLabel, 0, 3);
        TextField attackField = new TextField();
        attackField.setText(String.valueOf(GrandDa.ATTACK));
        attackField.setMaxWidth(40);
        attackField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                double newAttack = Double.parseDouble(newValue);
                GrandDa.ATTACK = newAttack;
                attackLabel.setFill(Color.GREEN);
            } catch (NumberFormatException e) {
                attackLabel.setFill(Color.RED);
            }
        });
        editGrandDaGrid.add(attackField, 1, 3);

        // JUMP_POWER
        Text jumpPowerLabel = new Text("Jump Power");
        editGrandDaGrid.add(jumpPowerLabel, 3, 1);
        TextField jumpPowerField = new TextField();
        jumpPowerField.setText(String.valueOf(GrandDa.JUMP_POWER));
        jumpPowerField.setMaxWidth(40);
        jumpPowerField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                double newJumpPower = Double.parseDouble(newValue);
                GrandDa.JUMP_POWER = newJumpPower;
                jumpPowerLabel.setFill(Color.GREEN);
            } catch (NumberFormatException e) {
                jumpPowerLabel.setFill(Color.RED);
            }
        });
        editGrandDaGrid.add(jumpPowerField, 4, 1);

        // KNOCKBACK
        Text knockbackLabel = new Text("Knockback");
        editGrandDaGrid.add(knockbackLabel, 3, 2);
        TextField knockbackField = new TextField();
        knockbackField.setText(String.valueOf(GrandDa.KNOCKBACK));
        knockbackField.setMaxWidth(40);
        knockbackField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                double newKnockback = Double.parseDouble(newValue);
                GrandDa.KNOCKBACK = newKnockback;
                knockbackLabel.setFill(Color.GREEN);
            } catch (NumberFormatException e) {
                knockbackLabel.setFill(Color.RED);
            }
        });
        editGrandDaGrid.add(knockbackField, 4, 2);
        //Animation Delay
        Text animnationDelayLabel = new Text("Animation Delay");
        editGrandDaGrid.add(animnationDelayLabel, 3, 3);
        TextField animnationDelayField = new TextField();
        animnationDelayField.setText(String.valueOf(GrandDa.ANIMANTION_DELAY));
        animnationDelayField.setMaxWidth(40);
        animnationDelayField.textProperty().addListener((observable, oldValue, newValue) -> {
            try {
                int newAnimnationDelay = Integer.parseInt(newValue);
                GrandDa.ANIMANTION_DELAY = newAnimnationDelay;
                animnationDelayLabel.setFill(Color.GREEN);
            } catch (NumberFormatException e) {
                animnationDelayLabel.setFill(Color.RED);
            }
        });
        editGrandDaGrid.add(animnationDelayField, 4, 3);


        editGrandDaGrid.setHgap(5);
        editGrandDaGrid.setVgap(5);
        editGrandDaGrid.setLayoutX(450);
        editGrandDaGrid.setLayoutY(520);

        this.getChildren().add(editGrandDaGrid);
    }


    private void removeEditGrid() {
        this.getChildren().removeIf(node -> node instanceof GridPane);
    }
}
