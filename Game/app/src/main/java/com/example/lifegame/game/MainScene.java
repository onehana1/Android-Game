package com.example.lifegame.game;

import com.example.lifegame.R;
import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.Button;
import com.example.lifegame.framework.MapLoader;
import com.example.lifegame.framework.MapLoaderObject;
import com.example.lifegame.framework.Metrics;

public class MainScene extends BaseScene {
    private final Player player;
    private final float bgspeed = 1.0f;

    public enum Layer {
        bg, item_1, choiceobj, player, ui,touch, controller, COUNT
        }

    public enum Choiced {
       freind, h_study, h_paint, h_music, COUNT
    }
    public Score score;
    int[] imageResources = {R.mipmap.playground1, R.mipmap.school1, R.mipmap.company1, R.mipmap.house1, R.mipmap.house, R.mipmap.house2, R.mipmap.house_2,R.mipmap.house3, R.mipmap.house_3 };

    public MainScene() {
        Metrics.setGameSize(16.0f, 9.0f);
        initLayers(Layer.COUNT);
        player = new Player();
        add(Layer.player, player);

       // add(new HorzScrollBackground(R.mipmap.school, 0.2f));
        HorzScrollBackground background = new HorzScrollBackground(imageResources, bgspeed, player);
        add(Layer.bg, background);

        // 플레이어의 나이 설정
        background.setPlayerAgeBasedOnCurrentImage();

        add(Layer.touch, new Button(R.mipmap.jump, 14.5f, 7.7f, 3.0f, 2.0f, new Button.Callback() {
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    player.jump();
                }
                return true;
            }
        }));

        add(Layer.touch, new Button(R.mipmap.choice, 11.5f, 7.7f, 3.0f, 2.0f, new Button.Callback() {
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    player.choice();
                }
                return true;
            }
        }));

        add(Layer.touch, new Button(R.mipmap.hobby_1, 1.0f, 8.4f, 1.0f, 1.0f, new Button.Callback() {
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {

                }
                return true;
            }
        }));

        add(Layer.touch, new Button(R.mipmap.hobby_2, 2.3f, 8.4f, 1.0f, 1.0f, new Button.Callback() {
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {

                }
                return true;
            }
        }));

        add(Layer.touch, new Button(R.mipmap.hobby_3, 3.5f, 8.4f, 1.0f, 1.0f, new Button.Callback() {
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {

                }
                return true;
            }
        }));

        score = new Score(R.mipmap.gold_number, 15, 0.2f, 0.5f);
        score.setScore(100);
        add(Layer.ui, score);

        add(Layer.controller, new MapLoader(player));
        add(Layer.choiceobj, new MapLoaderObject(player));

        add(Layer.controller, new CollisionChecker(player));
    }

    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }



}
