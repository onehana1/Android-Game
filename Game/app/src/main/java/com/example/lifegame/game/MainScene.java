package com.example.lifegame.game;

import com.example.lifegame.R;
import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.MapLoader;
import com.example.lifegame.framework.Metrics;
import com.example.lifegame.framework.coin;

import java.util.Random;

public class MainScene extends BaseScene {
    public enum Layer {
        bg, item, player, controller, COUNT
        }

    public MainScene() {
        Metrics.setGameSize(16.0f, 9.0f);
        initLayers(Layer.COUNT);

       // add(new HorzScrollBackground(R.mipmap.school, 0.2f));
        add(Layer.bg, new HorzScrollBackground(R.mipmap.school, 0.2f));

        add(Layer.player, new Player());
        add(Layer.controller, new MapLoader());
    }





}
