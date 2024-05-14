package animation.grandda;

import animation.AnimationData;
import type.PlayerAction;

public class DownStay extends AnimationData {
    public DownStay() {
        super(
                new String[] {
                        "/player/grandDa/down-stay/1.png",
                        "/player/grandDa/down-stay/2.png"
                },
                170,
                170,
                0,
                0,
                PlayerAction.DOWN_STAY
        );
    }
}
