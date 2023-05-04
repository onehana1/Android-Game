package com.example.lifegame.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

public class Sprite implements IGameObject {
    protected Bitmap bitmap;
    protected RectF dstRect = new RectF();
    protected float x, y, width, height;
    protected Sprite() {}
    public Sprite(int bitmapResId, float cx, float cy, float width, float height) {
        this.x = cx;
        this.y = cy;
        this.width = width;
        this.height = height;
        if (bitmapResId != 0) {
            setBitmapResource(bitmapResId);
        }
        fixDstRect();
    }

    public float getWidth() { return width; }
    public float getHeight() { return height; }
    public float getDstWidth() {
        return dstRect.width();
    }

    public float getDstHeight() {
        return dstRect.height();
    }

    protected void setBitmapResource(int bitmapResId) {
        bitmap = BitmapPool.get(bitmapResId);
    }

    protected void fixDstRect() {
        setSize(width, height);
    }

    protected void setSize(float width, float height) {
        float half_width = width / 2;
        float half_height = height / 2;
        dstRect.set(x - half_width, y - half_height, x + half_width, y + half_height);
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, null, dstRect, null);
    }
}
