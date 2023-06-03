package com.example.lifegame.framework;

import android.graphics.Canvas;

import com.example.lifegame.game.MainScene;
import com.example.lifegame.game.Player;

import java.util.Random;

public class MapLoaderObject implements IGameObject {
    private final Player player;
    private Random random = new Random();
    private float BottomX, itemX;

    private final float[] ratios = {4f, 5f, 6f}; // 각 지점의 비율
    private int ratioIndex = 0; // 비율 인덱스

    private ChoiceObj choiceObj;


    private ChoiceObj[] choiceObjs; // ChoiceObj 배열

    boolean musicSpawned= false;
    boolean studySpawned= false;
    boolean artSpawned = false;

    private float mapScrollSpeed = 0.0f; // 맵의 스크롤 속도

    public MapLoaderObject(Player player) {

        this.player = player;

        choiceObj = new ChoiceObj(); // ChoiceObj 객체 생성
        choiceObj.init(ChoiceObj.Type.c_study, 0, -0);

        choiceObjs = new ChoiceObj[3];
        choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.c_study, 0, 0);
        choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.c_music, 0, 0);
        choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.c_art, 0, 0);
    }

    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        float scrollSpeed = player.getBgSpeed(); // 배경의 속도

        BottomX -= scrollSpeed * BaseScene.frameTime;
        itemX -= scrollSpeed * BaseScene.frameTime;

        // 맵의 스크롤 위치가 비율에 따라 ChoiceObj 객체 생성
        if (player.isAgeChanged()) {
            int playerAge = player.getAge();
            if (playerAge == 1) { // 플레이어의 나이가 1일 때
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.c_art, 5, 5.6f);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.c_music, 8, 5.6f);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.c_study, 13, 5.6f);

            }
            else if (playerAge == 2) { // 플레이어의 나이가 2일 때
                System.out.println("2일때");
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.c_study, 5, 5.6f);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.c_music, 8, 5.6f);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.c_art, 13, 5.6f);


            } else if (playerAge == 3) { // 플레이어의 나이가 3일 때
                System.out.println("3일때");
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.c_study, 5, 5.6f);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.c_music, 8, 5.6f);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.c_art, 13, 5.6f);
            }
            else if (playerAge == 4) { // 플레이어의 나이가 3일 때
                System.out.println("4일때");
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.c_study, 5, 5.6f);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.c_music, 8, 5.6f);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.c_art, 13, 5.6f);
            }else { // 플레이어의 나이에 해당하지 않는 경우
//                choiceObj = null; // ChoiceObj를 null로 설정
//                choiceObj = new ChoiceObj(); // ChoiceObj 객체 생성
//                choiceObj.init(ChoiceObj.Type.c_study, 5, 0.0f);
            }
            scene.add(MainScene.Layer.choiceobj, choiceObjs[0]);
            scene.add(MainScene.Layer.choiceobj, choiceObjs[1]);
            scene.add(MainScene.Layer.choiceobj, choiceObjs[2]);

           // scene.add(MainScene.Layer.choiceobj, choiceObj);
        }

    }


    public void setMapScrollSpeed(float scrollSpeed) {
        this.mapScrollSpeed = scrollSpeed;
    }
    public void draw(Canvas canvas) {
//        // ChoiceObj가 있을 때만 그림
//        if (choiceObj != null) {
//            choiceObj.draw(canvas); // ChoiceObj를 그림 (필요한 로직으로 대체)
//        }
//
//        // ChoiceObj가 있을 때만 그림
//        if (choiceObjs != null) {
//            for (ChoiceObj choiceObj : choiceObjs) {
//                if (choiceObj != null && choiceObj.isSpawned()) {
//                    choiceObj.draw(canvas);
//                    System.out.println("ajdla;");
//
//                }
//            }
//        }

    }
}
