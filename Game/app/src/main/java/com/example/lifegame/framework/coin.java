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

    float speed = 4.0f;

    private int coinindex;

    public enum CoinType {
        C1, C2, C3, C4, C5, C6, C7
    }

    public int index;
    protected int[] SOUND_IDS = {
            R.raw.coin,
    };
    public int soundId() {
        return SOUND_IDS[index % SOUND_IDS.length];
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


    public coin() {
        setBitmapResource(R.mipmap.coins);
        width = height = 1;
        this.coinindex = 0;
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


    public int getCoinindex() {
        return coinindex;
    }


    public void setcoinindex(int index){
        coinindex = index;
    }
    public static int getRandomCoinIndex(Random random, int age) {
        // 플레이어의 나이에 따라 코인 인덱스 선택
        switch (age) {
            case 0:
                return random.nextInt(1);
            case 1:
//                int a1 = random.nextInt(1); //
//                //System.out.println("coinindex : " +coinindex);
//                return a1;
                return -1;
            case 2:
                int a2 = random.nextInt(3) + 1; //학생은 취미
                return a2;
            case 3:
                int a3;
                if (random.nextInt(10) < 7) {
                   return a3 = 0;
                } else {
                   return a3 = 6;
                }
            case 4:
                int a4 = random.nextInt(3) + 1; //집은 취미
                return a4;
            case 5:
                int a5;
                if (random.nextInt(10) < 7) return a5 = 0;
                else return a5 = 6;

            case 6:
                int a6 = random.nextInt(2) * 4; //시계랑 앨범
                return a6;

            default:
               // System.out.println("안오면 좋겠어");
                return -1;
        }
    }

    private void init(int index, float left, float top) {
        this.coinindex = index + 1;
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
            return MainScene.Layer.item_1;
    }

    public void update() {
        float dx = -speed * BaseScene.frameTime;
        // System.out.println("speed" + speed);
        dstRect.offset(dx, 0);

        if (dstRect.right < 0){
            BaseScene.getTopScene().remove(getLayer(), this);
        }
    }
}

