package animation.grandda;

import animation.AnimationData;
import type.PlayerAction;

public class RightWalk extends AnimationData {
   public RightWalk() {
       super(
               new String[] {
                       "/player/grandDa/right-walk/1.png",
                       "/player/grandDa/right-walk/2.png",
                       "/player/grandDa/right-walk/3.png",
                       "/player/grandDa/right-walk/4.png"
               },
               170,
               170,
               0,
               0,
                PlayerAction.RIGHT_WALK
       );
   }
}
