package com.yuan.mymusic.bean;

import java.io.Serializable;

/**
 * 音乐文件类
 * Created by YUAN on 2016/9/12.
 */

public class MusicFiles implements Serializable {

    private int id;

    private String path;
    /**
     * 文件名
     */
    private String name;
    /**
     * 歌曲文件大小
     */
    private Long size;
    /**
     * 播放时间长度
     */
    private long time;
    /**
     * 数据库中musicID
     */
    private int music_ID;
    /**
     * 演唱者/歌手名
     */
    private String artist;
    /**
     * 专辑名
     */
    private String album;
    /**
     * 网络加载歌手写真地址
     */
    private String artistPhotoURL;
    /**
     * 本地加载歌手写真地址
     */
    private String artistPhotoURLFile;
    /**
     * 歌词文件路径
     */
    private String lrcURL;

    public MusicFiles() {

    }

    public MusicFiles(String path, String name, Long size, long time, int music_ID, String artist,
                      String album, String artistPhotoURL, String artistPhotoURLFile, String lrcURL) {
        this.path = path;
        this.name = name;
        this.size = size;
        this.time = time;
        this.music_ID = music_ID;
        this.artist = artist;
        this.album = album;
        this.artistPhotoURL = artistPhotoURL;
        this.artistPhotoURLFile = artistPhotoURLFile;
        this.lrcURL = lrcURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getMusic_ID() {
        return music_ID;
    }

    public void setMusic_ID(int music_ID) {
        this.music_ID = music_ID;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtistPhotoURL() {
        return artistPhotoURL;
    }

    public void setArtistPhotoURL(String artistPhotoURL) {
        this.artistPhotoURL = artistPhotoURL;
    }

    public String getArtistPhotoURLFile() {
        return artistPhotoURLFile;
    }

    public void setArtistPhotoURLFile(String artistPhotoURLFile) {
        this.artistPhotoURLFile = artistPhotoURLFile;
    }

    public String getLrcURL() {
        return lrcURL;
    }

    public void setLrcURL(String lrcURL) {
        this.lrcURL = lrcURL;
    }

    @Override
    public String toString() {
        return "MusicFiles{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", time=" + time +
                ", music_ID=" + music_ID +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", artistPhotoURL='" + artistPhotoURL + '\'' +
                ", artistPhotoURLFile='" + artistPhotoURLFile + '\'' +
                ", lrcURL='" + lrcURL + '\'' +
                '}';
    }
}
