package com.yuan.music.utils.util;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.yuan.music.bean.MusicFiles;
import com.yuan.music.utils.music.MusicComparator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by YUAN on 2016/11/7.
 */

public class CurSorUtils {
    /**
     * 获取游标的信息
     *
     * @param context
     * @param selection
     * @param selectionArgs
     * @param sortOrder
     * @return
     */
    public static Cursor findMusic(Context context, String selection,
                                   String[] selectionArgs, String sortOrder) {
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.INTERNAL_CONTENT_URI,
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
                        MediaStore.Audio.Media.SIZE,
                        /**
                         * 测试看是什么
                        */
                        MediaStore.Audio.Albums.ALBUM
                }, selection, selectionArgs, sortOrder);
        return cursor;
    }

    /**
     * 根据路径获取游标的信息
     *
     * @param context       //上下文
     * @param path          //路径
     * @param selection     //挑选的条件
     * @param selectionArgs //挑选的参数
     * @param sortOrder     //排序方式
     * @return //返回的游标
     */
    public static Cursor findMusic(Context context, String path, String selection,
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
                        MediaStore.Audio.Media.SIZE,
                        /**
                         * 测试看是什么
                        */
                        MediaStore.Audio.Albums.ALBUM
                }, selection, selectionArgs, sortOrder);
        return cursor;
    }

    /**
     * 查找视频
     *
     * @param context
     * @return
     */
    public static Cursor findVideo(Context context) {
        Cursor cursor = context.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        /**
                         * 媒体库视频id
                        */
                        MediaStore.Video.Media._ID,
                        /**
                         * 视频名称
                        */
                        MediaStore.Video.Media.TITLE,
                        /**
                         * 缩略图
                        */
                        MediaStore.Video.Thumbnails.DATA,
                        /**
                         * 视频文件路径
                        */
                        MediaStore.Video.Media.DATA,
                        /**
                         * 视频播放时间长度
                        */
                        MediaStore.Video.Media.DURATION,
                        /**
                         *视频大小
                        */
                        MediaStore.Video.Media.SIZE
                }, null, null, null);
        return cursor;
    }

    /**
     * 用游标查询媒体库，获得图片信息
     */
    public static Cursor findImage(Context context) {
        Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{
                        /**
                         * 标题
                        */
                        MediaStore.Images.Media.TITLE,
                        /**
                         * 文件名
                        */
                        MediaStore.Images.Media.DISPLAY_NAME,
                        /**
                         * 图片媒体数据库id
                        */
                        MediaStore.Images.Media._ID,
                        /**
                         * 文件
                        */
                        MediaStore.Images.Media.DATE_TAKEN,
                        /**
                         * 文件地址
                        */
                        MediaStore.Images.Media.DATA,
                        /**
                         * 文件大小
                        */
                        MediaStore.Images.Media.SIZE
                }, null, null, null);
        return cursor;
    }

    public static Cursor findFile(Context context, String path) {
        Cursor cursor = context.getContentResolver().query(MediaStore.Files.getContentUri(path), new String[]{
                MediaStore.Files.FileColumns.DISPLAY_NAME,
                MediaStore.Files.FileColumns.TITLE,
                MediaStore.Files.FileColumns.SIZE,
                String.valueOf(MediaStore.Files.FileColumns.MEDIA_TYPE_IMAGE),
                MediaStore.Files.FileColumns.DATA
        }, null, null, null);
        return cursor;
    }
    /**
     * 获取图片添加数据库的方法
     *
     * @param cursor 图片索引游标
     */
/*
    public static List<ImageFiles> addImage(Cursor cursor) {
        List<ImageFiles> imageFiles = new ArrayList<>();
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            if (cursor.moveToNext()) {
                if (!TextUtils.isEmpty(cursor.getString(4)) && !TextUtils.isEmpty(cursor.getString(1))) {
                    ImageFiles imageFile = new ImageFiles(cursor.getInt(2), cursor.getString(4),
                            cursor.getString(1), cursor.getLong(5), cursor.getLong(3));
                    imageFiles.add(imageFile);
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
      */
/*  //将图片排序以图片大小进行排序
        LuoLingComparator comparatorImage = new LuoLingComparator(ImageFiles.class);
        Collections.sort(imageFiles, comparatorImage);
        //倒叙处理
        Collections.reverse(imageFiles);*//*

        return imageFiles;
    }
*/

    /**
     * 获取音乐添加数据库的方法
     *
     * @param cursor 图片索引游标
     */
    public static List<MusicFiles> addMusic(Cursor cursor) {
        List<MusicFiles> musicFiles = new ArrayList<>();
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            if (cursor.moveToNext()) {
                if (!TextUtils.isEmpty(cursor.getString(4)) && !TextUtils.isEmpty(cursor.getString(1))) {
                    MusicFiles musicFile = new MusicFiles(cursor.getString(4), cursor.getString(1),
                            cursor.getLong(6), cursor.getLong(5), cursor.getInt(0), cursor.getString(3), cursor.getString(2),
                            null, cursor.getString(7), null);
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
     * 获取视频添加数据库的方法
     *
     * @param cursor 图片索引游标
     */
/*
    public static List<VideoFiles> addVideo(Cursor cursor) {
        List<VideoFiles> videoFiles = new ArrayList<>();
        cursor.moveToFirst();
        for (int i = 0; i < cursor.getCount(); i++) {
            if (cursor.moveToNext()) {
                if (!TextUtils.isEmpty(cursor.getString(3)) && !TextUtils.isEmpty(cursor.getString(1))) {
                    VideoFiles videoFile = new VideoFiles(cursor.getString(3), cursor.getString(1), cursor.getLong(5),
                            cursor.getLong(4), cursor.getInt(0), cursor.getString(2));
                    videoFiles.add(videoFile);
                }
                */
/*int id, String path, String name, Long size, long time, int video_ID, String thumbnail*//*

                cursor.moveToNext();
            }
        }
        cursor.close();
        //将图片排序以图片大小进行排序
        LuoLingComparator comparatorImage = new LuoLingComparator(VideoFiles.class);
        Collections.sort(videoFiles, comparatorImage);
        //倒叙处理
        Collections.reverse(videoFiles);
        return videoFiles;
    }
*/

    /**
     * 遍历所有文件夹下的图片文件
     *
     * @param path
     */
/*
    public static List findFilePath4(final String path, final List<ImageFiles> imageFiles) {
        File[] a = new File(path).listFiles();

        for (File f : a) {
            if (f.isFile()) {
                if (f.getName().toLowerCase().endsWith(".jpg")) {
                    ImageFiles imageFile = new ImageFiles(i, f.getPath(), f.getName(), f.length(), f.lastModified());
                    imageFiles.add(imageFile);
                    i++;
                } else if (f.getName().toLowerCase().endsWith(".png")) {
                    ImageFiles imageFile = new ImageFiles(i, f.getPath(), f.getName(), f.length(), f.lastModified());
                    imageFiles.add(imageFile);
                    i++;
                } else if (f.getName().toLowerCase().endsWith(".jpeg")) {
                    ImageFiles imageFile = new ImageFiles(i, f.getPath(), f.getName(), f.length(), f.lastModified());
                    imageFiles.add(imageFile);
                    i++;
                } else if (f.getName().toLowerCase().endsWith(".bmp")) {
                    ImageFiles imageFile = new ImageFiles(i, f.getPath(), f.getName(), f.length(), f.lastModified());
                    imageFiles.add(imageFile);
                    i++;
                } else if (f.getName().toLowerCase().endsWith(".gif")) {
                    ImageFiles imageFile = new ImageFiles(i, f.getPath(), f.getName(), f.length(), f.lastModified());
                    imageFiles.add(imageFile);
                    i++;
                }
            } else {
                findFilePath4(f.getPath(), imageFiles);
            }

        }
        return imageFiles;
    }
*/

    /*public static List<MusicFiles> findFilePath(final String path, final List<MusicFiles> musicFiles) {
        File[] a = new File(path).listFiles();
        for (File f : a) {
            if (f.isFile()) {
                if (f.length() > 102400) {
                    if (f.getName().toLowerCase().endsWith(".mp3")) {
                        MusicFiles musicFile = new MusicFiles(f.getPath(), f.getName(),
                                f.length(), 0, i, null, null,
                                null, null, null);
                        musicFiles.add(musicFile);
                        i++;
                    } else if (f.getName().toLowerCase().endsWith(".wma")) {
                        MusicFiles musicFile = new MusicFiles(f.getPath(), f.getName(),
                                f.length(), 0, i, null, null,
                                null, null, null);
                        musicFiles.add(musicFile);
                        i++;
                    } else if (f.getName().toLowerCase().endsWith(".m4a")) {
                        MusicFiles musicFile = new MusicFiles(f.getPath(), f.getName(),
                                f.length(), 0, i, null, null,
                                null, null, null);
                        musicFiles.add(musicFile);
                        i++;
                    } else if (f.getName().toLowerCase().endsWith(".mmf")) {
                        MusicFiles musicFile = new MusicFiles(f.getPath(), f.getName(),
                                f.length(), 0, i, null, null,
                                null, null, null);
                        musicFiles.add(musicFile);
                        i++;
                    }
                }
            } else {
                findFilePath(f.getPath(), musicFiles);
            }
        }
        return musicFiles;
    }*/

    /**
     * 查找lrc文件
     */
    public static List<File> fillLRC(String path, List<File> files) {
        File[] file = new File(path).listFiles();
        for (File f : file) {
            if (f.isFile()) {
                if (f.getName().toLowerCase().endsWith(".lrc")) {
                    files.add(f);
                } else if (f.getName().toLowerCase().endsWith(".krc")) {
                    files.add(f);
                } else if (f.getName().toLowerCase().endsWith(".qrc")) {
                    files.add(f);
                }
            } else {
                fillLRC(f.getPath(), files);
            }
        }
        return files;
    }
}
