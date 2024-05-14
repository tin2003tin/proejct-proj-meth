package animation.grandma;

import animation.AnimationData;
import type.PlayerAction;

public class RightStay extends AnimationData {
    public RightStay() {
       super(
               new String[] {
                       "/player/grandMa/right-stay/1.png",
                       "/player/grandMa/right-stay/2.png"
               },
               170,
               170,
               0,
               0,
                PlayerAction.RIGHT_STAY
       );
    }
}
