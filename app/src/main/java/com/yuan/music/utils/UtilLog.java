package com.yuan.music.utils;

import android.util.Log;

/**
 * 日志输出类
 * Created by luis li .
 * company:hyrt.
 * date:2016/2/16.
 */
public class UtilLog {
    private static boolean isDebug;

    /**
     * 设置处于Debug模式，可以输出日志
     */
    public static void setDebugMode(boolean isdebug) {
        isDebug = isdebug;
    }

    public static void d(String tag, String msg) {
        if (isDebug) {
            Log.d(tag, msg);
        }
    }
    public static void v(String msg) {
        if (isDebug) {
            Log.d("luo", msg);
        }
    }
    public static void i(String tag, String msg) {
        if (isDebug) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (isDebug) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }

}
