package com.yuan.music.ui.msicList.model;


import android.content.Context;

import com.yuan.music.bean.MusicFiles;
import com.yuan.music.utils.UtilLog;
import com.yuan.music.utils.util.CurSorUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YUAN on 2016/11/03
 */

public class MusicListModelImpl implements MusicListModel {
    private final String TAG = "MusicListModelImpl: ";
    Context context;

    public List<MusicFiles> setPath(Context context, String path) {
        List<MusicFiles> musicFiles = new ArrayList<>();
        UtilLog.e(TAG, TAG + "path: " + path);
        musicFiles = CurSorUtils.addMusic(CurSorUtils.findMusic(context, path, null, null, null));
        return musicFiles;
    }
}