package com.example.lifegame.game;

import com.example.lifegame.R;
import com.example.lifegame.framework.AnimSprite;
import com.example.lifegame.framework.BaseScene;

public class MainScene extends BaseScene {

    private final Player player;

    public enum Layer {
        bg1, player, COUNT
    }

    public MainScene() {
        player = new Player();
        add(player);
        add(new HorzScrollBackground(R.mipmap.school, 0.2f));

       // AnimSprite animSprite = new AnimSprite(R.mipmap.pstudent, 4.5f, 5.0f, 1.5f, 2.22f, 1.f, 0);
      //  add(animSprite);

    }



}
