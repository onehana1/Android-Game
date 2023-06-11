package com.example.lifegame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.lifegame.framework.BitmapPool;
import com.example.lifegame.framework.Button;
import com.example.lifegame.framework.IGameObject;

public class cooltime implements IGameObject {
    private final Bitmap bitmap;
    private final float right, top;

    private final int srcCharWidth, srcCharHeight;
    private final float dstCharWidth, dstCharHeight;

    private Rect srcRect = new Rect();

    private RectF dstRect = new RectF();
    private int cool, time;

    private boolean isCooling = false;

    private Button hobbyButton;

    public cooltime(int mipmapResId, float right, float top, float width){
        this.bitmap = BitmapPool.get(mipmapResId);
        this.right = right;
        this.top = top;
        this.dstCharWidth = width;
        this.srcCharWidth = bitmap.getWidth() / 10;
        this.srcCharHeight = bitmap.getHeight();
        this.dstCharHeight = dstCharWidth * srcCharHeight / srcCharWidth;

        this.hobbyButton = hobbyButton;  // hobbyButton 객체 전달받기
    }

    public void setCoolingTime(int seconds) {
        int frames = seconds * 60;
        this.cool = frames;
        this.time = this.cool;
        this.isCooling = true;
    }

    public boolean getisCooling(){
        return isCooling;
    }

    public void update() {
        if (isCooling) {
            time--;
            if (time <= 0) {
                isCooling = false;
            }
        }
    }


    public void draw(Canvas canvas) {
        int value = this.time / 60;
        float x = right;
        while (value > 0) {
            int digit = value % 10;
            srcRect.set(digit * srcCharWidth, 0, (digit + 1) * srcCharWidth, srcCharHeight);
            x -= dstCharWidth;
            dstRect.set(x, top, x + dstCharWidth, top + dstCharHeight);
            canvas.drawBitmap(bitmap, srcRect, dstRect, null);
            value /= 10;
        }

        if (time <= 0) {
            // 쿨타임 종료 시 hobbyButton의 쿨타임 상태를 해제
            hobbyButton.setCooling(false);
        }
    }


    public void setcoltime(int time) {
        this.cool = time;
    }

    public void setButtoncool(Button hobbyButton) {
        this.hobbyButton = hobbyButton;
    }

}
