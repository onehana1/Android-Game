package com.example.lifegame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;


import com.example.lifegame.R;
import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.BitmapPool;
import com.example.lifegame.framework.ChoiceObj;
import com.example.lifegame.framework.Metrics;
import com.example.lifegame.framework.Sprite;


public class HorzScrollBackground extends Sprite {
    private final Player player;
    private float speed;
    private float width;
    private float scroll;
    private int currentIndex;

    private int[] bitmapResIds;

    public HorzScrollBackground(int[] bitmapResIds, float speed, Player player) {
        super(bitmapResIds[0], Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height);
        this.bitmapResIds = bitmapResIds;
        this.speed = speed;
        this.player = player;
        this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
        setSize(Metrics.game_height, width);
        this.currentIndex = 0;
    }

    public void setPlayerAgeBasedOnCurrentImage() {
        int age = 0;
        int currentImageResId = bitmapResIds[currentIndex];
        // 이미지 리소스 ID에 따라 플레이어의 나이를 설정
        if (currentImageResId == R.mipmap.playground) {
            age = 1;
        } else if (currentImageResId == R.mipmap.school) {
            age = 2;
        } else if (currentImageResId == R.mipmap.company) {
            age = 3;
        }
        player.setAge(age);
    }

    @Override
    public void update() {

        this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
        setSize(Metrics.game_height, width);

        if (player.getBgSpeed() != 0)
            speed = player.getBgSpeed();
        scroll -= speed * BaseScene.frameTime;

        // 이미지 전환을 위해 체크
        if (scroll <= -width) {
            scroll += width;
            currentIndex++;
            if (currentIndex >= bitmapResIds.length) {
                currentIndex = 0;
            }

            // 플레이어의 나이 업데이트
            setPlayerAgeBasedOnCurrentImage();
        }
    }

    @Override
    public void draw(Canvas canvas) {
        float curr = scroll % width;
        float next = curr + width;

        while (curr < width) {
            if (next > 0) {
                int index = currentIndex;
                if (next > width) {
                    index++;
                    if (index >= bitmapResIds.length) {
                        index = 0;
                    }
                }

                setBitmapResource(bitmapResIds[index]);
                dstRect.set(curr, 0, next, Metrics.game_height);
                canvas.drawBitmap(bitmap, null, dstRect, null);
            }

            curr += width;
            next += width;
        }
    }
}