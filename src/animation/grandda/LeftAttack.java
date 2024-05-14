package animation.grandda;

import animation.AnimationData;
import type.PlayerAction;

public class LeftAttack extends AnimationData {
    public LeftAttack() {
        super(
                new String[] {
                        "/player/grandDa/left-attack/1.png",
                        "/player/grandDa/left-attack/2.png",
                        "/player/grandDa/left-attack/3.png",
                        "/player/grandDa/left-attack/4.png",
                        "/player/grandDa/left-attack/5.png"
                },
                190,
                190,
                -45,
                -18,
                PlayerAction.LEFT_ATTACK
        );
    }
}
