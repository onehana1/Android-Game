package com.example.lifegame.game;

import android.graphics.Canvas;

import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.CollisionHelper;
import com.example.lifegame.framework.IBoxCollidable;
import com.example.lifegame.framework.IGameObject;

import java.util.ArrayList;


public class CollisionChecker implements IGameObject {
    private Player player;

    public CollisionChecker(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        ArrayList<IGameObject> items = scene.getObjectsAt(MainScene.Layer.item);
        for (int i = items.size() - 1; i >= 0; i--) {
            IGameObject gobj = items.get(i);
            if (!(gobj instanceof IBoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(player, (IBoxCollidable) gobj)) {
                scene.remove(MainScene.Layer.item, gobj);
            }
        }
    }

    @Override
    public void draw(Canvas canvas) {}
}
