package scene;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import player.Player;
import type.PlayerDirection;

abstract public class PlayerOnScene extends Pane {
    private Player player1;
    private Player player2;

    public  PlayerOnScene(Player player1,Player player2) {
        this.setPlayer1(player1);
        this.setPlayer1(player2);
    }

    private boolean player1ThreadRunning = true;

    private boolean player2ThreadRunning =true;

    private Thread player1Thread;
    private Thread player2Thread;

    public void initMoveThread() {
 {
            setPlayer1Thread(new Thread(() -> {
                while (isPlayer1ThreadRunning() && player1.getHealth() > 0) {
                    Platform.runLater(() -> {
                        this.movePlayer(player1);
                        this.attackPlayer(player1,player2);
                    });
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                this.endedScene();
            }));
            getPlayer1Thread().start();
            setPlayer2Thread(new Thread(() -> {
                while (isPlayer2ThreadRunning() && player2.getHealth() > 0) {
                    Platform.runLater(() -> {
                        this.movePlayer(player2);
                        this.attackPlayer(player2,player1);
                    });
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        break;
                    }
                }
                this.endedScene();
            }));
            getPlayer2Thread().start();
        }
    }

    public void stopMoveThread() {
        this.player1ThreadRunning = false;
        this.player2ThreadRunning = false;
        player1.getPlayerAnimation().getModel().setMovingUp(false);
        player1.getPlayerAnimation().getModel().setMovingRight(false);
        player1.getPlayerAnimation().getModel().setMovingDown(false);
        player1.getPlayerAnimation().getModel().setMovingLeft(false);
        player2.getPlayerAnimation().getModel().setMovingUp(false);
        player2.getPlayerAnimation().getModel().setMovingRight(false);
        player2.getPlayerAnimation().getModel().setMovingDown(false);
        player2.getPlayerAnimation().getModel().setMovingLeft(false);
        player1Thread.interrupt();
        player2Thread.interrupt();
    }
    abstract void setPlayerListener();
    abstract void movePlayer(Player player);

    abstract void attackPlayer(Player player,Player opposite);

    abstract void endedScene();

    abstract boolean canPlayerMove(Player player, PlayerDirection playerDirection,double next_move);

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public boolean isPlayer1ThreadRunning() {
        return player1ThreadRunning;
    }

    public void setPlayer1ThreadRunning(boolean player1ThreadRunning) {
        this.player1ThreadRunning = player1ThreadRunning;
    }

    public boolean isPlayer2ThreadRunning() {
        return player2ThreadRunning;
    }

    public void setPlayer2ThreadRunning(boolean player2ThreadRunning) {
        this.player2ThreadRunning = player2ThreadRunning;
    }

    public Thread getPlayer1Thread() {
        return player1Thread;
    }

    public void setPlayer1Thread(Thread player1Thread) {
        this.player1Thread = player1Thread;
    }

    public Thread getPlayer2Thread() {
        return player2Thread;
    }

    public void setPlayer2Thread(Thread player2Thread) {
        this.player2Thread = player2Thread;
    }

}
