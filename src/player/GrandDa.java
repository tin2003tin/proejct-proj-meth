// Player.java
package player;

import animation.AnimationData;
import animation.AnimationList;
import animation.grandda.*;
import javafx.scene.input.KeyCode;
import player.Player;
import type.PlayerAction;
import type.PlayerDirection;
import type.PlayerType;

public class GrandDa extends Player {

    public static int MAX_HEALTH = 20;
    public static double SPEED = 1;
    public static double ATTACK = 1;
    public static double JUMP_POWER = 1.5;
    public static double KNOCKBACK = 3;

    public static int ANIMANTION_DELAY = 25;
    public GrandDa() {
       super(PlayerType.GrandDa,MAX_HEALTH,MAX_HEALTH,SPEED,JUMP_POWER,KNOCKBACK);
       super.getPlayerAnimation()
               .initModel(
                       super.getPlayerAnimation()
                               .getAnimationMap()
                               .get(PlayerAction.DOWN_STAY)
                               .getCurrent()
                               .getImageView());
    }

    @Override
    public void move(KeyCode keyCode) {
        if (keyCode == KeyCode.W || keyCode == keyCode.P) {
            super.getPlayerAnimation().getModel().setMovingUp(true);
            super.getPlayerAnimation().getModel().setMovingDown(false);
            super.getPlayerAnimation().setPlayerDirection(PlayerDirection.UP);
        } else if (keyCode == KeyCode.S || keyCode == keyCode.SEMICOLON) {
            super.getPlayerAnimation().getModel().setMovingDown(true);
            super.getPlayerAnimation().getModel().setMovingUp(false);
            super.getPlayerAnimation().setPlayerDirection(PlayerDirection.DOWN);
        }
        if (keyCode == KeyCode.A || keyCode == keyCode.L) {
            super.getPlayerAnimation().getModel().setMovingLeft(true);
            super.getPlayerAnimation().getModel().setMovingRight(false);
            super.getPlayerAnimation().setPlayerDirection(PlayerDirection.LEFT);
        } else if (keyCode == KeyCode.D || keyCode == keyCode.QUOTE) {
            super.getPlayerAnimation().getModel().setMovingRight(true);
            super.getPlayerAnimation().getModel().setMovingLeft(false);
            super.getPlayerAnimation().setPlayerDirection(PlayerDirection.RIGHT);
        }
        if (keyCode == KeyCode.W || keyCode == keyCode.P) {
            super.getPlayerAnimation().getModel().setJump(true);
        }
    }

    @Override
    public void jump(KeyCode keyCode) {
        if (keyCode == KeyCode.W || keyCode == keyCode.P) {
            super.getPlayerAnimation().getModel().setJump(true);
        }
    }

    public void attack(KeyCode keyCode) {
        if (keyCode == keyCode.V || keyCode == KeyCode.M) {
            if (super.getPlayerAnimation().getPlayerDirection() == PlayerDirection.RIGHT) {
                super.getPlayerAnimation().getModel().setRightAttacking(true);
            }
            if (super.getPlayerAnimation().getPlayerDirection() == PlayerDirection.LEFT) {
                super.getPlayerAnimation().getModel().setLeftAttacking(true);
            }
        }
    }

    @Override
    public void stop(KeyCode keyCode) {

        if (keyCode == KeyCode.W || keyCode == keyCode.P) {
            super.getPlayerAnimation().getModel().setMovingUp(false);
            super.getPlayerAnimation().getModel().setJump(false);
        }
        else if (keyCode == KeyCode.S || keyCode == keyCode.SEMICOLON)  {
            super.getPlayerAnimation().getModel().setMovingDown(false);
        }
        else if (keyCode == KeyCode.A || keyCode == keyCode.L)  {
            super.getPlayerAnimation().getModel().setMovingLeft(false);
        }
        else if (keyCode == KeyCode.D || keyCode == keyCode.QUOTE) {
            super.getPlayerAnimation().getModel().setMovingRight(false);
        }
        if (!super.getPlayerAnimation().getModel().isMovingUp() && !super.getPlayerAnimation().getModel().isMovingDown()
                && !super.getPlayerAnimation().getModel().isMovingLeft() && !super.getPlayerAnimation().getModel().isMovingRight()) {
            super.getPlayerAnimation().setAnimantionCount(100);
        }
    }
    @Override
     public void initAnimation() {
        super.getPlayerAnimation().addPlayerAnimation(
                new String[] {
                        "animation.grandda.DownStay",
                        "animation.grandda.DownWalk",
                        "animation.grandda.UpStay",
                        "animation.grandda.UpWalk",
                        "animation.grandda.LeftStay",
                        "animation.grandda.LeftWalk",
                        "animation.grandda.RightStay",
                        "animation.grandda.RightWalk",
                        "animation.grandda.RightAttack",
                        "animation.grandda.LeftAttack"
                }
        );
    }

    @Override
    public void setDefault() {
        setMax_health(MAX_HEALTH);
        setHealth(MAX_HEALTH);
        setPlayerSpeed(SPEED);
        setJumpPower(JUMP_POWER);
        setKnockback(KNOCKBACK);
        setAttack(ATTACK);
        super.getPlayerAnimation().setAnimantionDelay(ANIMANTION_DELAY);
        super.getPlayerAnimation().setPlayerDirection(PlayerDirection.DOWN);
        super.getPlayerAnimation().getModel().setDefault();
    }
}
