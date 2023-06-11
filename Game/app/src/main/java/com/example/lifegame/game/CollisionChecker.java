package com.example.lifegame.game;

import android.graphics.Canvas;

import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.ChoiceObj;
import com.example.lifegame.framework.CollisionHelper;
import com.example.lifegame.framework.IBoxCollidable;
import com.example.lifegame.framework.IGameObject;
import com.example.lifegame.framework.PauseScene;
import com.example.lifegame.framework.coin;

import java.util.ArrayList;


public class CollisionChecker implements IGameObject {
    private Player player;
    private coin coin;

    public CollisionChecker(Player player) {
        this.player = player;
    }

    @Override
    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();

        ArrayList<IGameObject> itemss = scene.getObjectsAt(MainScene.Layer.item_1);
        for (int i = itemss.size() - 1; i >= 0; i--) {
            IGameObject gobj = itemss.get(i);
            if (!(gobj instanceof IBoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(player, (IBoxCollidable) gobj)) {
                // 코인과 플레이어 충돌 처리
                handleCoinCollision((coin) gobj);
                scene.remove(MainScene.Layer.item_1, gobj);
            }
        }



//        ArrayList<IGameObject> items_1 = scene.getObjectsAt(MainScene.Layer.item_1);
//        for (int i = items_1.size() - 1; i >= 0; i--) {
//            IGameObject gobj = items_1.get(i);
//
//            if (!(gobj instanceof IBoxCollidable)) {
//                continue;
//            }
//            if (CollisionHelper.collides(player, (IBoxCollidable) gobj)) {
//                scene.score.add(100,1);
//                scene.remove(MainScene.Layer.item_1, gobj);
//
//            }
//        }
//
//        ArrayList<IGameObject> items_7 = scene.getObjectsAt(MainScene.Layer.item_7);
//        for (int i = items_7.size() - 1; i >= 0; i--) {
//            IGameObject gobj = items_7.get(i);
//
//            if (!(gobj instanceof IBoxCollidable)) {
//                continue;
//            }
//            if (CollisionHelper.collides(player, (IBoxCollidable) gobj)) {
//                scene.score.add(-100,7);
//                scene.remove(MainScene.Layer.item_7, gobj);
//
//            }
//        }
//
//        ArrayList<IGameObject> items_2 = scene.getObjectsAt(MainScene.Layer.item_2);
//        for (int i = items_2.size() - 1; i >= 0; i--) {
//            IGameObject gobj = items_2.get(i);
//
//            if (!(gobj instanceof IBoxCollidable)) {
//                continue;
//            }
//            if (CollisionHelper.collides(player, (IBoxCollidable) gobj)) {
//
//                scene.score.add(100,2);
//                scene.remove(MainScene.Layer.item_2, gobj);
//
//            }
//        }

        ArrayList<IGameObject> choiceobjs = scene.getObjectsAt(MainScene.Layer.choiceobj);
        for (int i = choiceobjs.size() - 1; i >= 0; i--) {
            IGameObject gobj = choiceobjs.get(i);
            if (!(gobj instanceof IBoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(player, (IBoxCollidable) gobj)) {
                player.setCanchoice(true);
                if(player.getState()== player.state.CHOICE)
                    handleChoiceCollision((ChoiceObj) gobj);
            }
            else {
                player.setCanchoice(false);

            }
        }



    }


    private void handleCoinCollision(coin coinObj) {
        MainScene scene = (MainScene) BaseScene.getTopScene();

        int coinIndex = coinObj.getCoinindex();  // c의 인덱스 가져오기
        int amount = 100;

        if (coinIndex == 1) {
            scene.score.add(amount,1);
            System.out.println("돈");
        }
        else if (coinIndex == 2) {
            scene.score.add(amount,2);
            System.out.println("책");
        }
        else if (coinIndex == 3) {
            scene.score.add(amount,3);
            System.out.println("페인트");
        }
        else if (coinIndex == 4) {
            scene.score.add(amount,4);
            System.out.println("4기타");
        }
        else if (coinIndex == 5) {
            scene.score.add(amount,5);
            System.out.println("5시계");
        }
        else if (coinIndex == 6) {
            scene.score.add(amount,6);
            System.out.println("6앨범");
        }
        else if (coinIndex == 7) {
            scene.score.add(amount,7);
            System.out.println("담배");
        }

    }


    private void handleChoiceCollision(ChoiceObj choiceObj) {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        ChoiceObj.Type CT = null;
        ChoiceObj.Type choiceIndex = choiceObj.getType();  // c의 인덱스 가져오기
        int amount = 100;

        if (choiceIndex == CT.c_art) {
           // System.out.println("art!!");
            if(player.getHobby() != Player.Hobby.PAINT)
                player.setHobby(Player.Hobby.PAINT);
        }
        if (choiceIndex == CT.c_music) {
           // System.out.println("c_music!!");
            if(player.getHobby() != Player.Hobby.MUSIC)
                player.setHobby(Player.Hobby.MUSIC);
        }
        if (choiceIndex == CT.c_study) {
            //System.out.println("c_study!!");
            if(player.getHobby() !=Player.Hobby.STUDY)
                player.setHobby(Player.Hobby.STUDY);
        }

        if (choiceIndex == CT.c_m||choiceIndex == CT.c_mm||choiceIndex == CT.c_mmm) {
            System.out.println("c_m!!");
            scene.score.add(amount,8);
        }


        if (choiceIndex == CT.c_off1||choiceIndex == CT.c_off2||choiceIndex == CT.c_off3) {
            System.out.println("c_off1!!");
            scene.score.add(amount,8);
        }

    }


    @Override
    public void draw(Canvas canvas) {}
}
