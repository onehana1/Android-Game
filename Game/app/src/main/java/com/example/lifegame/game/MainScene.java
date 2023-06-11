package com.example.lifegame.game;

import com.example.lifegame.R;
import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.Button;
import com.example.lifegame.framework.IGameObject;
import com.example.lifegame.framework.MapLoader;
import com.example.lifegame.framework.MapLoaderObject;
import com.example.lifegame.framework.Metrics;
import com.example.lifegame.framework.PauseScene;

import java.util.ArrayList;

public class MainScene extends BaseScene {
    private final Player player;
    private final float bgspeed = 1.0f;

    public enum Layer {
        bg, item_1, choiceobj, player, ui,touch,coolui, controller, COUNT
        }

    public enum Choiced {
       freind, h_study, h_paint, h_music, COUNT
    }
    public Score score;

    public cooltime cooltime1,cooltime2,cooltime3,cooltimeChoice;

    public Button hobbyButton1;
    public Button hobbyButton2;
    public Button hobbyButton3;

    public Button jumpButton,choiceButton, pauseButton;




    private ArrayList<Integer> imageResourceslist;

    int[] imageResources = {R.mipmap.playground1, R.mipmap.school1, R.mipmap.company1, R.mipmap.house1, R.mipmap.house_1, R.mipmap.endingmap , R.mipmap.house2, R.mipmap.house_2, R.mipmap.endingmap ,R.mipmap.house3, R.mipmap.house_3, R.mipmap.endingmap };

    public MainScene() {
        Metrics.setGameSize(16.0f, 9.0f);
        initLayers(Layer.COUNT);
        imageResourceslist = new ArrayList<>();
        imageResourceslist.add(R.mipmap.playground1);
        imageResourceslist.add(R.mipmap.school1);


        player = new Player();
        add(Layer.player, player);

       // add(new HorzScrollBackground(R.mipmap.school, 0.2f));
        HorzScrollBackground background = new HorzScrollBackground(imageResourceslist, bgspeed, player);
        add(Layer.bg, background);

        // 플레이어의 나이 설정
        background.setPlayerAgeBasedOnCurrentImage();

        jumpButton =  new Button(R.mipmap.jump, R.mipmap.jump, 14.5f, 7.7f, 3.0f, 2.0f, new Button.Callback(){
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    player.jump();
                }
                return true;
            }
        });
        jumpButton.setActivation(true);
        add(Layer.touch, jumpButton) ;

        choiceButton =  new Button(R.mipmap.choice_c, R.mipmap.choice, 11.5f, 7.7f, 3.0f, 2.0f, new Button.Callback(){
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    player.choice();

                    cooltimeChoice.setCoolingTime(3);
                    choiceButton.setCooling(true);

                }

                if(player.getHobby()==Player.Hobby.PAINT)
                    hobbyButton3.setActivation(true);
                if(player.getHobby()==Player.Hobby.STUDY)
                    hobbyButton2.setActivation(true);
                if(player.getHobby()==Player.Hobby.MUSIC)
                    hobbyButton1.setActivation(true);

                return true;
            }
        });

        choiceButton.setActivation(true);

        cooltimeChoice = new cooltime(R.mipmap.blue_number, 12.5f, 8.1f, 0.2f);
        cooltimeChoice.setcoltime(3);
        cooltimeChoice.setButtoncool(choiceButton); // cooltime 클래스에 hobbyButton 설정
        add(Layer.coolui, cooltimeChoice);
        add(Layer.touch, choiceButton);



        add(Layer.touch, choiceButton);

        hobbyButton1 = new Button(R.mipmap.hobby_1, R.mipmap.hobby_1_cool,  1.0f, 8.4f, 1.0f, 1.0f, new Button.Callback() {
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed && !cooltime1.getisCooling()) {
                    player.pressHobby();
                    cooltime1.setCoolingTime(5); // 5초 동안 쿨타임 설정
                    System.out.println("눌렀다");
                    hobbyButton1.setCooling(true); // 버튼을 쿨타임 상태로 설정
                }
                return true;
            }
        });

        cooltime1 = new cooltime(R.mipmap.red_number, 1.22f, 8.1f, 0.4f);
        cooltime1.setcoltime(5);
        cooltime1.setButtoncool(hobbyButton1); // cooltime 클래스에 hobbyButton 설정
        add(Layer.coolui, cooltime1);
        add(Layer.touch, hobbyButton1);




        hobbyButton2 = new Button(R.mipmap.hobby_2, R.mipmap.hobby_2_cool,  2.3f, 8.4f, 1.0f, 1.0f, new Button.Callback() {
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed && !cooltime2.getisCooling()) {
                    player.pressHobby();
                    cooltime2.setCoolingTime(5); // 5초 동안 쿨타임 설정
                    System.out.println("눌렀다");
                    hobbyButton2.setCooling(true); // 버튼을 쿨타임 상태로 설정
                }
                return true;
            }
        });

        cooltime2 = new cooltime(R.mipmap.red_number, 2.56f, 8.1f, 0.4f);
        cooltime2.setcoltime(5);
        cooltime2.setButtoncool(hobbyButton2); // cooltime 클래스에 hobbyButton 설정
        add(Layer.coolui, cooltime2);
        add(Layer.touch, hobbyButton2);



        hobbyButton3 = new Button(R.mipmap.hobby_3, R.mipmap.hobby_3_cool, 3.5f, 8.5f, 1.0f, 1.0f, new Button.Callback() {
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed && !cooltime3.getisCooling()) {
                    player.pressHobby();
                    cooltime3.setCoolingTime(5); // 5초 동안 쿨타임 설정
                    System.out.println("눌렀다");
                    hobbyButton3.setCooling(true); // 버튼을 쿨타임 상태로 설정
                }
                return true;
            }
        });



        cooltime3 = new cooltime(R.mipmap.red_number, 3.75f, 8.1f, 0.4f);
        cooltime3.setcoltime(5);
        cooltime3.setButtoncool(hobbyButton3); // cooltime 클래스에 hobbyButton 설정
        add(Layer.coolui, cooltime3);
        add(Layer.touch, hobbyButton3);




        score = new Score(R.mipmap.gold_number, 14.5f, 0.2f, 0.5f);
        score.setScore(100);
        add(Layer.ui, score);

        pauseButton = new Button(R.mipmap.pause, R.mipmap.pause,15.50f, 0.5f, 0.6f, 0.6f, new Button.Callback() {
            public boolean onTouch(Button.Action action) {
                if (action == Button.Action.pressed) {
                    new PauseScene().pushScene();
                }
                return true;
            }
        });
        pauseButton.setActivation(true);
        add(Layer.touch, pauseButton);

        add(Layer.controller, new MapLoader(player));
        add(Layer.choiceobj, new MapLoaderObject(player));

        add(Layer.controller, new CollisionChecker(player));
    }


    protected void onStart() {
       // Sound.playMusic(R.raw.main);
    }


    protected void onEnd() {
        //Sound.stopMusic();
    }


    protected void onPause() {
       // Sound.pauseMusic();
        System.out.println("dd");
    }


    protected void onResume() {
       // Sound.resumeMusic();
    }

    protected int getTouchLayerIndex() {
        return Layer.touch.ordinal();
    }


    public boolean handleBackKey() {
        new PauseScene().pushScene();
        return true;
    }

    public void update(long elapsedNanos) {
        frameTime = elapsedNanos / 1_000_000_000f;
        for (ArrayList<IGameObject> objects: layers) {
            for (int i = objects.size() - 1; i >= 0; i--) {
                IGameObject gobj = objects.get(i);
                gobj.update();
            }
        }

        if (score.getHP() <= 0) {
            // 엔딩씬으로 전환
            //BaseScene.getTopScene().changeToEndingScene();
            new EndingScene().pushScene();
        }

        if (score.getSmokeScore() >= 7) {
            // 엔딩씬으로 전환
            BaseScene.getTopScene().changeToEndingScene();
            // new EndingScene().pushScene();
        }

        if (player.getAge() == 6) {
            BaseScene.getTopScene().changeToEndingScene();
            // new EndingScene().pushScene();
        }


    }


}
