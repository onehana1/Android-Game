package com.example.lifegame.framework;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.HashMap;

public class BitmapPool {
    private static HashMap<Integer, Bitmap> bitmaps = new HashMap<>();
    private static BitmapFactory.Options opts;

    public static Bitmap get(int mipmapResId) {
        Bitmap bitmap = bitmaps.get(mipmapResId);
        if (bitmap == null) {
            Resources res = GameView.res;
            if (opts == null) {
                opts = new BitmapFactory.Options();
                opts.inScaled = false;
            }
            bitmap = BitmapFactory.decodeResource(res, mipmapResId, opts);
            bitmaps.put(mipmapResId, bitmap);
        }
        return bitmap;
    }
}
