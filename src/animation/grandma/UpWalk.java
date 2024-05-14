package animation.grandma;

import animation.AnimationData;
import type.PlayerAction;

public class UpWalk extends AnimationData {
    public UpWalk() {
       super(
               new String[] {
                       "/player/grandMa/up-walk/1.png",
                       "/player/grandMa/up-walk/2.png",
                       "/player/grandMa/up-walk/3.png",
                       "/player/grandMa/up-walk/4.png"
               },
               170,
               170,
               0,
               0,
               PlayerAction.UP_WALK
       );
    }
}
