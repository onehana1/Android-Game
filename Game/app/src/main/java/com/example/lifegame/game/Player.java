package com.example.lifegame.game;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.lifegame.R;
import com.example.lifegame.framework.AnimSprite;
import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.Emotion;
import com.example.lifegame.framework.Emotions;
import com.example.lifegame.framework.IBoxCollidable;

public class Player extends AnimSprite implements IBoxCollidable {
    private RectF collisionRect = new RectF();
    private final float ground;
    private float jumpSpeed;
    private static final float JUMP_POWER = 10.0f;
    private static final float GRAVITY = 17.0f;

    private static final float RUN_SPEED = 2.0f;
    private static final float CHOICE_SPEED = 0.01f;

    float bgspeed = RUN_SPEED;
    private long choiceStartTime = 0;
    private static final long CHOICE_DURATION = 900;

    private Emotion emotion; // Emotion 객체 추가
    private Emotions emotions; // Emotions 객체 추가

    private int age; // 플레이어의 나이 변수
    private boolean ageChanged = false; // 나이 변경 여부 변수

    private boolean canchoice = false;



    public enum Hobby {NONE, STUDY, PAINT, MUSIC;}
    private Hobby hobby;

    public Player() {
        super(R.mipmap.player1, 2.0f, 6.8f, 2.0f, 2.0f, 8, 1);
        this.ground = y;

        emotion = new Emotion();
        emotions = new Emotions();
        age = 0; // 초기 나이 설정

        hobby = Hobby.NONE;

    }

    public void setHobby(Hobby i){
        hobby = i;
    }

    public Hobby getHobby(){
       return hobby;
    }

    public boolean isAgeChanged() {
        return ageChanged;
    }

    public void resetAgeChanged() {
        ageChanged = false;
    }

    public enum State {
        running,jump, CHOICE, COUNT
    }


    protected State state = State.running;


    protected static Rect[][] srcRects = {
            makeRects(0, 1, 2, 3), //student run
            makeRects(4), //student jump
            makeRects(4), //student choice

            makeRects(100, 101, 102, 103), //baby run
            makeRects(104), //baby jump
            makeRects(104), //baby choice

            makeRects(200, 201, 202, 203), //worker run
            makeRects(204), //worker jump
            makeRects(204), //worker choice

            makeRects(300, 301, 302, 303), //old worker run
            makeRects(304), //old worker jump
            makeRects(304), //old worker choice

    };

    private void updateSrcRects() {
        if (age==2) {
            srcRects = new Rect[][]{
                    makeRects(0, 1, 2, 3), // student run
                    makeRects(4), // student jump
                    makeRects(4), // student choice
            };
        } else if (age == 1) {
            srcRects = new Rect[][]{
                    makeRects(100, 101, 102, 103), // baby run
                    makeRects(104), // baby jump
                    makeRects(104), // baby choice
            };
        } else if (age == 3) {
            srcRects = new Rect[][]{
                    makeRects(200, 201, 202, 203), // worker run
                    makeRects(204), // worker jump
                    makeRects(204), // worker choice
            };
        } else if (age == 4) {
            srcRects = new Rect[][]{
                    makeRects(300, 301, 302, 303), // elderly run
                    makeRects(304), // elderly jump
                    makeRects(304), // elderly choice
            };


        }

        else if (age == 5) {
            srcRects = new Rect[][]{
                    makeRects(300, 301, 302, 303), // elderly run
                    makeRects(304), // elderly jump
                    makeRects(304), // elderly choice
            };


        }


    }

    public void setAge(int age) {
        if (this.age != age) {
            this.age = age;
            ageChanged = true;
            updateSrcRects();
        }
    }
    public int getAge() {
        return age;
    }


    private float getX() {
        return dstRect.centerX();
    }

    private float getY() {
        return dstRect.centerY();
    }


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

    public void aa(){
        MainScene scene = (MainScene) BaseScene.getTopScene();


    }


    public void pressHobby(){
        MainScene scene = (MainScene) BaseScene.getTopScene();
        scene.score.add(1, 9);
    }

    public void choice() {
        if(canchoice) {
            state = State.CHOICE;
            choiceStartTime = System.currentTimeMillis();// choice 시작 시간 기록
            System.out.println("Player is in choice state.");



            emotions.setplay(1);
        }
    }
    public void setCanchoice(boolean i){
        canchoice = i;
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
            emotions.setPosition(dstRect.left, dstRect.top);
            emotions.draw(canvas);
            //System.out.println("초이스");
        }

    }

    public RectF getCollisionRect() {
        return dstRect;
    }
}
