package com.example.lifegame.game;

import android.graphics.Canvas;

import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.ChoiceObj;
import com.example.lifegame.framework.CollisionHelper;
import com.example.lifegame.framework.IBoxCollidable;
import com.example.lifegame.framework.IGameObject;
import com.example.lifegame.framework.PauseScene;
import com.example.lifegame.framework.Sound;
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


        ArrayList<IGameObject> choiceobjs = scene.getObjectsAt(MainScene.Layer.choiceobj);
        for (int i = choiceobjs.size() - 1; i >= 0; i--) {
            IGameObject gobj = choiceobjs.get(i);
            if (!(gobj instanceof IBoxCollidable)) {
                continue;
            }
            if (CollisionHelper.collides(player, (IBoxCollidable) gobj)) {
                player.setCanchoice(true);
                if(player.getState()== player.state.CHOICE) {
                    if (player.getcheck() == 2) {
                        player.setsf2();
                        player.setsf();
                        return;
                    }
                    handleChoiceCollision((ChoiceObj) gobj);
                }
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
            Sound.playEffect(coinObj.soundId());
        }
        else if (coinIndex == 2) {
            scene.score.add(amount,2);
            System.out.println("책");
            Sound.playEffect(coinObj.soundId());
        }
        else if (coinIndex == 3) {
            scene.score.add(amount,3);
            System.out.println("페인트");
            Sound.playEffect(coinObj.soundId());
        }
        else if (coinIndex == 4) {
            scene.score.add(amount,4);
            System.out.println("4기타");
            Sound.playEffect(coinObj.soundId());
        }
        else if (coinIndex == 5) {
            scene.score.add(amount,5);
            System.out.println("5시계");
            Sound.playEffect(coinObj.soundId());
        }
        else if (coinIndex == 6) {
            scene.score.add(amount,6);
            System.out.println("6앨범");
            Sound.playEffect(coinObj.soundId());
        }
        else if (coinIndex == 7) {
            scene.score.add(amount,7);
            System.out.println("담배");
            Sound.playEffect(coinObj.soundId());
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
