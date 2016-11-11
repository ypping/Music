package com.yuan.music.utils.util;

import android.content.Context;
import android.media.MediaScannerConnection;
import android.net.Uri;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *     desc : 通知系统扫描新的媒体文件
 * </pre>
 * Created by YUAN on 2016/11/4.
 */

public class MediaScanner {

    private volatile static MediaScanner scanner;

    private MediaScanner() {
    }

    public static MediaScanner getInstance() {
        synchronized (MediaScanner.class) {
            if (scanner == null) {
                scanner = new MediaScanner();
            }
        }
        return scanner;
    }

    public void scanFile(Context context, ScanFile file) {
        List<ScanFile> scanFiles = new ArrayList<>();
        scanFiles.add(file);
        scanFile(context, scanFiles);
    }

    public void scanFile(Context context, List<ScanFile> scanFiles) {
        ScannerClient client = new ScannerClient(context, scanFiles);
        client.connectAndScan();
    }

    public static class ScanFile {
        /**
         * 要扫描的媒体文件路径或包含媒体文件的文件夹路径
         */
        public String filePaths;
        /**
         * 要扫描的媒体文件类型 eg: audio/mp3  media/*  application/ogg
         * image/jpeg  image/png  video/mpeg   video/3gpp
         * ......
         */
        public String mineType;

        public ScanFile(String filePaths, String mineType) {
            this.filePaths = filePaths;
            this.mineType = mineType;
        }
    }

    public class ScannerClient implements MediaScannerConnection.MediaScannerConnectionClient {
        /**
         * 要扫描的文件或文件夹
         */
        private List<ScanFile> scanFiles = null;
        /**
         * 实际要扫描的文件集合
         */
        private List<ScanFile> filePaths = null;

        private MediaScannerConnection mediaScannerConnection;

        public ScannerClient(Context context, List<ScanFile> scanFiles) {
            this.scanFiles = scanFiles;
            mediaScannerConnection = new MediaScannerConnection(context, this);
        }

        /**
         * 链接MediaScanner并开始扫描
         */
        public void connectAndScan() {
            if (scanFiles != null && !scanFiles.isEmpty()) {
                this.filePaths = new ArrayList<>();
                for (ScanFile scanFile : scanFiles) {
                    findFile(scanFile);
                }
                mediaScannerConnection.connect();
            }
        }

        private void findFile(ScanFile scanFile) {
            File file = new File(scanFile.filePaths);
            if (file.isFile()) {
                filePaths.add(scanFile);
            } else {
                File[] f = file.listFiles();
                if (f != null && f.length > 0) {
                    for (File fs : f
                            ) {
                        findFile(new ScanFile(fs.getAbsolutePath(), scanFile.mineType));
                    }
                }
            }
        }

        private void scanNext() {
            if (filePaths != null && !filePaths.isEmpty()) {
                ScanFile sf = filePaths.remove(filePaths.size() - 1);
                mediaScannerConnection.scanFile(sf.filePaths, sf.mineType);
            } else {
                mediaScannerConnection.disconnect();
            }
        }

        @Override
        public void onMediaScannerConnected() {
            scanNext();
        }

        @Override
        public void onScanCompleted(String path, Uri uri) {
            scanNext();
            //媒体扫描完成可以配合EventBus等消息通讯工具发出通知,也可接收Intent.ACTION_MEDIA_SCANNER_FINISHED的广播
            //EventBus.getDefault().post(new EventMediaScanCompleted(path));
        }
    }
}
