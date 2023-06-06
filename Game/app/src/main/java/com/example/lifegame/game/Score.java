package com.example.lifegame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.lifegame.BuildConfig;
import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.BitmapPool;
import com.example.lifegame.framework.IGameObject;

public class Score implements IGameObject {
    private final Bitmap bitmap;
    private final int srcCharWidth, srcCharHeight;
    private final float right, top;
    private final float dstCharWidth, dstCharHeight;
    private Rect srcRect = new Rect();
    private RectF dstRect = new RectF();
    private int score, displayScore;

    private int moneyScore;
    private int lifeScore;
    private int hobbyScore;

    private int hp;

    private int happyScore;


    private int friendScore;

    private int smokeScore;

    public Score(int mipmapResId, float right, float top, float width) {
        this.bitmap = BitmapPool.get(mipmapResId);
        this.right = right;
        this.top = top;
        this.dstCharWidth = width;
        this.srcCharWidth = bitmap.getWidth() / 10;
        this.srcCharHeight = bitmap.getHeight();
        this.dstCharHeight = dstCharWidth * srcCharHeight / srcCharWidth;


        this.moneyScore = 0; // 돈 점수 초기화
        this.lifeScore = 0; // 생명 점수 초기화
        this.hobbyScore =0; // 취미 점수 초기화
    }

    public void setScore(int score) {
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    @Override
    public void update() {
        int diff = score - displayScore;
        if (diff == 0) return;
        if (-10 < diff && diff < 0) {
            displayScore--;
        } else if (0 < diff && diff < 10) {
            displayScore++;
        } else {
            displayScore += diff / 10;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        int value = this.displayScore;
        float x = right;
        while (value > 0) {
            int digit = value % 10;
            srcRect.set(digit * srcCharWidth, 0, (digit + 1) * srcCharWidth, srcCharHeight);
            x -= dstCharWidth;
            dstRect.set(x, top, x + dstCharWidth, top + dstCharHeight);
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
            value /= 10;
        }
    }

    public void add(int amount, int index) {
        score += amount;

        //1~7 코인 / 8choice

        if (index == 1) {
            //moneyScore++; // 인덱스 1의 코인을 얻으면 돈 점수 상승
            moneyScore += amount;
      //      System.out.println("moneyScore - " + moneyScore);
        }

        if (index == 2||index == 3||index == 4) {
            hobbyScore += 1; // 2,3,4 취미 코인
            happyScore += amount;
         //   System.out.println("happyScore - " + happyScore);
        }
        //5시계 6앨범
        if (index == 5) {
            happyScore += amount;
            hp -= 1;
         //   System.out.println("hp - " + hp);
        }
        if (index == 6) {
            happyScore += amount;
          //  System.out.println("happyScore - " + happyScore);
        }

        if (index == 7) {
            //  lifeScore--; // 인덱스 7의 코인을 얻으면 생명 점수 하락
            smokeScore += 1;
            hp -= 1;
            lifeScore -=amount;
           // System.out.println("hp - " + hp);
        }

        if (index == 8) {
            friendScore += amount;
            System.out.println("friendScore - " + friendScore);
        }

        if (index == 9) {
            hobbyScore += amount;
            System.out.println("hobbyScore - " + hobbyScore);
        }


    }

    public int getMoneyScore() {
        return moneyScore;
    }

    public int getLifeScore() {
        return lifeScore;
    }

    public int getHobbyScore() {
        return hobbyScore;
    }
}
