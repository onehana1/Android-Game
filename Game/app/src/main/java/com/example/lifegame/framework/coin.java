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
                return random.nextInt(3);
            case 1:
                int a1 = random.nextInt(2) * 6; //
                //System.out.println("coinindex : " +coinindex);
                return a1;
                //return -1;
            case 2:
                int a2 = random.nextInt(2) * 6; //1,7
                return a2;
            case 3:
                int a3 = random.nextInt(3) + 2;
                return a3;
            case 4:
                int a4 = random.nextInt(2) * 6; //1,7
                return a4;

            case 5:
                int a5 = random.nextInt(2) + 5; //5,6
                return a5;

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
//        if(coinindex==1)
//            return MainScene.Layer.item_1;
//        if(coinindex==2)
//            return MainScene.Layer.item_1;
//        if(coinindex==3)
//            return MainScene.Layer.item_1;
//        if(coinindex==4)
//            return MainScene.Layer.item_1;
//        if(coinindex==5)
//            return MainScene.Layer.item_1;
//        if(coinindex==6)
//            return MainScene.Layer.item_1;
//        if(coinindex==7)
//            return MainScene.Layer.item_1;
//        else
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

