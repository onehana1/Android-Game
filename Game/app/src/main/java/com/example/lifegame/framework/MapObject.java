package com.example.lifegame.framework;

import android.graphics.RectF;

import com.example.lifegame.game.MainScene;

public class MapObject extends Sprite implements IBoxCollidable, IRecyclable{
    public static final float SPEED = 2.0f;
    public void update() {
        float dx = -SPEED * BaseScene.frameTime;

        dstRect.offset(dx, 0);

        if (dstRect.right < 0){

        }
    }



    public RectF getCollisionRect() {
        return dstRect;
    }
    public void onRecycle() {}
}
