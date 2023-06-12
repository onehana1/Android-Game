package com.example.lifegame.game;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.lifegame.BuildConfig;
import com.example.lifegame.R;
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
    private int score, displayScore, prat;

    //================점수=================
    private int moneyScore;
    private int lifeScore;
    private int hobbyScore;

    private int studyScore;
    private int paintScore;
    private int singScore;


    private int hp;

    private int happyScore;
    private int maxhappy = 10;



    private int friendScore;

    private int smokeScore;
    private final Paint smokeScorePaint; // 체력 게이지를 그리기 위한 Paint 객체
    private final Bitmap cigaretteImage; // 체력 이미지


    //================체력=================
    private final int maxHp = 10; // 최대 체력
    private final float hpGaugeWidth = 3.5f; // 체력 게이지 너비
    private final float hpGaugeHeight = 0.5f; // 체력 게이지 높이
    private final float hpGaugeX = 0.8f; // 체력 게이지 위치 X 좌표
    private final float hpGaugeY = 0.2f; // 체력 게이지 위치 Y 좌표
    private final Paint hpGaugePaint; // 체력 게이지를 그리기 위한 Paint 객체

    //=====================================

    private final Bitmap lifeImage; // 체력 이미지
    private final float lifeImageWidth = 0.5f; // 체력 이미지 너비
    private final float lifeImageHeight = 0.5f; // 체력 이미지 높이
    private final float lifeImageX = 0.2f; // 체력 이미지 위치 X 좌표
    private final float lifeImageY = 1.f; // 체력 이미지 위치 Y 좌표

    //=================happy====================

    private final float happyGaugeWidth = 3.5f; // happy 게이지 너비
    private final float happyGaugeHeight = 0.5f; // happy 게이지 높이
    private final float happyGaugeX = 5.5f; // happy게이지 위치 X 좌표
    private final float happyGaugeY = 0.2f; // happy 게이지 위치 Y 좌표
    private final Paint happyGaugePaint; // happy게이지를 그리기 위한 Paint 객체
    //=====================================
    private final Bitmap happyImage; //
    private final float happyImageWidth = 0.5f;
    private final float happyImageHeight = 0.5f;
    private final float happyImageX = 4.8f;
    private final float happyImageY = 1.f;
    //=====================================


    //=====================================

    public Score(int mipmapResId, float right, float top, float width) {
        this.bitmap = BitmapPool.get(mipmapResId);
        this.right = right;
        this.top = top;
        this.dstCharWidth = width;
        this.srcCharWidth = bitmap.getWidth() / 10;
        this.srcCharHeight = bitmap.getHeight();
        this.dstCharHeight = dstCharWidth * srcCharHeight / srcCharWidth;


        this.moneyScore =0; // 돈 점수 초기화
        this.lifeScore = 0; // 생명 점수 초기화
        this.hobbyScore =0; // 취미 점수 초기화
        this.happyScore = maxhappy;
        this.friendScore=0;

        this.paintScore = 0;
        this.singScore = 100;
        this.studyScore = 0;

        this.hp = maxHp;
        this.prat = 10;

        hpGaugePaint = new Paint();
        hpGaugePaint.setColor(Color.RED);

        happyGaugePaint = new Paint();
        happyGaugePaint.setColor(Color.GREEN);


        smokeScorePaint= new Paint();
        smokeScorePaint.setColor(Color.RED);


        // 체력 이미지 초기화
        lifeImage = BitmapPool.get(R.mipmap.life);
        happyImage = BitmapPool.get(R.mipmap.happy);
        cigaretteImage = BitmapPool.get(R.mipmap.cigarette);


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

        // smokeScore 그리기
        String smokeText = smokeScore + "" ;
        float smokeTextX = right;
        float smokeTextY = top + dstCharHeight + 20; // 이동할 Y 좌표 값 조정 가능

        smokeScorePaint.setTextSize(0.5f);
        canvas.drawText(smokeText, 6, 8.7f, smokeScorePaint);
        String smoke2Text = "/" ;
        canvas.drawText(smoke2Text, 6.2f, 8.7f, smokeScorePaint);
        String smoke3Text = "7" ;
        canvas.drawText(smoke3Text, 6.4f, 8.7f, smokeScorePaint);



        dstRect.set(happyImageX, 8.2f, happyImageX + 1.0f, 8.7f);
        canvas.drawBitmap(cigaretteImage, null, dstRect, null);



        dstRect.set(lifeImageX, top, lifeImageX + lifeImageWidth, top + lifeImageHeight);
        canvas.drawBitmap(lifeImage, null, dstRect, null);


        dstRect.set(happyImageX, top, happyImageX + happyImageWidth, top + happyImageHeight);
        canvas.drawBitmap(happyImage, null, dstRect, null);


        // 체력 게이지 그리기
        float hpRatio = (float) hp / maxHp; // 현재 체력 비율 계산
        float hpGaugeWidthCurrent = hpGaugeWidth * hpRatio; // 현재 체력에 맞는 너비 계산
        canvas.drawRect(hpGaugeX, hpGaugeY, hpGaugeX + hpGaugeWidthCurrent, hpGaugeY + hpGaugeHeight, hpGaugePaint);

        // 체력 게이지 그리기
        float happyRatio = (float) happyScore / maxhappy; // 현재 체력 비율 계산
        float happyGaugeWidthCurrent = happyGaugeWidth * happyRatio; // 현재 체력에 맞는 너비 계산
        canvas.drawRect(happyGaugeX, happyGaugeY, happyGaugeX + happyGaugeWidthCurrent, happyGaugeY + happyGaugeHeight, happyGaugePaint);


    }


    public void add(int amount, int index) {

        //1~7 코인 1돈 2책 3 페인트 4기타 5시계 6앨범 7담배/ 8choice

        if (index == 1) {
            //moneyScore++; // 인덱스 1의 코인을 얻으면 돈 점수 상승
            moneyScore += amount;
            happyScore -= 1;
            score += amount;
      //      System.out.println("moneyScore - " + moneyScore);
        }

        if (index == 2) {
            studyScore += 1; // 2,3,4 취미 코인
            hobbyScore += 1;

            happyScore += 1;
            score += amount;
            //   System.out.println("happyScore - " + happyScore);
        }
        if (index == 3) {
            paintScore += 1; // 2,3,4 취미 코인
            hobbyScore += 1;
            happyScore += 1;
            score += amount;
            //   System.out.println("happyScore - " + happyScore);
        }
        if (index == 4) {
            singScore += 1; // 2,3,4 취미 코인
            hobbyScore += 1;
            happyScore += 1;
            score += amount;
            //   System.out.println("happyScore - " + happyScore);
        }
        //5시계 6앨범
        if (index == 5) {
            happyScore += 1;
            hp -= 1;
         //   System.out.println("hp - " + hp);
        }
        if (index == 6) {
            happyScore += 1;
          //  System.out.println("happyScore - " + happyScore);
        }

        if (index == 7) {
            //  lifeScore--; // 담배코인
            smokeScore += 1;
            happyScore += 1;
            hp -= 1;
            lifeScore -=amount;
           // System.out.println("hp - " + hp);
        }

        if (index == 8) {
            friendScore += 1;
            System.out.println("friendScore - " + friendScore);
        }

        if (index == 9) {
            hobbyScore += 1;
            System.out.println("hobbyScore - " + hobbyScore);
        }


    }
    public int getHP() {
        return hp;
    }

    public int getMoneyScore() {
        return moneyScore;
    }

    public int getSmokeScore() {
        return smokeScore;
    }

    public int getLifeScore() {
        return lifeScore;
    }

    public int getHobbyScore() {
        return hobbyScore;
    }

    public int getFriendScore() {
        return friendScore;
    }
    public int getHappyScore() {
        return happyScore;
    }


    public int getPaintScore() {
        return paintScore;
    }
    public int getStudyScore() {
        return studyScore;
    }
    public int getSingScore() {
        return singScore;
    }



}
