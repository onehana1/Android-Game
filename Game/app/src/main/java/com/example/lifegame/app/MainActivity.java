package com.example.lifegame.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.GameView;
import com.example.lifegame.game.MainScene;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);

        new MainScene().pushScene();
    }

    protected void onPause() {
        gameView.pauseGame();
        super.onPause();
    }
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
    }
    protected void onDestroy() {
        BaseScene.popAll();
        super.onDestroy();
    }
    public void onBackPressed() {
        if (gameView.handleBackKey()) {
            return;
        }
        super.onBackPressed();
    }
}