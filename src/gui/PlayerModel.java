package gui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class PlayerModel extends Pane {

    private double moveDistanceX = 10.0;
    private double moveDistanceY = 10.0;
    private boolean isMovingUp;
    private boolean isMovingDown;
    private boolean isMovingLeft;
    private boolean isMovingRight;
    private boolean isRightAttacking;
    private boolean isLeftAttacking;
    private  boolean isOnGround;
    private boolean isJump;
    private double initialSpeed;
    private boolean attackRight;
    private boolean attackLeft;
    private double forcePX;
    private double forceNX;
    private boolean isShow;

    public PlayerModel(ImageView imageView) {
        setDimensions(150, 150);
        setModel(imageView);
        setDefault();
    }

    public void setDimensions(double width, double height) {
        setPrefWidth(width);
        setPrefHeight(height);
    }

    public void setModel(ImageView imageView) {
        this.getChildren().clear();
        getChildren().add(imageView);
    }

    public void clearModel() {
        this.getChildren().clear();
    }

    public void setDefault() {
        setShow(true);
        setAttackRight(false);
        setAttackLeft(false);
        setInitialSpeed(0);
        setForceNX(0);
        setForcePX(0);
        setJump(false);
        setMovingLeft(false);
        setMovingRight(false);
        setMovingDown(false);
        setLeftAttacking(false);
        setMovingUp(false);
    }

    public double getX() {
        return getLayoutX();
    }
    public void setX(double x) {
        this.setLayoutX(x);
    }

    public double getY() {
        return getLayoutY();
    }
    public void setY(double y) {
        this.setLayoutY(y);
    }
    public void setLocation(double x, double y) {
        setLayoutX(x);
        setLayoutY(y);
    }

    public double getMoveDistanceX() {
        return moveDistanceX;
    }

    public void setMoveDistanceX(double moveDistanceX) {
        this.moveDistanceX = moveDistanceX;
    }

    public double getMoveDistanceY() {
        return moveDistanceY;
    }

    public void setMoveDistanceY(double moveDistanceY) {
        this.moveDistanceY = moveDistanceY;
    }

    public boolean isMovingUp() {
        return isMovingUp;
    }

    public void setMovingUp(boolean movingUp) {
        isMovingUp = movingUp;
    }

    public boolean isMovingDown() {
        return isMovingDown;
    }

    public void setMovingDown(boolean movingDown) {
        isMovingDown = movingDown;
    }

    public boolean isMovingLeft() {
        return isMovingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        isMovingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return isMovingRight;
    }

    public void setMovingRight(boolean movingRight) {
        isMovingRight = movingRight;
    }

    public boolean isRightAttacking() {
        return isRightAttacking;
    }

    public void setRightAttacking(boolean rightAttacking) {
        isRightAttacking = rightAttacking;
    }

    public boolean isLeftAttacking() {
        return isLeftAttacking;
    }

    public void setLeftAttacking(boolean leftAttacking) {
        isLeftAttacking = leftAttacking;
    }

    public boolean isOnGround() {
        return isOnGround;
    }

    public void setOnGround(boolean onGround) {
        isOnGround = onGround;
    }

    public boolean isJump() {
        return isJump;
    }

    public void setJump(boolean jump) {
        isJump = jump;
    }

    public double getInitialSpeed() {
        return initialSpeed;
    }

    public void setInitialSpeed(double initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    public void decreaseSpeed(double amount) {
        this.initialSpeed -= amount;
    }

    public boolean isAttackRight() {
        return attackRight;
    }

    public void setAttackRight(boolean attackRight) {
        this.attackRight = attackRight;
    }

    public boolean isAttackLeft() {
        return attackLeft;
    }

    public void setAttackLeft(boolean attackLeft) {
        this.attackLeft = attackLeft;
    }

    public double getForcePX() {
        return forcePX;
    }

    public void setForcePX(double forcePX) {
        this.forcePX = forcePX;
    }

    public double getForceNX() {
        return forceNX;
    }

    public void setForceNX(double forceNX) {
        this.forceNX = forceNX;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }
}
