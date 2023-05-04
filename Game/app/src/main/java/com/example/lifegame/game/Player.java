package com.example.lifegame.game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.lifegame.R;
import com.example.lifegame.framework.AnimSprite;
import com.example.lifegame.framework.IBoxCollidable;

public class Player extends AnimSprite implements IBoxCollidable {
    private RectF collisionRect = new RectF();

    public Player() {
        super(R.mipmap.player, 2.0f, 7.0f, 2.0f, 2.0f, 8, 1);
    }

    protected enum State {
        running, COUNT
    }
    protected State state = State.running;

    protected enum Age {
        student, COUNT
    }


    protected static Rect[][] srcRects = {
            makeRects(0, 1, 2, 3) //student
    };

    protected static float[][] edgeInsetRatios = {
            { 0.1f, 0.01f, 0.1f, 0.0f }, // State.running
    };

    protected static Rect[] makeRects(int... indices) {
        Rect[] rects = new Rect[indices.length];
        for (int i = 0; i < indices.length; i++) {
            int idx = indices[i];
            int l = 0 + (idx % 100) * 65;
            int t = 0 + (idx / 100) * 74;
            rects[i] = new Rect(l, t, l + 65, t + 74);
        }
        return rects;
    }

    public void update() {

    }

    private void fixCollisionRect() {
        float[] insets = edgeInsetRatios[state.ordinal()];
        collisionRect.set(
                dstRect.left + width * insets[0],
                dstRect.top + height * insets[1],
                dstRect.right - width * insets[2],
                dstRect.bottom - height * insets[3]);
    }

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createdOn) / 1000.0f;
        Rect[] rects = srcRects[state.ordinal()];
        int frameIndex = Math.round(time * fps) % rects.length;
        canvas.drawBitmap(bitmap, rects[frameIndex], dstRect, null);
    }

    public RectF getCollisionRect() {
        return collisionRect;
    }
}
