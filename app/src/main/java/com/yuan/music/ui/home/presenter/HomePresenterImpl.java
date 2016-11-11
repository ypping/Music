package com.yuan.music.ui.home.presenter;


import android.content.Context;

import com.yuan.music.bean.Folder;
import com.yuan.music.bean.MusicFiles;
import com.yuan.music.ui.home.model.HomeModel;
import com.yuan.music.ui.home.model.HomeModelImpl;
import com.yuan.music.ui.home.view.HomeView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YUAN on 2016/10/26
 */

public class HomePresenterImpl implements HomePresenter {
    private HomeView homeView;
    private HomeModel homeModel;

    public HomePresenterImpl(HomeView homeView) {
        this.homeView = homeView;
        initHomePresent();
    }

    @Override
    public List<Folder> getData(Context context, List<Folder> folders) {
        List<MusicFiles> musicFiles = new ArrayList<>();
        musicFiles.addAll(homeModel.addMusic(homeModel.findMusic(context, null, null, null)));
        folders.addAll(homeModel.getDirectoryName(musicFiles));
        folders = homeModel.getClassify(musicFiles, folders);
        return folders;
    }

    private void initHomePresent() {
        homeModel = new HomeModelImpl();
    }
}