package animation.grandda;

import animation.AnimationData;
import type.PlayerAction;

public class UpWalk extends AnimationData {
    public UpWalk() {
       super(
               new String[] {
                       "/player/grandDa/up-walk/1.png",
                       "/player/grandDa/up-walk/2.png",
                       "/player/grandDa/up-walk/3.png",
                       "/player/grandDa/up-walk/4.png"
               },
               170,
               170,
               0,
               0,
                PlayerAction.UP_WALK
       );
    }
}
