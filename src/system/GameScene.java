package system;

import javafx.scene.layout.Pane;
import type.constant.Settings;
import type.interfaces.SceneLogic;

abstract public class GameScene extends Pane {
    public SceneLogic getScript() {
        return script;
    }

    public void setScript(SceneLogic script) {
        this.script = script;
    }

    private SceneLogic script;

    public GameScene(SceneLogic script) {
        this.setScript(script);

        setPrefWidth(Settings.DEFAULT_SCENE_WIDTH);
        setPrefHeight(Settings.DEFAULT_SCENE_HEIGHT);

        this.initscene();
        this.setPlayerListener();
    }

    public abstract void initscene();

    public abstract void setPlayerListener();
}