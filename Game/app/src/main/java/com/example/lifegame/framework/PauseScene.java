package com.example.lifegame.framework;

import com.example.lifegame.R;

public class PauseScene extends BaseScene {

    private float angle;

    public enum Layer {
        bg, title, touch, COUNT
    }

    Button playButton;
    public PauseScene() {
        initLayers(Layer.COUNT);
        add(Layer.bg, new Sprite(R.mipmap.blackbg, 8.0f, 4.5f, 16.f, 9.f));
        add(Layer.bg, new Sprite(R.mipmap.paused, 8.0f, 1.2f, 7, 1.8f));

        playButton = new Button(R.mipmap.play, R.mipmap.play,8.0f, 4.5f, 4f, 4f, new Button.Callback() {
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    popScene();
                }
                return false;
            }
        });
        playButton.setActivation(true);
        add(Layer.touch, playButton);

    }

    public boolean isTransparent() {
        return true;
    }



    @Override
    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }
}