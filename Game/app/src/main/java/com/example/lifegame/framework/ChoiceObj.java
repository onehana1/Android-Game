package com.example.lifegame.framework;

import com.example.lifegame.R;
import com.example.lifegame.game.MainScene;

import java.util.Random;

public class ChoiceObj extends MapObject{
    private Type type;
    public static final float SPEED = 2.0f;
    private boolean spawned = false;

    public enum Type {
        c_art, c_music, c_study, COUNT;
        int resId() { return resIds[this.ordinal()]; }
        float width() { return widths[this.ordinal()]; }
        float height() { return heights[this.ordinal()]; }
        static int[] resIds = {
                R.mipmap.c_art,
                R.mipmap.c_music,
                R.mipmap.c_study,
        };
        static float[] widths = { 2.0f, 5.2f, 2.0f };
        static float[] heights = { 2.3f, 2.3f, 2.3f };
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

    public void update() {
        float dx = -SPEED * BaseScene.frameTime;

        dstRect.offset(dx, 0);

        if (dstRect.right < 0) {
            setSpawned(false);
        }
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
}
