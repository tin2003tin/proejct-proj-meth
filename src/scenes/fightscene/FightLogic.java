package scenes.fightscene;

import gui.PlayerModel;
import main.Main;
import player.Player;
import type.PlayerDirection;
import type.constant.Settings;
import type.interfaces.SceneLogic;

public class FightLogic implements SceneLogic {
    public void setView(FightScene view) {
        this.view = view;
    }

    private FightScene view;
    @Override
    public void update() {
        movePlayer(Main.player1);
        movePlayer(Main.player2);
        attackPlayer(Main.player1, Main.player2);
        attackPlayer(Main.player2, Main.player1);
    }
    private void movePlayer(Player player) {
        PlayerModel playerModel = player.getPlayerAnimation().getModel();
        double next_move =  player.getPlayerAnimation().getModel().getMoveDistanceX() * player.getPlayerSpeed();
        if (playerModel.isMovingLeft() && canPlayerMove(player, PlayerDirection.LEFT,next_move) ) {
            playerModel.setLocation(playerModel.getX() - playerModel.getMoveDistanceX() * player.getPlayerSpeed(), playerModel.getY());
        }
        if (playerModel.isMovingRight() && canPlayerMove(player, PlayerDirection.RIGHT,next_move)) {
            playerModel.setLocation(playerModel.getX() + playerModel.getMoveDistanceX() * player.getPlayerSpeed(), playerModel.getY());
        }
        if (player.getPlayerAnimation().getModel().isOnGround()) {
            if (playerModel.isJump()) {
                playerModel.setOnGround(false);
                playerModel.setInitialSpeed(player.getJumpPower() * playerModel.getMoveDistanceY());
            }
        } else {
            if (playerModel.getY() <= Settings.GROUND_HEIGHT) {
                playerModel.setY(playerModel.getY() - (playerModel.getInitialSpeed()));
                playerModel.decreaseSpeed(1);
            } else {
                playerModel.setY(Settings.GROUND_HEIGHT);
                playerModel.setOnGround(true);

            }
        }
        if (playerModel.getForcePX() > 0) {
            if (canPlayerMove(player, PlayerDirection.RIGHT,playerModel.getForcePX())) {
                playerModel.setX(playerModel.getX() + playerModel.getForcePX());
            } else {
                playerModel.setForcePX(0);
            }
            playerModel.setForcePX(playerModel.getForcePX() - 4);
        }
        if (playerModel.getForceNX() > 0) {
            if (canPlayerMove(player, PlayerDirection.LEFT,playerModel.getForceNX())) {
                playerModel.setX(playerModel.getX() - playerModel.getForceNX());
            } else {
                playerModel.setForceNX(0);
            }
            playerModel.setForceNX(playerModel.getForceNX() - 4);
        }
    }
    private boolean canPlayerMove(Player player, PlayerDirection playerDirection,double next_move) {
        double hitblock = player.getPlayerAnimation().getHitblock();
        double player_X = player.getPlayerAnimation().getModel().getX();
        double player_y = player.getPlayerAnimation().getModel().getY();
        switch (playerDirection) {
            case UP -> {

            }
            case DOWN ->  {
                return  true;
            }
            case RIGHT -> {
                if (player_X + next_move > Settings.DEFAULT_SCENE_WIDTH - 180) {
                    return  false;
                }
            }
            case LEFT ->  {
                if (player_X- next_move < 10) {
                    return  false;
                }
            }
        }
        return true;
    }
    private void attackPlayer(Player player, Player opposite) {
        PlayerModel playerModel = player.getPlayerAnimation().getModel();
        PlayerModel oppositeModel = opposite.getPlayerAnimation().getModel();

        if (playerModel.isAttackLeft()) {
            double distanceX = playerModel.getX() - oppositeModel.getX();
            double distanceY =  oppositeModel.getY() - playerModel.getY();
            if (distanceX > 30 && distanceX < 150 && distanceY > -100 && distanceY < 100) {

                oppositeModel.setForceNX(10 * player.getKnockback());
                opposite.setHealth((int) (opposite.getHealth() - player.getAttack()));
                view.inithealthBars();
            }
            playerModel.setAttackLeft(false);
        } else if (playerModel.isAttackRight())
        {
            double distanceX = oppositeModel.getX() - playerModel.getX();
            double distanceY =  oppositeModel.getY() - playerModel.getY();

            if (distanceX > 30 && distanceX < 150 && distanceY > -100 && distanceY <= 100) {

                oppositeModel.setForcePX(10 * player.getKnockback());
                opposite.setHealth((int) (opposite.getHealth() - player.getAttack()));
                view.inithealthBars();
            }
            playerModel.setAttackRight(false);
        }
    }
}

