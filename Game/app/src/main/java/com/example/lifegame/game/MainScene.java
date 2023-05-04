package com.example.lifegame.game;

import com.example.lifegame.R;
import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.Metrics;
import com.example.lifegame.framework.coin;

import java.util.Random;

public class MainScene extends BaseScene {
    public enum Layer {
        bg, item, player, COUNT
        }

    public MainScene() {
        Metrics.setGameSize(16.0f, 9.0f);
        initLayers(Layer.COUNT);

       // add(new HorzScrollBackground(R.mipmap.school, 0.2f));
        add(Layer.bg, new HorzScrollBackground(R.mipmap.school, 0.2f));
        Random r = new Random();
        for (int i = 0, x = 10; i < coin.COIN_COUNT; i++, x++) {
            int jellyIndex = r.nextInt(coin.COIN_COUNT);
            int y = r.nextInt(8);
            add(Layer.item, coin.get(jellyIndex, x, y));
        }
        add(Layer.player, new Player());


    }



}
