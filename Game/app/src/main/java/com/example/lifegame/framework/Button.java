package com.example.lifegame.framework;

import android.graphics.Canvas;
import android.view.MotionEvent;

public class Button extends Sprite implements ITouchable {
    private static final String TAG = Button.class.getSimpleName();
    private final Callback callback;

    private final int coolImageResId;  // 쿨타임 상태의 이미지 리소스 ID
    private final int defaultImageResId;  // 기본 이미지 리소스 ID
    private boolean isCooling;  // 쿨타임 상태 여부


    public enum Action {
        pressed, released,
    }
    public interface Callback {
        public boolean onTouch(Action action);
    }
    public Button(int defaultImageResId, int coolImageResId, float cx, float cy, float width, float height, Callback callback) {
        super(defaultImageResId, cx, cy, width, height);
        this.defaultImageResId = defaultImageResId;
        this.coolImageResId = coolImageResId;
        this.callback = callback;
        this.isCooling = false;  // 초기 상태는 쿨타임 상태가 아님
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        float x = Metrics.toGameX(e.getX());
        float y = Metrics.toGameY(e.getY());
        if (!dstRect.contains(x, y)) {
            return false;
        }
        int action = e.getAction();


        if (action == MotionEvent.ACTION_DOWN) {
            if (!isCooling) {
                // 버튼이 쿨타임 상태가 아닌 경우에만 콜백 호출
                callback.onTouch(Action.pressed);
            }
        } else if (action == MotionEvent.ACTION_UP) {
            if (!isCooling) {
                // 버튼이 쿨타임 상태가 아닌 경우에만 콜백 호출
                callback.onTouch(Action.released);
            }
        }
        return true;
    }

    public void draw(Canvas canvas) {
        if (isCooling) {
            // 쿨타임 상태인 경우 쿨타임 이미지 그리기
            canvas.drawBitmap(BitmapPool.get(coolImageResId), null, dstRect, null);
        } else {
            // 쿨타임 상태가 아닌 경우 기본 이미지 그리기
            canvas.drawBitmap(BitmapPool.get(defaultImageResId), null, dstRect, null);
        }
    }

    public void setCooling(boolean cooling) {
        this.isCooling = cooling;
    }

}
