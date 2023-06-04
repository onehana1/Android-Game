package com.example.lifegame.framework;

import android.graphics.Canvas;

import com.example.lifegame.game.MainScene;
import com.example.lifegame.game.Player;

import java.util.Random;

public class MapLoader implements IGameObject {
    private final Player player;
    private Random random = new Random();
    private float BottomX, itemX;

    private static int coinCount = 0; // 생성된 코인 수

    public MapLoader(Player player) {
        this.player = player;
    }
    public static void resetCoinCount() {
        coinCount = 0; // 코인 생성 수 초기화
    }

    int maxCoinCount = 10; // 플레이어 나이에 따른 최대 코인 생성 수



    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();

        BottomX -= MapObject.SPEED * BaseScene.frameTime;
        itemX -= MapObject.SPEED * BaseScene.frameTime;


        int age = player.getAge();

        // 플레이어의 나이가 변경되었을 때 코인 생성 수 초기화
        if (player.isAgeChanged()) {
            coinCount = 0;
            player.resetAgeChanged();

        }

        while (itemX < Metrics.game_width) {
            coin coinItem = coin.get(coin.getCoinIndex(random, age), itemX, random.nextInt(8));
            scene.add(MainScene.Layer.item, coinItem);
            itemX += coinItem.getWidth();
            coinCount++;
           // System.out.println(coinCount); // 새로운 줄로 이동하여 출력
        }

    }


    public void draw(Canvas canvas) {}
}
