package animation;

import gui.PlayerModel;
import javafx.application.Platform;
import javafx.scene.image.ImageView;
import type.PlayerAction;
import type.PlayerDirection;
import type.PlayerType;

import java.util.HashMap;
import java.util.Map;

public class PlayerAnimation {
    private PlayerType playerType;
    private PlayerDirection playerDirection;
    private PlayerModel model;

    private double hitblock;

    private Map<PlayerAction, AnimationList> animationMap;

    private Thread animantionThread;
    private int attackCount = 0;

    private int animantionCount = 0;

    private int animantionDelay = 25;

    public  PlayerAnimation(PlayerType playerType) {
        this.setPlayerType(playerType);
        this.animationMap = new HashMap<>();
        this.setPlayerDirection(PlayerDirection.DOWN);
        this.initAnimantionThread();
        this.setHitblock(4);
    }
    public void initModel(ImageView imageView) {
        this.model = new PlayerModel(imageView);
    }

    public void initAnimantionThread() {
        this.animantionThread = new Thread(() -> {
            while (true) {
                Platform.runLater(() -> {
                    this.changeAnimantion();
                });
                try {
                    this.animantionCount++;
                    Thread.sleep(animantionDelay);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        this.animantionThread.start();
    }
    public void changeAnimantion() {
            if (this.model != null && this.model.isLeftAttacking()) {
                if (this.animantionCount > 4) {
                    if (this.attackCount < 5) {
                        this.animantionCount = 0;
                        this.setAnimation(PlayerAction.LEFT_ATTACK);
                        if (this.attackCount == 3) {
                            this.getModel().setAttackLeft(true);
                        }
                        this.attackCount++;
                    } else {
                        this.getModel().setLeftAttacking(false);
                        this.getAnimationMap().get(PlayerAction.LEFT_ATTACK).ResetNode();
                        this.attackCount = 0;
                        this.animantionCount = 100;
                        this.getModel().setMoveDistanceX(this.getModel().getMoveDistanceX() * 2);
                    }
                }
            } else if (this.model != null && this.model.isRightAttacking()) {
                if (this.animantionCount > 4) {
                    if (this.attackCount < 5) {
                        this.animantionCount = 0;
                        this.setAnimation(PlayerAction.RIGHT_ATTACK);
                        if (this.attackCount == 3) {
                            this.getModel().setAttackRight(true);
                        }
                        this.attackCount++;
                    } else {
                        this.getModel().setRightAttacking(false);
                        this.getAnimationMap().get(PlayerAction.RIGHT_ATTACK).ResetNode();
                        this.attackCount = 0;
                        this.animantionCount = 100;
                        this.getModel().setMoveDistanceX(this.getModel().getMoveDistanceX() * 2);
                    }
                }
            }
            if (this.model != null && !this.model.isLeftAttacking() && !this.model.isRightAttacking() ) {
                switch (this.playerDirection) {
                    case DOWN -> {
                        if (this.model.isMovingDown()) {
                            if (this.animantionCount > 4) {
                                this.setAnimation(PlayerAction.DOWN_WALK);
                                this.animantionCount = 0;
                            }
                        } else {
                            if (this.animantionCount > 40) {
                                this.setAnimation(PlayerAction.DOWN_STAY);
                                this.animantionCount = 0;

                            }
                        }
                    }
                    case UP -> {
                        if (this.model.isMovingUp()) {
                            if (this.animantionCount > 4) {
                                this.setAnimation(PlayerAction.UP_WALK);
                                this.animantionCount = 0;
                            }
                        } else {
                            if (this.animantionCount > 40) {
                                this.setAnimation(PlayerAction.UP_STAY);
                                this.animantionCount = 0;
                            }
                        }
                    }
                    case LEFT -> {
                        if (this.model.isMovingLeft()) {
                            if (this.animantionCount > 4) {
                                this.setAnimation(PlayerAction.LEFT_WALK);
                                this.animantionCount = 0;
                            }
                        } else {
                            if (this.animantionCount > 40) {
                                this.setAnimation(PlayerAction.LEFT_STAY);
                                this.animantionCount = 0;
                            }
                        }
                    }
                    case RIGHT ->  {

                        if (this.model.isMovingRight()) {
                            if (this.animantionCount > 4) {
                                this.setAnimation(PlayerAction.RIGHT_WALK);
                                this.animantionCount = 0;
                            }
                        } else {
                            if (this.animantionCount > 40) {
                                this.setAnimation(PlayerAction.RIGHT_STAY);
                                this.animantionCount = 0;
                            }
                        }
                    }
                }
        }
    }
    public void setAnimation(PlayerAction playerAction) {
        Platform.runLater(() -> {
            if (this.model.isShow()) {
                this.model.setModel(this.animationMap.get(playerAction).getCurrent().getImageView());
                this.animationMap.get(playerAction).getNextAnimation();
            }
        });
    }

    public void addPlayerAnimation(PlayerAction playerAction,AnimationList animationList) {
        this.animationMap.put(playerAction,animationList);
    }
    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public PlayerDirection getPlayerDirection() {
        return playerDirection;
    }

    public void setPlayerDirection(PlayerDirection playerDirection) {
        this.playerDirection = playerDirection;
    }

    public PlayerModel getModel() {
        return model;
    }
    public void setModel(PlayerModel playerModel) {
        this.model = playerModel;
    }

    public Map<PlayerAction, AnimationList> getAnimationMap() {
        return animationMap;
    }

    public Thread getAnimantionThread() {
        return animantionThread;
    }

    public int getAnimantionCount() {
        return animantionCount;
    }

    public void setAnimantionCount(int animantionCount) {
        this.animantionCount = animantionCount;
    }

    public double getHitblock() {
        return hitblock;
    }

    public void setHitblock(double hitblock) {
        this.hitblock = hitblock;
    }

    public int getAnimantionDelay() {
        return animantionDelay;
    }

    public void setAnimantionDelay(int animantionDelay) {
        this.animantionDelay = animantionDelay;
    }
}
