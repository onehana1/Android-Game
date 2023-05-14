package com.example.lifegame.game;

import static android.content.ContentValues.TAG;

import android.util.Log;

import com.example.lifegame.R;
import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.Button;
import com.example.lifegame.framework.MapLoader;
import com.example.lifegame.framework.MapLoaderObject;
import com.example.lifegame.framework.Metrics;
import com.example.lifegame.framework.Sprite;
import com.example.lifegame.framework.coin;

import java.util.Random;

public class MainScene extends BaseScene {
    private final Player player;
    private final float bgspeed = 1.0f;

    public enum Layer {
        bg, item,choiceobj, player, ui,touch, controller, COUNT
        }

    public MainScene() {
        Metrics.setGameSize(16.0f, 9.0f);
        initLayers(Layer.COUNT);
        player = new Player();

       // add(new HorzScrollBackground(R.mipmap.school, 0.2f));
        add(Layer.bg, new HorzScrollBackground(R.mipmap.school, bgspeed, player));

        add(Layer.player, player);

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

        add(Layer.touch, new Button(R.mipmap.hobby_1, 1.0f, 8.3f, 1.0f, 1.0f, new Button.Callback() {
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {

                }
                return true;
            }
        }));

        add(Layer.touch, new Button(R.mipmap.hobby_2, 2.0f, 8.3f, 1.0f, 1.0f, new Button.Callback() {
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {

                }
                return true;
            }
        }));

        add(Layer.touch, new Button(R.mipmap.hobby_3, 3.0f, 8.3f, 1.0f, 1.0f, new Button.Callback() {
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {

                }
                return true;
            }
        }));


        add(Layer.controller, new MapLoader(player));
        add(Layer.controller, new MapLoaderObject(player));
        add(Layer.controller, new CollisionChecker(player));
    }

    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }



}
