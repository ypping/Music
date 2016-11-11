package com.yuan.music.ui.msicList.presenter;


import android.content.Context;

import com.yuan.music.bean.MusicFiles;
import com.yuan.music.ui.msicList.model.MusicListModel;
import com.yuan.music.ui.msicList.model.MusicListModelImpl;
import com.yuan.music.ui.msicList.view.MusicListView;

import java.util.List;

/**
 * Created by YUAN on 2016/11/03
 */

public class MusicListPresenterImpl implements MusicListPresenter {
    private final String TAG = "MusicListPresenterImpl";
    private MusicListModel musicListModel;
    private MusicListView musicListView;

    public MusicListPresenterImpl(MusicListView musicListView) {
        this.musicListView = musicListView;
        initModel();
    }


    @Override
    public List<MusicFiles> setPath(Context context,String path) {
        return musicListModel.setPath(context,path);
    }

    private void initModel() {
        musicListModel = new MusicListModelImpl();
    }
}