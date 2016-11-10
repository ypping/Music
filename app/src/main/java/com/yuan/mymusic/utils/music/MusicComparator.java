package com.yuan.mymusic.utils.music;

import com.yuan.mymusic.bean.MusicFiles;

import java.util.Comparator;

/**
 * 排序类
 * Created by YUAN on 2016/11/3.
 */

public class MusicComparator implements Comparator {
    public MusicComparator() {
    }

    @Override
    public int compare(Object o1, Object o2) {
        MusicFiles music = (MusicFiles) o1;
        MusicFiles musicFiles = (MusicFiles) o2;

        //首先比较大小，如果大小相同，则比较名字

        int flag = music.getSize().compareTo(musicFiles.getSize());
        if (flag == 0) {
            flag = music.getName().compareTo(musicFiles.getName());
        } else {
            flag = 0;
        }
        return flag;
    }
}
