package animation;

import type.PlayerAction;

public abstract class AnimationData {
    public final String[] frames;
    public final int width;
    public final int height;
    public final int offsetX;
    public final int offsetY;
    public final PlayerAction actionType;

    public AnimationData(String[] frames, int width, int height, int offsetX, int offsetY, PlayerAction type) {
        this.frames = frames;
        this.width = width;
        this.height = height;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.actionType = type;
    }
}
