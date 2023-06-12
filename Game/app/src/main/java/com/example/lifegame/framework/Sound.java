package com.example.lifegame.framework;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import java.util.HashMap;

public class Sound {
    protected static MediaPlayer mediaPlayer;
    protected static SoundPool soundPool;
    private static boolean isSoundPoolLoaded = false;
    private static HashMap<Integer, Integer> soundIdMap = new HashMap<>();
    private static HashMap<Integer, Boolean> soundLoadingMap = new HashMap<>();

    public static void playMusic(int resId) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        mediaPlayer = MediaPlayer.create(GameView.view.getContext(), resId);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    public static void stopMusic() {
        if (mediaPlayer == null) return;
        mediaPlayer.stop();
        mediaPlayer = null;
    }

    public static void pauseMusic() {
        if (mediaPlayer == null) return;
        mediaPlayer.pause();
    }

    public static void resumeMusic() {
        if (mediaPlayer == null) return;
        mediaPlayer.start();
    }

    public static void playEffect(final int resId) {
        if (!isSoundPoolLoaded) {
            loadSoundPool(new OnSoundPoolLoadedListener() {
                @Override
                public void onSoundPoolLoaded() {
                    playSoundEffect(resId);
                }
            });
        } else {
            if (soundIdMap.containsKey(resId)) {
                int soundId = soundIdMap.get(resId);
                playSoundEffect(soundId);
            } else {
                loadSoundEffect(resId);
            }
        }
    }

    private static void loadSoundEffect(final int resId) {
        if (soundLoadingMap.containsKey(resId) && soundLoadingMap.get(resId)) {
            return;
        }
        soundLoadingMap.put(resId, true);

        SoundPool pool = getSoundPool();
        final int soundId = pool.load(GameView.view.getContext(), resId, 1);
        pool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundIdMap.put(resId, soundId);
                soundLoadingMap.put(resId, false);
                playSoundEffect(soundId);
            }
        });
    }

    private static void playSoundEffect(int soundId) {
        SoundPool pool = getSoundPool();
        pool.play(soundId, 1f, 0.8f, 1, 0, 1f);
    }

    private static SoundPool getSoundPool() {
        if (soundPool != null) return soundPool;

        AudioAttributes attrs = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(attrs)
                .setMaxStreams(3)
                .build();
        isSoundPoolLoaded = true;
        return soundPool;
    }

    private static void loadSoundPool(final OnSoundPoolLoadedListener listener) {
        SoundPool pool = getSoundPool();
        pool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if (listener != null) {
                    listener.onSoundPoolLoaded();
                }
            }
        });
    }

    private interface OnSoundPoolLoadedListener {
        void onSoundPoolLoaded();
    }
}