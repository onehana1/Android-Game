package com.example.lifegame.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;


import com.example.lifegame.R;
import com.example.lifegame.framework.BaseScene;
import com.example.lifegame.framework.BitmapPool;
import com.example.lifegame.framework.ChoiceObj;
import com.example.lifegame.framework.Metrics;
import com.example.lifegame.framework.Sprite;

import java.util.ArrayList;
import java.util.Collections;


public class HorzScrollBackground extends Sprite {
    private final Player player;
    private float speed;
    private float width;
    private float scroll;
    private float scroll2;
    private int currentIndex;

    static int forhouse =0;
    static int forend =0;
    static int forjob =0;

    int film = 0;


    private int[] bitmapResIdsS;

    private ArrayList<Integer> bitmapResIds;
    private ArrayList<Integer> bitmapReverseResIds;


    public HorzScrollBackground(ArrayList<Integer> bitmapResIds, float speed, Player player) {
        super(bitmapResIds.get(0), Metrics.game_width / 2, Metrics.game_height / 2, Metrics.game_width, Metrics.game_height);
        this.bitmapResIds = bitmapResIds;


        this.speed = speed;
        this.player = player;
        this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
        setSize(Metrics.game_height, width);
        this.currentIndex = 0;
    }

    public void setPlayerAgeBasedOnCurrentImage() {
        int age = 1;
        if (currentIndex >= 0 && currentIndex < bitmapResIds.size()) {
            int currentImageResId = bitmapResIds.get(currentIndex);
            // 이미지 리소스 ID에 따라 플레이어의 나이를 설정
            if (film == 0) {
                if (currentImageResId == R.mipmap.playground1) {
                    this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
                    setSize(Metrics.game_height, width);
                    age = 1;

                } else if (currentImageResId == R.mipmap.school1) {
                    this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
                    setSize(Metrics.game_height, width);
                    age = 2;
                } else if (currentImageResId == R.mipmap.company1 || currentImageResId == R.mipmap.paintermap || currentImageResId == R.mipmap.singermap) {
                    this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
                    setSize(Metrics.game_height, width);
                    age = 3;
                } else if (currentImageResId == R.mipmap.house1 || currentImageResId == R.mipmap.house2 || currentImageResId == R.mipmap.house3) {
                    this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
                    setSize(Metrics.game_height, width);
                    age = 4;
                } else if (currentImageResId == R.mipmap.officemap2 || currentImageResId == R.mipmap.singermap2 || currentImageResId == R.mipmap.paintermap2) {
                    this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
                    setSize(Metrics.game_height, width);
                    age = 5;
                } else if (currentImageResId == R.mipmap.house_1 || currentImageResId == R.mipmap.house_2 || currentImageResId == R.mipmap.house_3) {
                    this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
                    setSize(Metrics.game_height, width);
                    age = 6;
                } else if (currentImageResId == R.mipmap.house_1c || currentImageResId == R.mipmap.house_2c || currentImageResId == R.mipmap.house_3c) {
                    this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
                    setSize(Metrics.game_height, width);
                    age = 6;
                } else if (currentImageResId == R.mipmap.endingmap) {
                    this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
                    setSize(Metrics.game_height, width);
                    age = 7;
                    film = 1;
                }
            } else if (film == 1) {
                //recurrentImageResId = bitmapReverseResIds.get(currentIndex);

                this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
                setSize(Metrics.game_height, width);
                age = 8;

                if (currentImageResId == R.mipmap.playground1) {
                    System.out.println("엔딩씬으로가자");
                    film = 2;
                    player.setFilm(2);
                }

            } else {
                System.out.println("버그다!!!!!!!!!");

                this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
                setSize(Metrics.game_height, width);
                age = 8;

                if (currentImageResId == R.mipmap.endingmap) {
                    film = 2;
                    player.setFilm(2);
                }
            }

        }

        player.setAge(age);
    }

    @Override
    public void update() {
        MainScene scene = (MainScene) BaseScene.getTopScene();


        if (player.getBgSpeed() != 0)
            speed = player.getBgSpeed();

        scroll -= speed * BaseScene.frameTime;
        scroll2 += speed * BaseScene.frameTime;


        // 이미지 전환을 위해 체크
        if(film==0)
        if (scroll <= -width) {
            scroll += width;
            this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
            setSize(Metrics.game_height, width);

            currentIndex++;

             {
                if (currentIndex == 1) {
                    if (player.getJob() == Player.JOB.employee) {
                        bitmapResIds.add(R.mipmap.company1);
                    }
                    else if (player.getJob() == Player.JOB.painter) {
                        bitmapResIds.add(R.mipmap.paintermap);
                    }
                    else if (player.getJob() == Player.JOB.singer) {
                        bitmapResIds.add(R.mipmap.singermap);

                    }
                    else {
                        bitmapResIds.add(R.mipmap.company1);
                    }
                }


                if (currentIndex == 2) {
                    if (scene.score.getMoneyScore() > 1000 && forhouse == 0) {
                        bitmapResIds.add(R.mipmap.house1);

                        forhouse = 1;
                    } else if (scene.score.getMoneyScore() > 500 && forhouse == 0) {
                        bitmapResIds.add(R.mipmap.house2);

                        forhouse = 1;
                    } else if (forhouse == 0) {
                        bitmapResIds.add(R.mipmap.house3);
                        forhouse = 1;
                    }
                }

                if (currentIndex == 3) {
                    if (player.getJob() == Player.JOB.employee && forjob == 0) {
                        bitmapResIds.add(R.mipmap.officemap2);

                        forjob = 1;
                    } else if (player.getJob() == Player.JOB.painter && forjob == 0) {
                        bitmapResIds.add(R.mipmap.paintermap2);

                        forjob = 1;
                    } else if (forjob == 0) {
                        bitmapResIds.add(R.mipmap.singermap2);

                        forjob = 1;
                    }
                }

                if (currentIndex == 4) {
                    if (scene.score.getMoneyScore() > 1000 && forhouse == 1) {
                        bitmapResIds.add(R.mipmap.house_1c);
                        bitmapResIds.add(R.mipmap.endingmap);
                        forhouse = 2;
                    } else if (scene.score.getMoneyScore() > 500 && forhouse == 1) {
                        bitmapResIds.add(R.mipmap.house_2c);
                        bitmapResIds.add(R.mipmap.endingmap);
                        forhouse = 2;
                    } else if (forhouse == 1) {
                        bitmapResIds.add(R.mipmap.house_3c);
                        bitmapResIds.add(R.mipmap.endingmap);
                        forhouse = 2;
                    }
                }
            }

        }


//        if (currentIndex >= bitmapResIds.size()) {
//            currentIndex = 0;
//            System.out.println("오면안됨...");
//        }

        // 플레이어의 나이 업데이트
        setPlayerAgeBasedOnCurrentImage();

        if(film==1){
            if (scroll <= -width) {
                scroll += width;

                currentIndex--;

                if (currentIndex < 0) {
                    currentIndex = bitmapReverseResIds.size() - 1;
                }

                this.width = bitmap.getWidth() * Metrics.game_height / bitmap.getHeight();
                setSize(Metrics.game_height, width);

            }
            // bitmapResIds 리스트를 역순으로 정렬
            if (player.getAge() == 7) {
              //  currentIndex = 0;

//                System.out.println("역순으로 저장");
//                bitmapReverseResIds = new ArrayList<>(bitmapResIds);
//                Collections.reverse(bitmapReverseResIds);
            }
        }


    }


    @Override
    public void draw(Canvas canvas) {
        float curr = scroll % width;
        float next = curr + width;
        float nextNext = curr + width + width;

        int nextNextIndex = currentIndex; // 다다음 비트맵 리소스의 인덱스.


        if (film == 1) {
            if (bitmapResIds != null) {
                while (curr < Metrics.game_width) {
                    if (next > 0) {
                        int index = currentIndex;
                        if (next > width) {
                            index--;
                            if (index < 0) {
                                index = bitmapResIds.size() - 1;
                            }
                        }

                        setBitmapResource(bitmapResIds.get(index));
                        dstRect.set(Metrics.game_width - next, 1, Metrics.game_width - curr, Metrics.game_height - 1);
                        canvas.drawBitmap(bitmap, null, dstRect, null);
                    }

                    curr += width;
                    next += width;
                }
            }

        }
        else {

            while (curr < width) {
                if (next > 0) {
                    int index = currentIndex;
                    if (next > width) {
                        index++;
                        if (index >= bitmapResIds.size()) {
                            index = 0;
                        }
                    }

                    setBitmapResource(bitmapResIds.get(index));
                    dstRect.set(curr, 1, next, Metrics.game_height - 1);
                    canvas.drawBitmap(bitmap, null, dstRect, null);
                }

                curr += width;
                next += width;
            }
        }
    }
}