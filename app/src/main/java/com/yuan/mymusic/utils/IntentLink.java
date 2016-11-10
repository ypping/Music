package com.yuan.mymusic.utils;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by YUAN on 2016/9/8.
 */
public class IntentLink {
    /**
     * 判断是否有网络
     *
     * @return
     */
    public static boolean isInternet(Context context) {
        ConnectivityManager connection = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connection.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    /**
     * 判断网络的类型
     *
     * @return
     */
    public static int getInternetType(Context context) {
        int netType = 0;
        ConnectivityManager connection = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connection.getActiveNetworkInfo();
        if (networkInfo == null) {
            context.startActivity(new Intent(Settings.ACTION_SETTINGS));
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String info = networkInfo.getExtraInfo();
            if (info.toLowerCase().equals("3gnet")) {
                netType = 3;
                Log.i("infoto", "info:" + info.toLowerCase().toLowerCase());
            } else {
                netType = 2;
                Log.i("infoto", "info:" + info.toLowerCase().toLowerCase());
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = 1;
        }
        return netType;
    }

}
