package com.example.lifegame.framework;

import com.example.lifegame.R;
import com.example.lifegame.game.MainScene;

import java.util.Random;

public class ChoiceObj extends MapObject{
    private Type type;
    float speed = 2.0f;
    private boolean spawned = false;

    private float x;
    private float y;

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public int getChoiceindex() {
        return 1;
    }

    @Override
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public enum Type {
        c_art, c_music, c_study,
        c_m, c_mm, c_mmm,

        c_off1,c_off2,c_off3,

        COUNT;
        int resId() { return resIds[this.ordinal()]; }
        float width() { return widths[this.ordinal()]; }
        float height() { return heights[this.ordinal()]; }
        static int[] resIds = {
                R.mipmap.c_art,
                R.mipmap.c_music,
                R.mipmap.c_study,

                R.mipmap.c_m,
                R.mipmap.c_mm,
                R.mipmap.c_mmm,

                R.mipmap.c_off1,
                R.mipmap.c_off2,
                R.mipmap.c_off3,

        };


        static float[] widths = { 2.0f, 5.2f, 2.0f, 1.8f,1.8f,1.8f,1.8f,1.8f,1.8f };
        static float[] heights = { 2.3f, 2.3f, 2.3f, 2.2f,2.2f,2.2f,2.2f,2.2f,2.2f };
        static Type random(Random random) {
            return Type.values()[random.nextInt(2)];
        }

    }



    public boolean isSpawned() {
        return spawned;
    }

    public void setSpawned(boolean spawned) {
        this.spawned = spawned;
    }

    public void setType(Type i){
     type = i;
    }

    public Type getType(){
        return type;
    }



    public void setX(float x) {
        dstRect.offset(x - dstRect.left, 0);
    }

    public void setY(float y) {
        dstRect.offset(0, y - dstRect.top);
    }

    public static ChoiceObj get(Type type, float left, float top) {
        ChoiceObj platform = (ChoiceObj) RecycleBin.get(ChoiceObj.class);
        if (platform == null) {
            platform = new ChoiceObj();
        }
        platform.init(type, left, top);
        return platform;
    }

    public void init(Type type, float left, float top) {
        this.type = type;
        setBitmapResource(type.resId());
        width = type.width();
        height = type.height();
        //x,y 를 사용하지 않고 dstRect 만을 사용하도록 한다.
        dstRect.set(left, top, left + width, top + height);
    }


    protected MainScene.Layer getLayer() {
        return MainScene.Layer.choiceobj;
    }

    protected MainScene.Choiced getChoice() {
        return MainScene.Choiced.h_study;
    }



    public void update() {
        float dx = -speed * BaseScene.frameTime;
       // System.out.println("speed" + speed);
        dstRect.offset(dx, 0);

        if (dstRect.right < 0){
            BaseScene.getTopScene().remove(getLayer(), this);
        }
    }

}


