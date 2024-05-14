package animation.grandma;

import animation.AnimationData;
import type.PlayerAction;

public class UpStay extends AnimationData {
    public UpStay() {
        super(
                new String[] {
                        "/player/grandMa/up-stay/1.png",
                        "/player/grandMa/up-stay/2.png"
                },
                170,
                170,
                0,
                0,
                PlayerAction.UP_STAY
        );
    }
}
