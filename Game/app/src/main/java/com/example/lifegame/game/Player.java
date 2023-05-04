package com.example.lifegame.game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.lifegame.R;
import com.example.lifegame.framework.AnimSprite;
import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.IBoxCollidable;

public class Player extends AnimSprite implements IBoxCollidable {
    private RectF collisionRect = new RectF();
    private final float ground;
    private float jumpSpeed;
    private static final float JUMP_POWER = 9.0f;
    private static final float GRAVITY = 17.0f;


    public Player() {
        super(R.mipmap.player, 2.0f, 7.0f, 2.0f, 2.0f, 8, 1);
        this.ground = y;
    }

    protected enum State {
        running,jump, COUNT
    }
    protected State state = State.running;


    protected static Rect[][] srcRects = {
            makeRects(0, 1, 2, 3), //student run
            makeRects(4) //student jump
    };

    protected static float[][] edgeInsetRatios = {
            { 0.0f, 0.0f, 0.0f, 0.0f }, // State.running
            { 0.0f, 0.0f, 0.0f, 0.0f }, // State.jump

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
    public void jump() {
        if (state == State.running) {
            state = State.jump;
            jumpSpeed = -JUMP_POWER;
        }
    }

    public void update() {
        if (state == State.jump) {
            float dy = jumpSpeed * BaseScene.frameTime;
            jumpSpeed += GRAVITY * BaseScene.frameTime;
            if (y + dy >= ground) {
                dy = ground - y;
                state = State.running;
            }
            y += dy;
            fixDstRect();
        }
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
        return dstRect;
    }
}
