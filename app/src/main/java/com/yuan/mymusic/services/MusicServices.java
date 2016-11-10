package com.yuan.mymusic.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.io.IOException;

/**
 * 音乐播放的服务
 * Created by YUAN on 2016/11/2.
 */

public class MusicServices extends Service {
    private final String TAG = "MusicServices";
    private MusicBinder musicBinder;
    /**
     * 全局定义一个media play方便管理
     */
    private MediaPlayer mediaPlayer = new MediaPlayer();

    class MusicBinder extends Binder {
        public Service getServices() {
            return MusicServices.this;
        }
    }

    /**
     * 播放音乐
     *
     * @param path
     * @throws IOException
     */
    public void playMusic(String path) throws IOException {
        if (mediaPlayer.isPlaying()) {
            stopMusic();
        }
        mediaPlayer.reset();
        mediaPlayer.setDataSource(path);
        mediaPlayer.pause();
        mediaPlayer.start();
    }

    /**
     * 音乐停止
     */
    public void stopMusic() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.stop();
    }

    /**
     * 音乐暂停
     */
    public void pauseMusic() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBinder;
    }

    public MusicServices() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
