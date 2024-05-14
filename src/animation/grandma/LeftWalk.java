package animation.grandma;

import animation.AnimationData;
import type.PlayerAction;

public class LeftWalk extends AnimationData {
    public LeftWalk() {
        super(
                new String[] {
                        "/player/grandMa/left-walk/1.png",
                        "/player/grandMa/left-walk/2.png",
                        "/player/grandMa/left-walk/3.png",
                        "/player/grandMa/left-walk/4.png"
                },
                170,
                170,
                0,
                0,
                PlayerAction.LEFT_WALK
        );
    }
}
