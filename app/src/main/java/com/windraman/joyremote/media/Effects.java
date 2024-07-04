package com.windraman.joyremote.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.windraman.joyremote.R;

import java.util.HashMap;

public class Effects {

    // Sound ID can't be 0 (zero)
    public static final int SOUND_1 = 1;
    public static final int SOUND_2 = 2;
    private static final String TAG = Effects.class.toString();
    private static final Effects INSTANCE = new Effects();
    int priority = 1;
    int no_loop = 0;
    float normal_playback_rate = 1f;
    private SoundPool soundPool;
    private HashMap<Integer, Integer> soundPoolMap;
    private int volume;
    private Context context;
    private Effects() {

    }

    public static Effects getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        this.context = context;
        soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
        soundPoolMap = new HashMap<Integer, Integer>();
        soundPoolMap.put(SOUND_1, soundPool.load(context, R.raw.sound_1, 1));
        soundPoolMap.put(SOUND_2, soundPool.load(context, R.raw.sound_2, 1));
        AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        volume = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
    }

    public void playSound(int soundId) {
        //Log.i(TAG, "!!!!!!!!!!!!!! playSound_1 !!!!!!!!!!");
        soundPool.play(soundId, volume, volume, priority, no_loop, normal_playback_rate);
        //Toast.makeText(context, "Now you can hear sound effect!", Toast.LENGTH_LONG).show();
    }
}