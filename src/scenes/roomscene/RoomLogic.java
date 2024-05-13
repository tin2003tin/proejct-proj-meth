package scenes.roomscene;

import gui.PlayerModel;
import main.Main;
import player.Player;
import scenes.fightscene.FightScene;
import system.SceneManager;
import type.GameTime;
import type.PlayerDirection;
import type.constant.Settings;
import type.interfaces.SceneLogic;

public class RoomLogic implements SceneLogic {
    @Override
    public void update() {
        movePlayer(Main.player1);
        movePlayer(Main.player2);
        checkSleep();
        checkgameStart();
    }
    private void movePlayer(Player player) {
        double next_move = player.getPlayerAnimation().getModel().getMoveDistanceX() * player.getPlayerSpeed();
        PlayerModel playerModel = player.getPlayerAnimation().getModel();
        double directX = 0;
        double directY = 0;
        if (playerModel.isMovingUp() && canPlayerMove(player, PlayerDirection.UP,next_move)) {
            directY = -1;
        }
        if (playerModel.isMovingDown() && canPlayerMove(player, PlayerDirection.DOWN,next_move))  {
            directY = 1;
        }
        if (playerModel.isMovingLeft() && canPlayerMove(player, PlayerDirection.LEFT,next_move)) {
            directX = -1;
        }
        if (playerModel.isMovingRight() && canPlayerMove(player, PlayerDirection.RIGHT,next_move)) {
            directX = 1;
        }

        double norm = Math.sqrt(directX * directX + directY * directY);

        if (norm != 0) {
            double newX = playerModel.getX() + directX / norm * next_move;
            double newY = playerModel.getY() + directY / norm * next_move;

            playerModel.setLocation(newX, newY);
        }
    }
    private void checkSleep() {
        double player1_X = Main.player1.getPlayerAnimation().getModel().getX();
        double player1_y = Main.player1.getPlayerAnimation().getModel().getY();
        double player2_X = Main.player2.getPlayerAnimation().getModel().getX();
        double player2_y = Main.player2.getPlayerAnimation().getModel().getY();
        if (player1_X >= 220 && player2_X >= 680 ) {
            if (player1_y < 230 && player2_y < 230) {
                if (Main.gameTime == GameTime.NightTime) {
                    Main.gameTime = GameTime.DayLight;
                } else {
                    Main.gameTime = GameTime.NightTime;
                }
                Main.player1.setDefault();
                Main.player2.setDefault();
                SceneManager.moveScene(new RoomScene());
            }
        }
    }
    private void checkgameStart() {
        double player1_X = Main.player1.getPlayerAnimation().getModel().getX();
        double player1_y = Main.player1.getPlayerAnimation().getModel().getY();
        double player2_X = Main.player2.getPlayerAnimation().getModel().getX();
        double player2_y = Main.player2.getPlayerAnimation().getModel().getY();
        if (player1_X >= 80 && player1_X <= 180 && player2_X >= 540 && player2_X <= 640 ) {
            if (player1_y >= 310 && player2_y >= 310) {
                SceneManager.moveScene(new FightScene());
            }
        }
    }

    private boolean canPlayerMove(Player player, PlayerDirection playerDirection, double next_move) {
        double hitblock = player.getPlayerAnimation().getHitblock();
        double player_X = player.getPlayerAnimation().getModel().getX();
        double player_y = player.getPlayerAnimation().getModel().getY();

        switch (playerDirection) {
            case UP -> {
                if (player_y + hitblock - next_move  < 160) {
                    return false;
                }
                if (player_X + hitblock < 240 && player_X - hitblock > 120 &&   player_y - next_move < 210) {
                    return  false;
                }

                if (player_X + hitblock < 240 + 480 && player_X - hitblock > 120+ 490  &&   player_y - next_move < 210) {
                    return  false;
                }

            }
            case DOWN ->  {
                if (player_y + hitblock + next_move > 330) {
                    return false;
                }

            }
            case RIGHT -> {
                if (player_X + next_move > Settings.DEFAULT_SCENE_WIDTH-170) {
                    return false;
                }
                if (player_X - next_move < 230 && player_X + next_move > 230 ) {
                    return  false;
                }

                if (player_X - next_move < 370 && player_X + next_move > 370 ) {
                    return  false;
                }
                if (player_X -next_move < 105 && player_X + next_move > 100 && player_y < 220) {
                    return  false;
                }

                if (player_X -next_move < 580 && player_X + next_move > 575 && player_y < 210) {
                    return  false;
                }


                if (player_X - next_move < 690 && player_X + next_move > 690 ) {
                    return  false;
                }
            }
            case LEFT ->  {
                if (player_X - next_move < 50) {
                    return  false;
                }

                if (player_X + next_move > 500 && player_X - next_move < 500 ) {
                    return  false;
                }
            }
        }
        return true;
    }
}
