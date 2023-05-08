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

    public MapLoaderObject(Player player) {
        this.player = player;
    }


    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();


        BottomX -= player.getBgSpeed() * BaseScene.frameTime;
        itemX -= player.getBgSpeed() * BaseScene.frameTime;


        System.out.println("초이스오브젝트: " + player.getBgSpeed());

// 맵의 스크롤 위치가 전체 너비의 1/4 지점인 경우 ChoiceObj 객체 생성

        if (BottomX < -Metrics.game_width / 7.5 && !artSpawned) {
            artSpawned = true;
            ChoiceObj artObj = new ChoiceObj();
            artObj.init(ChoiceObj.Type.c_art, Metrics.game_width, 5.6f);
            scene.add(MainScene.Layer.choiceobj, artObj);
        }
        if (BottomX < -Metrics.game_width / 4 && !studySpawned) {
            studySpawned = true;
            ChoiceObj studyObj = new ChoiceObj();
            studyObj.init(ChoiceObj.Type.c_study, Metrics.game_width, 5.6f);
            scene.add(MainScene.Layer.choiceobj, studyObj);
        }

        if (BottomX < -Metrics.game_width / 2.5 && !musicSpawned) {
            musicSpawned = true;
            ChoiceObj musicObj = new ChoiceObj();
            musicObj.init(ChoiceObj.Type.c_music, Metrics.game_width, 5.6f);
            scene.add(MainScene.Layer.choiceobj, musicObj);
        }

    }

    public void draw(Canvas canvas) {}
}