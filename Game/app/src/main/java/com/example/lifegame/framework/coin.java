package com.example.lifegame.framework;


import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.lifegame.R;
import com.example.lifegame.game.MainScene;

import java.util.Random;

public class coin extends MapObject{
    public static final int COIN_COUNT = 7;
    private static final int ITEMS_IN_A_ROW = 7;
    private static final int SIZE = 40;
    private static final int BORDER = 1;
    protected Rect srcRect = new Rect();

    float speed = 1.0f;

    public enum CoinType {
        C1, C2, C3, C4, C5, C6, C7
    }


    public coin() {
        setBitmapResource(R.mipmap.coins);
        width = height = 1;
    }

    public static coin get(int index, float left, float top) {
        coin item = (coin) RecycleBin.get(coin.class);
        if (item == null) {
            item = new coin();
        }
        item.init(index, left, top);
        return item;
    }

    public static int getRandomIndex(Random random) {
        return random.nextInt(COIN_COUNT);
    }

    public static int getCoinIndex(Random random, int age) {
        // 플레이어의 나이에 따라 코인 인덱스 선택
        switch (age) {
            case 0:
                return random.nextInt(3);
            case 1:
                return -1; // 나이가 1이면 코인이 나오지 않도록 -1을 반환
            case 2:
                return random.nextInt(3) + 2;
            case 3:
                return random.nextInt(3) + 2;
            case 4:
                return random.nextInt(2) * 6; //1,7
            case 5:
                return random.nextInt(2) + 5; // 5, 6
            default:
                return random.nextInt(1);
        }
    }

    private void init(int index, float left, float top) {
        setSrcRect(index);
        dstRect.set(left, top, left + width, top + height);
    }

    private void setSrcRect(int index) {
        int x = index % ITEMS_IN_A_ROW;
        int y = index / ITEMS_IN_A_ROW;
        int left = x * (SIZE + BORDER) + BORDER;
        int top = 0;
        srcRect.set(left, top, left + SIZE, top + SIZE);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);
    }

    protected MainScene.Layer getLayer() {
        return MainScene.Layer.item;
    }
}
