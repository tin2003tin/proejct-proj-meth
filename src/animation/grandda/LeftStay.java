package animation.grandda;

import animation.AnimationData;
import type.PlayerAction;

public class LeftStay extends AnimationData {
    public LeftStay() {
        super(
                new String[] {
                        "/player/grandDa/left-stay/1.png",
                        "/player/grandDa/left-stay/2.png"
                },
                170,
                170,
                0,
                0,
                PlayerAction.LEFT_STAY
        );
    }
}
