package animation.grandma;

import animation.AnimationData;
import type.PlayerAction;

public class DownWalk extends AnimationData {
    public DownWalk() {
       super(
               new String[] {
                       "/player/grandMa/down-walk/1.png",
                       "/player/grandMa/down-walk/2.png",
                       "/player/grandMa/down-walk/3.png",
                       "/player/grandMa/down-walk/4.png"
               },
               170,
               170,
               0,
               0,
                PlayerAction.DOWN_WALK
       );
    }
}
