package com.example.lifegame.game;

import com.example.lifegame.R;
import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.Button;
import com.example.lifegame.framework.Metrics;
import com.example.lifegame.framework.Sprite;

public class EndingScene extends BaseScene {
    private final float bgX = 8.0f;
    private final float bgY = 4.5f;

    public enum Layer {
        bg,  touch, COUNT
    }


    public EndingScene() {
        Metrics.setGameSize(16.0f, 9.0f);
        initLayers(Layer.COUNT);  // 씬에 1개의 레이어만 필요하다면 변경 가능

        // 엔딩 이미지 추가
        add(Layer.bg, new Sprite(R.mipmap.smoke_ending, bgX, bgY, 16.0f, 9.0f));


    }

    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }
}
