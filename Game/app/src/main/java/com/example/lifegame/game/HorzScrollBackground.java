package com.example.lifegame.game;

import android.graphics.Canvas;


import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.ChoiceObj;
import com.example.lifegame.framework.Metrics;
import com.example.lifegame.framework.Sprite;


public class HorzScrollBackground extends Sprite {
    private final Player player;
    private float speed;
    private final float width;
    private float scroll;

    private int scrollCount; // 현재까지 스크롤한 횟수
    private int[] bitmapResIds; // 이미지 리소스 ID 배열
    private int currentBitmapIndex; // 현재 사용 중인 이미지 리소스의 인덱스


    public HorzScrollBackground(int[] bitmapResIds, float speed, Player player) {
        super(bitmapResIds[0], Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height);
        this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
        setSize(Metrics.game_height, width);
        this.speed = speed;
        this.player = player;// Player 객체를 저장

        this.bitmapResIds = bitmapResIds;
        this.currentBitmapIndex = 0; // 처음에는 첫 번째 이미지 리소스를 사용
    }
    @Override
    public void update() {
        if(player.getBgSpeed()!=0)
            speed = player.getBgSpeed();
        scroll -= speed * BaseScene.frameTime;


        // 이미지 전환을 위해 체크
        if (scroll <= -width) {
            scroll += width;
            currentBitmapIndex++;
            if (currentBitmapIndex >= bitmapResIds.length) {
                currentBitmapIndex = 0;
            }
            setBitmapResource(bitmapResIds[currentBitmapIndex]);
        }

    }


    @Override
    public void draw(Canvas canvas) {
        float curr = scroll % width;
        if (curr > 0) curr -= width;

        while (curr < Metrics.game_width) {
            dstRect.set(curr, 0, curr + width, Metrics.game_height);
            canvas.drawBitmap(bitmap, null, dstRect, null);
            curr += width;
        }
    }







}
