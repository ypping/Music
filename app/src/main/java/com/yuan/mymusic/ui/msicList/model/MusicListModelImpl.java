package com.yuan.mymusic.ui.msicList.model;


import android.content.Context;
import android.database.Cursor;

import com.yuan.mymusic.bean.MusicFiles;
import com.yuan.mymusic.utils.util.CurSorUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YUAN on 2016/11/03
 */

public class MusicListModelImpl implements MusicListModel {
    Context context;
    @Override
    public List<MusicFiles> setPath(String path) {
        List<MusicFiles> musicFiles = new ArrayList<>();
        File[] files = new File(path).listFiles();
      /*  for (File f : files) {
            MusicFiles music = new MusicFiles();
            music.setPath(f.getPath());
            music.setName(f.getName());
            musicFiles.add(music);
        }*/

        return musicFiles;
    }
}