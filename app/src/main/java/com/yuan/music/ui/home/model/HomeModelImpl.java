package com.yuan.music.ui.home.model;


import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.yuan.music.bean.Folder;
import com.yuan.music.bean.MusicFiles;
import com.yuan.music.utils.UtilLog;
import com.yuan.music.utils.music.MusicComparator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by YUAN on 2016/10/26
 */

public class HomeModelImpl implements HomeModel {
    private final String TAG = "HomeModelImpl";

    @Override
    public List<Folder> getDirectoryName(List<MusicFiles> list) {
        List<String> string = new ArrayList<>();
        List<Folder> folders = new ArrayList<>();
        for (MusicFiles f : list) {
            File file = new File(f.getPath()).getParentFile();

            string.add(new File(f.getPath()).getParentFile().getPath());
        }
        Set set = new LinkedHashSet<String>();
        set.addAll(string);
        string.clear();
        string.addAll(set);
        for (int i = 0; i < string.size(); i++) {
            Folder folder = new Folder();
            folder.setFileName(new File(string.get(i)).getName());
            folder.setPath(string.get(i));
            folders.add(folder);
        }
        return folders;
    }

    public Cursor findMusic(Context context, String path, String selection,
                            String[] selectionArgs, String sortOrder) {
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.getContentUriForPath(path),
                new String[]{
                        /**
                         * 媒体库歌曲id
                        */
                        MediaStore.Audio.Media._ID,
                        /**
                         * 歌曲名称
                        */
                        MediaStore.Audio.Media.TITLE,
                        /**
                         * 歌曲专辑名
                        */
                        MediaStore.Audio.Media.ALBUM,
                        /**
                         * 歌手名
                        */
                        MediaStore.Audio.Media.ARTIST,
                        /**
                         * 歌曲文件路径
                        */
                        MediaStore.Audio.Media.DATA,
                        /**
                         * 歌曲播放时间长度
                        */
                        MediaStore.Audio.Media.DURATION,
                        /**
                         *歌曲大小
                        */
                        MediaStore.Audio.Media.SIZE
                }, selection, selectionArgs, sortOrder);
        return cursor;
    }

    public Cursor findMusic(Context context, String selection,
                            String[] selectionArgs, String sortOrder) {
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        /**
                         * 媒体库歌曲id
                        */
                        MediaStore.Audio.Media._ID,
                        /**
                         * 歌曲名称
                        */
                        MediaStore.Audio.Media.TITLE,
                        /**
                         * 歌曲专辑名
                        */
                        MediaStore.Audio.Media.ALBUM,
                        /**
                         * 歌手名
                        */
                        MediaStore.Audio.Media.ARTIST,
                        /**
                         * 歌曲文件路径
                        */
                        MediaStore.Audio.Media.DATA,
                        /**
                         * 歌曲播放时间长度
                        */
                        MediaStore.Audio.Media.DURATION,
                        /**
                         *歌曲大小
                        */
                        MediaStore.Audio.Media.SIZE
                }, selection, selectionArgs, sortOrder);
        return cursor;
    }

    /**
     * 获取音乐添加数据库的方法
     *
     * @param cursor 图片索引游标
     */
    public List<MusicFiles> addMusic(Cursor cursor) {
        List<MusicFiles> musicFiles = new ArrayList<>();
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            if (cursor.moveToNext()) {
                if (!TextUtils.isEmpty(cursor.getString(4)) && !TextUtils.isEmpty(cursor.getString(1))) {
                    MusicFiles musicFile = new MusicFiles(cursor.getString(4), cursor.getString(1),
                            cursor.getLong(6), cursor.getLong(5), cursor.getInt(0), cursor.getString(3), cursor.getString(2),
                            null, null, null);
                    musicFiles.add(musicFile);
                }
                /*String path, String name, Long size, long time, int music_ID, String artist,
                      String album, String artistPhotoURL, String artistPhotoURLFile, String lrcURL*/
                cursor.moveToNext();
            }
        }
        cursor.close();
        //将图片排序以图片大小进行排序
        MusicComparator comparatorImage = new MusicComparator();
        Collections.sort(musicFiles, comparatorImage);
        //倒叙处理
        Collections.reverse(musicFiles);

        return musicFiles;
    }

    /**
     * 获取文件夹信息
     *
     * @param musicFiles
     * @param folder
     * @return
     */
    public List<Folder> getClassify(List<MusicFiles> musicFiles, List<Folder> folder) {
        List<Folder> folders = new ArrayList<>();
        for (Folder folder1 : folder) {
            for (MusicFiles files : musicFiles) {
                String name = new File(files.getPath()).getParentFile().getName();
                String fileName = folder1.getFileName();
                if (name.equals(fileName)) {
                    folder1.getMusicFiles().add(files);
                    UtilLog.e(TAG, TAG + "folder1: " + folder1 + "  folder: " + folder);
                }
            }
            folders.add(folder1);
        }
        return folders;
    }
}