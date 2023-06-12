package com.example.lifegame.game;

import static com.example.lifegame.game.MainScene.Layer.player;

import android.view.MotionEvent;

import com.example.lifegame.R;
import com.example.lifegame.app.TitleActivity;
import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.Button;
import com.example.lifegame.framework.IGameObject;
import com.example.lifegame.framework.Metrics;
import com.example.lifegame.framework.Sprite;

import java.util.ArrayList;

public class EndingScene extends BaseScene {
    private final float bgX = 8.0f;
    private final float bgY = 4.5f;

    public enum Layer {
        bg,  touch,button, COUNT
    }

    private ArrayList<Integer> endingImages;

    private int currentImageIndex = 0;
    private boolean lastImageShown = false;

    private Button backButton;

    public EndingScene() {

        Metrics.setGameSize(16.0f, 9.0f);
        initLayers(Layer.COUNT);

        MainScene scene = (MainScene) BaseScene.getTopScene();
        int coin = scene.score.getMoneyScore();
        int smoke = scene.score.getSmokeScore();
        int hobby = scene.score.getHobbyScore();
        int friend = scene.score.getFriendScore();
        int happy = scene.score.getHappyScore();



        int hp = scene.score.getHP();
        endingImages = new ArrayList<>();



        // 엔딩 이미지 추가
        if(coin<200){
            endingImages.add( R.mipmap.poorending);
          // add(Layer.bg, new Sprite(R.mipmap.poorending, bgX, bgY, 16.0f, 9.0f));
        }
        else if(coin<1500){
            endingImages.add( R.mipmap.normalending);
          //  add(Layer.bg, new Sprite(R.mipmap.normalending, bgX, bgY, 16.0f, 9.0f));
        }
        else {
            endingImages.add( R.mipmap.richending);
          //  add(Layer.bg, new Sprite(R.mipmap.richending, bgX, bgY, 16.0f, 9.0f));

        }

        if(hobby<30)
            endingImages.add( R.mipmap.nohobbyending);
        else
            endingImages.add( R.mipmap.hobbyending);

        if(friend<3)
            endingImages.add( R.mipmap.aloneending);
        else
            endingImages.add( R.mipmap.friendending);

        if(happy<1000)
            endingImages.add( R.mipmap.depressionending);
        else
            endingImages.add( R.mipmap.excitiedending);


        if(smoke>=170)
        {   endingImages.clear();
            endingImages.add( R.mipmap.smoke_ending);
            // add(Layer.bg, new Sprite(R.mipmap.smoke_ending, bgX, bgY, 16.0f, 9.0f));
        }

        // 최소한 한 개의 이미지가 있는지 확인
        if (endingImages.isEmpty()) {
            endingImages.add(R.mipmap.gameover);
        }

    }

    private void addEndingImage(int imageResource) {
        endingImages.add(imageResource);
    }

    public boolean onTouchEvent(MotionEvent event) {
        boolean handled = super.onTouchEvent(event);
        if (!handled && event.getAction() == MotionEvent.ACTION_DOWN) {
            showNextImage();
            return true;
        }
        return handled;
    }

    private void showNextImage() {
        if (lastImageShown) {
            return; // 이미 마지막 이미지를 표시했으면 더 이상 진행하지 않음
        }

        if (currentImageIndex < endingImages.size()) {
            // 다음 이미지 표시
            int imageResource = endingImages.get(currentImageIndex);
            add(Layer.bg, new Sprite(imageResource, bgX, bgY, 16.0f, 9.0f));
            currentImageIndex++;
        } else {
            // 마지막 이미지에서 멈춤
            add(Layer.bg, new Sprite(R.mipmap.gameover, bgX, bgY, 16.0f, 9.0f));
            lastImageShown = true;
            addBackButton();
        }
    }
    private void removeTouchButton() {
        ArrayList<IGameObject> touchObjects = getObjectsAt(Layer.touch);
        if (!touchObjects.isEmpty()) {
            IGameObject touchButton = touchObjects.get(0);
            remove(Layer.touch, touchButton);
        }
    }

    private void addBackButton() {
        backButton = new Button(R.mipmap.regame, R.mipmap.regame, 13.0f, 2.0f, 4.0f, 2.0f, new Button.Callback() {
            @Override
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    returnToTitle();
                }
                return true;
            }
        });
        backButton.setActivation(true);
        add(Layer.touch, backButton);
    }


    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }

    private void returnToTitle() {
        // EndingScene을 종료하고 TitleActivity로 돌아감
        finishActivity();
    }
}
