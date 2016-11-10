package com.yuan.mymusic.ui.msicList.presenter;


import com.yuan.mymusic.bean.MusicFiles;
import com.yuan.mymusic.ui.msicList.model.MusicListModel;
import com.yuan.mymusic.ui.msicList.model.MusicListModelImpl;
import com.yuan.mymusic.ui.msicList.view.MusicListView;

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
    public List<MusicFiles> setPath(String path) {
        return musicListModel.setPath(path);
    }

    private void initModel() {
        musicListModel = new MusicListModelImpl();
    }
}