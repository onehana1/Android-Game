package com.example.lifegame.game;

import android.graphics.Canvas;


import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.Metrics;
import com.example.lifegame.framework.Sprite;


public class HorzScrollBackground extends Sprite {
    private final float speed;
    private final float width;
    private float scroll;
    public HorzScrollBackground(int bitmapResId, float speed) {
        super(bitmapResId, Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height);
        this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
        setSize(Metrics.game_height, width);
        this.speed = speed;
    }
    @Override
    public void update() {
        scroll += speed * BaseScene.frameTime;
    }

    @Override
    public void draw(Canvas canvas) {
        float curr = scroll % width;
        if (curr > 0) curr -= width;
        while (curr < Metrics.game_width) {
            dstRect.set(curr, 0,curr + width , Metrics.game_height);
            canvas.drawBitmap(bitmap, null, dstRect, null);
            curr += width;
        }
    }}
