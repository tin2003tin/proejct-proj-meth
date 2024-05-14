package player;

import animation.PlayerAnimation;
import type.interfaces.Moveable;
import type.PlayerType;

abstract public class Player implements Moveable {

    private int max_health;
    private int health;

    PlayerAnimation playerAnimation;
    private double playerSpeed;
    private double jumpPower;
    private double knockback;
    private double attack;

    public Player(PlayerType playerType,int max_health,int health,double playerSpeed,double jumpPower, double knockback) {
        this.playerAnimation = new PlayerAnimation(playerType);
        this.setHealth(health);
        this.setMax_health(max_health);
        this.setPlayerSpeed(playerSpeed);
        this.setJumpPower(jumpPower);
        this.setKnockback(knockback);
        this.initAnimation();
    }

    public double getPlayerSpeed() {
        return playerSpeed;
    }

    public void setPlayerSpeed(double playerSpeed) {
        if (playerSpeed < 0) {
            this.playerSpeed = 0;
            return;
        }
        this.playerSpeed = playerSpeed;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0) {
            this.health = 0;
            return;
        }
        this.health = health;
    }

    public PlayerAnimation getPlayerAnimation() {
        return playerAnimation;
    }

    public void setPlayerAnimation(PlayerAnimation playerAnimation) {
        this.playerAnimation = playerAnimation;
    }

    public int getMax_health() {
        return max_health;
    }

    public void setMax_health(int max_health) {
        this.max_health = max_health;
    }

    abstract public void initAnimation();

    public abstract void setDefault();

    public double getJumpPower() {
        return jumpPower;
    }

    public void setJumpPower(double jumpPower) {
        this.jumpPower = jumpPower;
    }

    public double getKnockback() {
        return knockback;
    }

    public void setKnockback(double knockback) {
        this.knockback = knockback;
    }

    public double getAttack() {
        return attack;
    }

    public void setAttack(double attack) {
        this.attack = attack;
    }
}
