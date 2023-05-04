package com.example.lifegame.game;

import com.example.lifegame.R;
import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.Metrics;

public class MainScene extends BaseScene {
    public enum Layer {
        bg, player, COUNT
        }

    public MainScene() {
        Metrics.setGameSize(16.0f, 9.0f);
        initLayers(Layer.COUNT);

       // add(new HorzScrollBackground(R.mipmap.school, 0.2f));
        add(Layer.bg, new HorzScrollBackground(R.mipmap.school, 0.2f));
        add(Layer.player, new Player());


    }



}
