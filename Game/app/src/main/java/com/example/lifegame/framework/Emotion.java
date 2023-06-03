package com.example.lifegame.framework;

import static com.example.lifegame.game.MainScene.Layer.player;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


import com.example.lifegame.R;
import com.example.lifegame.game.MainScene;


public class Emotion extends MapObject {
    protected Rect srcRect = new Rect();
    private static final int ROW = 1;
    private static final int SIZE = 40;
    private static final int BORDER = 1;
    private boolean showSuccess = false;

    public Emotion() {
        setBitmapResource(R.mipmap.success);
    }

    private void init(int index, float left, float top) {
        setSrcRect(index);
        dstRect.set(left, top, left + width, top + height);
    }

    private void setSrcRect(int index) {
        int x = index % ROW;
        int y = index / ROW;
        int left = x * (SIZE + BORDER) + BORDER;
        int top = 0;
        srcRect.set(left, top, left + SIZE, top + SIZE);
    }
    
    public void setPosition(float x, float y) {
        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + width;
        dstRect.bottom = y + height;
    }
    public void setShowSuccess(boolean show) {
        this.showSuccess = show;
    }


    public void draw(Canvas canvas) {

    }

    protected MainScene.Layer getLayer() {
        return MainScene.Layer.ui;
    }
}