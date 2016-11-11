package com.yuan.music.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件夹的内容
 * Created by YUAN on 2016/11/3.
 */

public class Folder implements Serializable{
    private String photo;
    private String fileName;
    private String path;
    private List<MusicFiles> musicFiles;


    public Folder(String photo, String fileName) {
        this.photo = photo;
        this.fileName = fileName;
        musicFiles = new ArrayList<>();
    }

    public Folder() {
        musicFiles = new ArrayList<>();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<MusicFiles> getMusicFiles() {
        return musicFiles;
    }

    public void setMusicFiles(List<MusicFiles> musicFiles) {
        this.musicFiles = musicFiles;
    }
}
