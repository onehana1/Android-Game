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
