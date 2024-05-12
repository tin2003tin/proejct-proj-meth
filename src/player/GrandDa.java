// Player.java
package player;

import animation.AnimationList;
import gui.PlayerModel;
import inter.Moveable;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import type.PlayerAction;
import type.PlayerDirection;
import type.PlayerType;

public class GrandDa extends Player implements Moveable  {

    public static int MAX_HEALTH = 20;
    public static double SPEED = 1;
    public static double ATTACK = 1;
    public static double JUMP_POWER = 1.5;
    public static double KNOCKBACK = 3;

    public static int ANIMANTION_DELAY = 25;
    public GrandDa() {
       super(PlayerType.GrandDa,MAX_HEALTH,MAX_HEALTH,SPEED,JUMP_POWER,KNOCKBACK);
       this.initAnimation();
        super.getPlayerAnimation().initModel(super.getPlayerAnimation().getAnimationMap().get(PlayerAction.DOWN_STAY).getCurrent().getImageView());
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
     void initAnimation() {
        AnimationList downStayList = new AnimationList();
        downStayList.addAnimation("res/player/grandDa/down-stay/1.png",170,170,0,0);
        downStayList.addAnimation("res/player/grandDa/down-stay/2.png",170,170,0,0);
        super.getPlayerAnimation().addPlayerAnimation(PlayerAction.DOWN_STAY,downStayList);
        AnimationList downWalkList = new AnimationList();
        downWalkList.addAnimation("res/player/grandDa/down-walk/1.png",170,170,0,0);
        downWalkList.addAnimation("res/player/grandDa/down-walk/2.png",170,170,0,0);
        downWalkList.addAnimation("res/player/grandDa/down-walk/3.png",170,170,0,0);
        downWalkList.addAnimation("res/player/grandDa/down-walk/4.png",170,170,0,0);
        super.getPlayerAnimation().addPlayerAnimation(PlayerAction.DOWN_WALK,downWalkList);

        AnimationList upStayList = new AnimationList();
        upStayList.addAnimation("res/player/grandDa/up-stay/1.png",170,170,0,0);
        upStayList.addAnimation("res/player/grandDa/up-stay/2.png",170,170,0,0);
        super.getPlayerAnimation().addPlayerAnimation(PlayerAction.UP_STAY,upStayList);
        AnimationList upWalkList = new AnimationList();
        upWalkList.addAnimation("res/player/grandDa/up-walk/1.png",170,170,0,0);
        upWalkList.addAnimation("res/player/grandDa/up-walk/2.png",170,170,0,0);
        upWalkList.addAnimation("res/player/grandDa/up-walk/3.png",170,170,0,0);
        upStayList.addAnimation("res/player/grandDa/up-walk/4.png",170,170,0,0);
        super.getPlayerAnimation().addPlayerAnimation(PlayerAction.UP_WALK,upWalkList);

        AnimationList leftStayList = new AnimationList();
        leftStayList.addAnimation("res/player/grandDa/left-stay/1.png",170,170,0,0);
        leftStayList.addAnimation("res/player/grandDa/left-stay/2.png",170,170,0,0);
        super.getPlayerAnimation().addPlayerAnimation(PlayerAction.LEFT_STAY,leftStayList);
        AnimationList leftWalkList = new AnimationList();
        leftWalkList.addAnimation("res/player/grandDa/left-walk/1.png",170,170,0,0);
        leftWalkList.addAnimation("res/player/grandDa/left-walk/2.png",170,170,0,0);
        leftWalkList.addAnimation("res/player/grandDa/left-walk/3.png",170,170,0,0);
        leftWalkList.addAnimation("res/player/grandDa/left-walk/4.png",170,170,0,0);
        super.getPlayerAnimation().addPlayerAnimation(PlayerAction.LEFT_WALK,leftWalkList);

        AnimationList rightStayList = new AnimationList();
        rightStayList.addAnimation("res/player/grandDa/right-stay/1.png",170,170,0,0);
        rightStayList.addAnimation("res/player/grandDa/right-stay/2.png",170,170,0,0);
        super.getPlayerAnimation().addPlayerAnimation(PlayerAction.RIGHT_STAY,rightStayList);
        AnimationList rightWalkList = new AnimationList();
        rightWalkList.addAnimation("res/player/grandDa/right-walk/1.png",170,170,0,0);
        rightWalkList.addAnimation("res/player/grandDa/right-walk/2.png",170,170,0,0);
        rightWalkList.addAnimation("res/player/grandDa/right-walk/3.png",170,170,0,0);
        rightWalkList.addAnimation("res/player/grandDa/right-walk/4.png",170,170,0,0);

        super.getPlayerAnimation().addPlayerAnimation(PlayerAction.RIGHT_WALK,rightWalkList);
        AnimationList rightAttackList = new AnimationList();
        rightAttackList.addAnimation("res/player/grandDa/right-attack/1.png",190,190,25,-18);
        rightAttackList.addAnimation("res/player/grandDa/right-attack/2.png",190,190,25,-18);
        rightAttackList.addAnimation("res/player/grandDa/right-attack/3.png",190,190,25,-18);
        rightAttackList.addAnimation("res/player/grandDa/right-attack/4.png",190,190,25,-18);
        rightAttackList.addAnimation("res/player/grandDa/right-attack/5.png",190,190,25,-18);
        super.getPlayerAnimation().addPlayerAnimation(PlayerAction.RIGHT_ATTACK,rightAttackList);

        AnimationList leftAttackList = new AnimationList();
        leftAttackList.addAnimation("res/player/grandDa/left-attack/1.png",190,190,-45,-18);
        leftAttackList.addAnimation("res/player/grandDa/left-attack/2.png",190,190,-45,-18);
        leftAttackList.addAnimation("res/player/grandDa/left-attack/3.png",190,190,-45,-18);
        leftAttackList.addAnimation("res/player/grandDa/left-attack/4.png",190,190,-45,-18);
        leftAttackList.addAnimation("res/player/grandDa/left-attack/5.png",190,190,-45,-18);
        super.getPlayerAnimation().addPlayerAnimation(PlayerAction.LEFT_ATTACK,leftAttackList);
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
