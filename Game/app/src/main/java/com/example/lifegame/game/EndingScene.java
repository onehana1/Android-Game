package com.example.lifegame.game;

import static com.example.lifegame.game.MainScene.Layer.player;

import com.example.lifegame.R;
import com.example.lifegame.app.TitleActivity;
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

        MainScene scene = (MainScene) BaseScene.getTopScene();
        int a = scene.score.getMoneyScore();
        System.out.println(a);


        // 엔딩 이미지 추가
        if(a<200){
            add(Layer.bg, new Sprite(R.mipmap.poorending, bgX, bgY, 16.0f, 9.0f));
        }
        else if(a<700){
            add(Layer.bg, new Sprite(R.mipmap.normalending, bgX, bgY, 16.0f, 9.0f));
        }
        else {
            add(Layer.bg, new Sprite(R.mipmap.richending, bgX, bgY, 16.0f, 9.0f));

        }




        // 버튼 추가
        Button backButton = new Button(R.mipmap.regame , R.mipmap.regame, 13.0f, 2.0f, 4.0f, 2.0f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    returnToTitle();
                }
                return true;
            }
        });
        backButton.setActivation(true);
        add(Layer.touch, backButton);
    }


    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }

    private void returnToTitle() {
        // EndingScene을 종료하고 TitleActivity로 돌아감
        finishActivity();
    }
}
