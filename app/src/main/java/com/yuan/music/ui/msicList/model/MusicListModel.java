package com.yuan.music.ui.msicList.model;

import android.content.Context;

import com.yuan.music.bean.MusicFiles;

import java.util.List;

/**
 * Created by YUAN on 2016/11/03
 */

public interface MusicListModel {
    List<MusicFiles> setPath(Context context,String path);
}