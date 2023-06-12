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
            if(coinCount < 15) {
                float randomNumber = random.nextFloat() * (7 - 3) + 3;
                int a = coin.getRandomCoinIndex(random, age);
                coin coinItem = coin.get(a, 30.0f + randomNumber, random.nextInt(4) + 1.8f);
                coinItem.setcoinindex(a+1);
                //System.out.println(a+1);
                scene.add(coinItem.getLayer(), coinItem);
                itemX += coinItem.getWidth();
                coinItem.setSpeed(randomNumber);
                coinCount++;

            }

            else {
                break;
            }
        }




    }


    public void draw(Canvas canvas) {}
}
