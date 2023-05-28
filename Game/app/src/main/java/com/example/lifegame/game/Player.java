package com.example.lifegame.game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.lifegame.R;
import com.example.lifegame.framework.AnimSprite;
import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.Emotion;
import com.example.lifegame.framework.IBoxCollidable;

public class Player extends AnimSprite implements IBoxCollidable {
    private RectF collisionRect = new RectF();
    private final float ground;
    private float jumpSpeed;
    private static final float JUMP_POWER = 9.0f;
    private static final float GRAVITY = 17.0f;

    private static final float RUN_SPEED = 1.0f;
    private static final float CHOICE_SPEED = 0.1f;

    float bgspeed = 1.0f;
    private long choiceStartTime = 0;
    private static final long CHOICE_DURATION = 2000; // 2초

    private Emotion emotion; // Emotion 객체 추가

    private int age; // 플레이어의 나이 변수

    public Player() {
        super(R.mipmap.player, 2.0f, 7.0f, 2.0f, 2.0f, 8, 1);
        this.ground = y;

        emotion = new Emotion();
        age = 0; // 초기 나이 설정
    }

    public enum State {
        running,jump, CHOICE, COUNT
    }


    protected State state = State.running;


    protected static Rect[][] srcRects = {
            makeRects(0, 1, 2, 3), //student run
            makeRects(4), //student jump
            makeRects(4) //student jump

    };

    protected static float[][] edgeInsetRatios = {
            { 0.0f, 0.0f, 0.0f, 0.0f }, // State.running
            { 0.0f, 0.0f, 0.0f, 0.0f }, // State.jump
            { 0.0f, 0.0f, 0.0f, 0.0f }  // State.CHOICE

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

    public State getState() {
        return this.state;
    }

    public void jump() {
        if (state != State.CHOICE && state == State.running){
            state = State.jump;
            jumpSpeed = -JUMP_POWER;
        }
    }

    public void choice() {
        state = State.CHOICE;
        choiceStartTime = System.currentTimeMillis();// choice 시작 시간 기록
        System.out.println("Player is in choice state.");
    }

    public void setAge(int age) {
        this.age = age;
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

        else if (state == State.CHOICE) {
            {
                if (System.currentTimeMillis() - choiceStartTime > CHOICE_DURATION) {
                    state = State.running;
                    setBgSpeed(RUN_SPEED);

                }
                else {
                    fixDstRect();
                    setBgSpeed(CHOICE_SPEED);
                   // System.out.println("플레이어의 배경속도: " + this.getBgSpeed());
                    y = ground;
                }
            }
        }
        else {
            state = State.running;
           // System.out.println("플레이어의 배경속도: " + this.getBgSpeed());

        }

    }

    public void setBgSpeed(float newspeed) {
        this.bgspeed = newspeed;
    }
    public float getBgSpeed() {
        return bgspeed;
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


        // Player의 상태가 CHOICE일 때 Emotion 객체 그리기
        if (state == State.CHOICE) {
            emotion.setPosition(dstRect.left, dstRect.top);
            emotion.draw(canvas);
            System.out.println("초이스");
        }

    }

    public RectF getCollisionRect() {
        return dstRect;
    }
}
