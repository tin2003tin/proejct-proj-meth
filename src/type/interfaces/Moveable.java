package type.interfaces;

import javafx.scene.input.KeyCode;

public interface Moveable {
    public void move(KeyCode keyCode);

    public void stop(KeyCode keyCode);
    public  void attack(KeyCode keyCode);
    public void jump(KeyCode keyCode);

}
