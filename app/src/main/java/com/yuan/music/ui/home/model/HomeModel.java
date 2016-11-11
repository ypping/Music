package com.yuan.music.ui.home.model;

import android.content.Context;
import android.database.Cursor;

import com.yuan.music.bean.Folder;
import com.yuan.music.bean.MusicFiles;

import java.util.List;

/**
 * Created by YUAN on 2016/10/26
 */

public interface HomeModel {
    List<Folder> getDirectoryName(List<MusicFiles> list);

    Cursor findMusic(Context context, String selection,
                     String[] selectionArgs, String sortOrder);
    Cursor findMusic(Context context,String path, String selection,
                     String[] selectionArgs, String sortOrder);
    List<MusicFiles> addMusic(Cursor cursor);

    List<Folder> getClassify(List<MusicFiles> musicFiles, List<Folder> folder);
}