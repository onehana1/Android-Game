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
    boolean musicSpawned= false;
    boolean studySpawned= false;
    boolean artSpawned = false;

    private float mapScrollSpeed = 0.0f; // 맵의 스크롤 속도

    public MapLoaderObject(Player player) {
        this.player = player;
    }


    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();

        float scrollSpeed = player.getBgSpeed(); // 배경의 속도

        Player.State playerState = player.getState();

        BottomX -= scrollSpeed * BaseScene.frameTime;
        itemX -= scrollSpeed * BaseScene.frameTime;



        // 맵의 스크롤 위치가 비율에 따라 ChoiceObj 객체 생성
        if (BottomX < -Metrics.game_width / ratios[ratioIndex]) {
            ratioIndex++;
            if (ratioIndex >= ratios.length) {
                ratioIndex = 0;
            }

            float spawnX = Metrics.game_width;
            float spawnY = 5.6f;

            ChoiceObj choiceObj;
            // 원하는 숫자에 따라 ChoiceObj 객체 생성 및 초기화
            if (ratioIndex == 1 && !artSpawned) {
                choiceObj = new ChoiceObj();
                choiceObj.init(ChoiceObj.Type.c_art, spawnX  - 10.0f, spawnY);
                artSpawned = true;
            } else if (ratioIndex == 2 && !studySpawned) {
                choiceObj = new ChoiceObj();
                choiceObj.init(ChoiceObj.Type.c_study, spawnX, spawnY);
                studySpawned = true;
            } else if (ratioIndex == 0 && !musicSpawned) {
                choiceObj = new ChoiceObj();
                choiceObj.init(ChoiceObj.Type.c_music, spawnX, spawnY);
                musicSpawned = true;
            } else {
                return; // 이미 생성된 ChoiceObj가 있는 경우 종료
            }

            choiceObj.setSpeed((player.getBgSpeed()));
            scene.add(MainScene.Layer.choiceobj, choiceObj);
        }



    }


    public void setMapScrollSpeed(float scrollSpeed) {
        this.mapScrollSpeed = scrollSpeed;
    }
    public void draw(Canvas canvas) {}
}
