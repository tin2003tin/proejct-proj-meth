package animation.grandda;

import animation.AnimationData;
import type.PlayerAction;

public class DownWalk extends AnimationData {
    public DownWalk() {
       super(
               new String[] {
                       "/player/grandDa/down-walk/1.png",
                       "/player/grandDa/down-walk/2.png",
                       "/player/grandDa/down-walk/3.png",
                       "/player/grandDa/down-walk/4.png"
               },
               170,
               170,
               0,
               0,
                PlayerAction.DOWN_WALK
       );
    }
}
