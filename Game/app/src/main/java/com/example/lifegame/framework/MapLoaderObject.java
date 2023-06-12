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


    private  float  by = 5.3f;
    private ChoiceObj choiceObj;


    private ChoiceObj[] choiceObjs; // ChoiceObj 배열

    boolean musicSpawned= false;
    boolean studySpawned= false;
    boolean artSpawned = false;
    float scrollSpeed;
    private float mapScrollSpeed = 0.0f; // 맵의 스크롤 속도

    public MapLoaderObject(Player player) {

        this.player = player;
        scrollSpeed = player.getBgSpeed(); // 배경의 속도


//        choiceObj = new ChoiceObj(); // ChoiceObj 객체 생성
//        choiceObj.init(ChoiceObj.Type.c_study, 0, -0);

        choiceObjs = new ChoiceObj[3];
        choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.c_study, 0, 0);
        choiceObjs[0].setType(ChoiceObj.Type.c_study);
        choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.c_music, 0, 0);
        choiceObjs[1].setType(ChoiceObj.Type.c_music);
        choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.c_art, 0, 0);
        choiceObjs[2].setType(ChoiceObj.Type.c_art);


    }

    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();
        scrollSpeed = player.getBgSpeed();

        BottomX -= scrollSpeed * BaseScene.frameTime;
        itemX -= scrollSpeed * BaseScene.frameTime;



        // 맵의 스크롤 위치가 비율에 따라 ChoiceObj 객체 생성
        if (player.isAgeChanged()) {
            int playerAge = player.getAge();
            Player.JOB job = player.getJob();

            if (playerAge == 1) { // 플레이어의 나이가 1일 때
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.c_art, 8, by);
                choiceObjs[0].setType(ChoiceObj.Type.c_art);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.c_music, 15, by);
                choiceObjs[1].setType(ChoiceObj.Type.c_music);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.c_study, 23, by);
                choiceObjs[2].setType(ChoiceObj.Type.c_study);
            }
            else if (playerAge == 2) { // 플레이어의 나이가 2일 때
                System.out.println("2일때");
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.c_m, 17, by);
                choiceObjs[0].setType(ChoiceObj.Type.c_m);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.c_mm, 24, by);
                choiceObjs[1].setType(ChoiceObj.Type.c_mm);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.c_mmm, 28, by);
                choiceObjs[2].setType(ChoiceObj.Type.c_mmm);
            }
            else if (playerAge == 3&&job==Player.JOB.employee) { // 플레이어의 나이가 3일 때
                System.out.println("3일때");
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.c_off1, 17, by);
                choiceObjs[0].setType(ChoiceObj.Type.c_off1);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.c_off2, 24, by);
                choiceObjs[1].setType(ChoiceObj.Type.c_off2);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.c_off3, 28, by);
                choiceObjs[2].setType(ChoiceObj.Type.c_off3);
            }
            else if (playerAge == 3&&job==Player.JOB.painter) { // 플레이어의 나이가 4일 때
                System.out.println("3일때");
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.p1, 17, by);
                choiceObjs[0].setType(ChoiceObj.Type.p1);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.p2, 24, by);
                choiceObjs[1].setType(ChoiceObj.Type.p2);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.p3, 28, by);
                choiceObjs[2].setType(ChoiceObj.Type.p3);
            }
            else if (playerAge == 3&&job==Player.JOB.singer) { // 플레이어의 나이가 4일 때
                System.out.println("3일때");
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.s1, 17, by);
                choiceObjs[0].setType(ChoiceObj.Type.s1);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.s2, 24, by);
                choiceObjs[1].setType(ChoiceObj.Type.s2);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.s3, 28, by);
                choiceObjs[2].setType(ChoiceObj.Type.s3);
            }
            else if (playerAge == 3&&job==Player.JOB.employee) { // 플레이어의 나이가 3일 때
                System.out.println("3일때");
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.c_off1, 17, by);
                choiceObjs[0].setType(ChoiceObj.Type.c_off1);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.c_off2, 24, by);
                choiceObjs[1].setType(ChoiceObj.Type.c_off2);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.c_off3, 28, by);
                choiceObjs[2].setType(ChoiceObj.Type.c_off3);
            }
            else if (playerAge == 4) { // 플레이어의 나이가 4일 때
                System.out.println("4일때");
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.b1, 17, by);
                choiceObjs[0].setType(ChoiceObj.Type.s1);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.b2, 24, by);
                choiceObjs[1].setType(ChoiceObj.Type.s2);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.b2, 0, -by);
                choiceObjs[2].setType(ChoiceObj.Type.s2);
            }

            else if (playerAge == 5&&job==Player.JOB.painter) { // 플레이어의 나이가 4일 때
                System.out.println("3일때");
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.p1, 17, by);
                choiceObjs[0].setType(ChoiceObj.Type.p1);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.p2, 24, by);
                choiceObjs[1].setType(ChoiceObj.Type.p2);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.p3, 28, by);
                choiceObjs[2].setType(ChoiceObj.Type.p3);
            }
            else if (playerAge == 5&&job==Player.JOB.singer) { // 플레이어의 나이가 4일 때
                System.out.println("3일때");
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.s1, 17, by);
                choiceObjs[0].setType(ChoiceObj.Type.s1);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.s2, 24, by);
                choiceObjs[1].setType(ChoiceObj.Type.s2);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.s3, 28, by);
                choiceObjs[2].setType(ChoiceObj.Type.s3);
            }
            else if (playerAge == 5&&job==Player.JOB.employee) { // 플레이어의 나이가 3일 때
                System.out.println("3일때");
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.c_off1, 17, by);
                choiceObjs[0].setType(ChoiceObj.Type.c_off1);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.c_off2, 24, by);
                choiceObjs[1].setType(ChoiceObj.Type.c_off2);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.c_off3, 28, by);
                choiceObjs[2].setType(ChoiceObj.Type.c_off3);
            }

            else if (playerAge == 6) { // 플레이어의 나이가 4일 때
                System.out.println("6일때");
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.b1, 17, by);
                choiceObjs[0].setType(ChoiceObj.Type.s1);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.b2, 24, by);
                choiceObjs[1].setType(ChoiceObj.Type.s2);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.b2, 0, -by);
                choiceObjs[2].setType(ChoiceObj.Type.s2);
            }

            else { // 플레이어의 나이에 해당하지 않는 경우
                choiceObjs = new ChoiceObj[3];
                choiceObjs[0] = ChoiceObj.get(ChoiceObj.Type.b1, 17, -by);
                choiceObjs[0].setType(ChoiceObj.Type.s1);
                choiceObjs[1] = ChoiceObj.get(ChoiceObj.Type.b2, 24, -by);
                choiceObjs[1].setType(ChoiceObj.Type.s2);
                choiceObjs[2] = ChoiceObj.get(ChoiceObj.Type.b2, 28, -by);
                choiceObjs[2].setType(ChoiceObj.Type.s2);
            }



            scene.add(MainScene.Layer.choiceobj, choiceObjs[0]);
            scene.add(MainScene.Layer.choiceobj, choiceObjs[1]);
            scene.add(MainScene.Layer.choiceobj, choiceObjs[2]);

           // scene.add(MainScene.Layer.choiceobj, choiceObj);
        }

        choiceObjs[0].setSpeed(scrollSpeed);
        choiceObjs[1].setSpeed(scrollSpeed);
        choiceObjs[2].setSpeed(scrollSpeed);

    }


    public void setMapScrollSpeed(float scrollSpeed) {
        this.mapScrollSpeed = scrollSpeed;
    }
    public void draw(Canvas canvas) {
        // ChoiceObj가 있을 때만 그림
        if (choiceObj != null) {
            choiceObj.draw(canvas); // ChoiceObj를 그림 (필요한 로직으로 대체)
        }

        // ChoiceObj가 있을 때만 그림
        if (choiceObjs != null) {
            for (ChoiceObj choiceObj : choiceObjs) {
                if (choiceObj != null && choiceObj.isSpawned()) {
                    choiceObj.draw(canvas);
                //    System.out.println("ajdla;");

                }
            }
        }

    }
}
