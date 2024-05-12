package inter;

import javafx.scene.input.KeyCode;

import java.security.Key;

public interface Moveable {
    public void move(KeyCode keyCode);

    public void stop(KeyCode keyCode);
    public  void attack(KeyCode keyCode);
    public void jump(KeyCode keyCode);

}
