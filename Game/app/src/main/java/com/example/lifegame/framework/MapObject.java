package com.example.lifegame.framework;

import android.graphics.RectF;

import com.example.lifegame.game.MainScene;

public class MapObject extends Sprite implements IBoxCollidable, IRecyclable{
    public static final float SPEED = 2.0f;
    public static final float COB_SPEED = 1.0f;
    float speed= 4.0f;
    public void update() {
        float dx = -speed * BaseScene.frameTime;

        dstRect.offset(dx, 0);

        if (dstRect.right < 0){
            BaseScene.getTopScene().remove(getLayer(), this);
        }
    }

    public void setSpeed(float newspeed){
        speed =newspeed;
    }
    public float getSpeed(){
       return speed;
    }

    protected MainScene.Layer getLayer() {
        return MainScene.Layer.choiceobj;
    }
    public RectF getCollisionRect() {
        return dstRect;
    }
    public void onRecycle() {}
}
