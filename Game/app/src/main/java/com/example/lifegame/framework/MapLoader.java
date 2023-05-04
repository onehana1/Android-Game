package com.example.lifegame.framework;

import android.graphics.Canvas;

import com.example.lifegame.game.MainScene;

import java.util.Random;

public class MapLoader implements IGameObject {
    private Random random = new Random();
    private float BottomX, itemX;


    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        BottomX -= MapObject.SPEED * BaseScene.frameTime;

        itemX -= MapObject.SPEED * BaseScene.frameTime;
        while (itemX < Metrics.game_width) {
            coin coinItem = coin.get(coin.getRandomIndex(random), itemX, random.nextInt(7));
            scene.add(MainScene.Layer.item, coinItem);
            itemX += coinItem.getWidth();
        }
    }

    public void draw(Canvas canvas) {}
}
