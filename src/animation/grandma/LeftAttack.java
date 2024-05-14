package animation.grandma;

import animation.AnimationData;
import type.PlayerAction;

public class LeftAttack extends AnimationData {
    public LeftAttack() {
        super(
                new String[] {
                        "/player/grandMa/left-attack/1.png",
                        "/player/grandMa/left-attack/2.png",
                        "/player/grandMa/left-attack/3.png",
                        "/player/grandMa/left-attack/4.png",
                        "/player/grandMa/left-attack/5.png"
                },
                190,
                190,
                -45,
                -18,
                PlayerAction.LEFT_ATTACK
        );
    }
}
