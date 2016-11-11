package com.yuan.music;

import android.app.Application;

import com.yuan.music.utils.UtilLog;

/**
 * Created by YUAN on 2016/10/20.
 */

public class MusicApplication extends Application {
    private final String TAG = "MusicApplication";
    private static MusicApplication musicApplication;

    public static MusicApplication getInstater() {
        if (musicApplication == null) {
            musicApplication = new MusicApplication();
        }
        return musicApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        UtilLog.setDebugMode(true);
    }
}
