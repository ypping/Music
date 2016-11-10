package com.yuan.mymusic.ui.home.presenter;

import android.content.Context;

import com.yuan.mymusic.bean.Folder;

import java.util.List;

/**
 * Created by YUAN on 2016/10/26.
 */

public interface HomePresenter {

    List<Folder> getData(Context context, List<Folder> folders);
}
