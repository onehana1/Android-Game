package com.example.lifegame.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

import com.example.lifegame.R;

import java.util.Random;

public class Emotions implements IGameObject {
    private static final float EMOTION_SIZE = 5.0f; // Emotion 이미지의 크기
    private static final int SUCCESS_IMAGE = R.mipmap.success; // success 이미지 리소스 ID

    private Bitmap successBitmap; // success 이미지 비트맵
    private RectF dstRect; // Emotion 이미지의 목적 사각형
    private Random random; // 랜덤 객체

    public Emotions() {
        successBitmap = BitmapPool.get(SUCCESS_IMAGE);
        dstRect = new RectF();
        random = new Random();
    }

    public void setPosition(float x, float y) {
        float halfSize = EMOTION_SIZE / 2;
        dstRect.set(x - halfSize, y - halfSize, x + halfSize, y + halfSize);
    }

    @Override
    public void update() {
        // Emotion의 업데이트 로직 (필요한 경우 구현)
    }

    @Override
    public void draw(Canvas canvas) {
        // 플레이어 상태가 CHOICE일 때 50%의 확률로 success 이미지를 그립니다.
//        if (random.nextFloat() < 0.5f) {
            canvas.drawBitmap(successBitmap, null, dstRect, null);
      //  }
    }
}
