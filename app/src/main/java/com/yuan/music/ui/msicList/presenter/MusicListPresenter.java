package com.yuan.music.ui.msicList.presenter;

import android.content.Context;

import com.yuan.music.bean.MusicFiles;

import java.util.List;

/**
 * Created by YUAN on 2016/11/3.
 */

public interface MusicListPresenter {
    List<MusicFiles> setPath(Context context, String path);

}
