package animation.grandma;

import animation.AnimationData;
import type.PlayerAction;

public class RightAttack extends AnimationData {
    public RightAttack() {
        super(
                new String[] {
                        "/player/grandMa/right-attack/1.png",
                        "/player/grandMa/right-attack/2.png",
                        "/player/grandMa/right-attack/3.png",
                        "/player/grandMa/right-attack/4.png",
                        "/player/grandMa/right-attack/5.png"
                },
                190,
                190,
                25,
                -18,
                PlayerAction.RIGHT_ATTACK
        );
    }
}
