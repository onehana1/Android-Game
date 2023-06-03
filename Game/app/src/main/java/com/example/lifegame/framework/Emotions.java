package com.example.lifegame.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.lifegame.R;

import java.util.Random;


public class Emotions implements IGameObject {
    private static final float EMOTION_SIZE = 5.0f; // Emotion 이미지의 크기
    private static final int SUCCESS_IMAGE = R.mipmap.success; // success 이미지 리소스 ID
    private static final int FAIL_IMAGE = R.mipmap.fail; // success 이미지 리소스 ID


    private Bitmap successBitmap; // success 이미지 비트맵
    private Bitmap failBitmap; // success 이미지 비트맵

    private RectF dstRect; // Emotion 이미지의 목적 사각형
    private boolean  callemotion;
    private Random random; // 랜덤 객체

    private float offsetY = 0.0f; // Y축 방향으로 이동할 오프셋 값

    private Paint paint; // 투명도를 조절하는 Paint 객체


    private int emply = 0;


    public Emotions() {
        successBitmap = BitmapPool.get(SUCCESS_IMAGE); //성공
        failBitmap = BitmapPool.get(FAIL_IMAGE); //실패

        dstRect = new RectF();
        random = new Random();

        paint = new Paint();
        paint.setAlpha(255); // 초기 투명도 설정
    }

    public void setPosition(float x, float y) {
        float halfSize = EMOTION_SIZE / 2;
        dstRect.set(x - halfSize, y - halfSize, x + halfSize, y + halfSize);
    }

    @Override
    public void update() {
        // Emotion의 업데이트 로직 (필요한 경우 구현)


    }

    public void setplay(int i){
        emply = i;
    }

    public int getplay(){
        return emply;
    }

    @Override
    public void draw(Canvas canvas) {
        if (emply == 1) {
            offsetY += 0.02;
            int alpha = (int) (255 - (255 * offsetY / 1.02)); // 투명도 계산
            paint.setAlpha(alpha); // 투명도 설정

            if (offsetY >= 1.0) {
                emply=0;
                offsetY = 0.0f;
            }
            float adjustedY = dstRect.top - offsetY;
            dstRect.set(dstRect.left, adjustedY, dstRect.right, adjustedY + EMOTION_SIZE);

            canvas.drawBitmap(successBitmap, null, dstRect, paint);

            // canvas.drawBitmap(successBitmap, null, dstRect, null);
        }

        if (emply == 2) {
            offsetY -= 0.02;
            int alpha = (int) (255 - (255 * -(offsetY) / 1.02)); // 투명도 계산
            paint.setAlpha(alpha); // 투명도 설정

            if (offsetY <= -1.0) {
                emply=0;
                offsetY = 0.0f;
            }
            float adjustedY = dstRect.top - offsetY;
            dstRect.set(dstRect.left, adjustedY, dstRect.right, adjustedY + EMOTION_SIZE);

            canvas.drawBitmap(failBitmap, null, dstRect, paint);

        }
    }
}
