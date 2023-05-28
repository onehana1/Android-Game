package com.example.lifegame.framework;

import android.graphics.Canvas;

import com.example.lifegame.game.MainScene;
import com.example.lifegame.game.Player;

import java.util.Random;

public class MapLoader implements IGameObject {
    private final Player player;
    private Random random = new Random();
    private float BottomX, itemX;

    private final float[] ratios = {4f, 5f, 6f}; // 각 지점의 비율
    private int ratioIndex = 0; // 비율 인덱스

    private ChoiceObj choiceObj;
    boolean musicSpawned= false;
    boolean studySpawned= false;
    boolean artSpawned = false;

    public MapLoader(Player player) {
        this.player = player;
    }


    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();

        BottomX -= MapObject.SPEED * BaseScene.frameTime;
        itemX -= MapObject.SPEED * BaseScene.frameTime;


        while (itemX < Metrics.game_width) {
            //coin coinItem = coin.get(coin.getRandomIndex(random), itemX, random.nextInt(8));
            coin coinItem = coin.get(coin.getCoinIndex(random, player.getAge()), itemX, random.nextInt(8));
            scene.add(MainScene.Layer.item, coinItem);
            itemX += coinItem.getWidth();
        }

    }

    public void draw(Canvas canvas) {}
}
