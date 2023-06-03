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
    private final float width;
    private float scroll;

    private int[] bitmapResIds; // 이미지 리소스 ID 배열
    private int currentIndex; // 현재 사용 중인 이미지 리소스의 인덱스


    public HorzScrollBackground(int[] bitmapResIds, float speed, Player player) {
        super(bitmapResIds[1], Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height);
        this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
        setSize(Metrics.game_height, width);
        this.speed = speed;
        this.player = player;// Player 객체를 저장

        this.bitmapResIds = bitmapResIds;
        this.currentIndex = 0; // 처음에는 첫 번째 이미지 리소스를 사용
    }

    public int getCurrentImageResourceId() {
        return bitmapResIds[currentIndex];
    }


    public void setPlayerAgeBasedOnCurrentImage() {
        int currentImageResId = bitmapResIds[currentIndex];
        int age = 0;
        // 이미지 리소스 ID에 따라 플레이어의 나이를 설정
        if (currentImageResId == R.mipmap.school) {
            age = 1;
            System.out.println("setAge 1!");

        } else if (currentImageResId == R.mipmap.company) {
            age = 2;
            System.out.println("setAge 2!");

        } else if (currentImageResId == R.mipmap.playground) {
            age = 1 ;
            System.out.println("setAge 3!");

        }
        player.setAge(age);
    }

    @Override
    public void update() {
        if (player.getBgSpeed() != 0)
            speed = player.getBgSpeed();
        scroll -= speed * BaseScene.frameTime;

        // 이미지 전환을 위해 체크
        if (scroll <= -width) {
            scroll += width;

            // 다음 이미지로 전환
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

        while (curr < Metrics.game_width) {
            if (next > 0) {
                int index = currentIndex;
                if (next > Metrics.game_width) {
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
