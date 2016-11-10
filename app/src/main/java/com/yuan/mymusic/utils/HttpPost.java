package com.yuan.mymusic.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

/**
 * post请求方式封装类，通过 HttpPost(URL url)获得实例！
 * <p/>
 * </p>
 * 或者通过 parseUrl(String url)来获得示例！
 * </p>
 * 通过putString(k,v)或putMap(map)来添加发送数据
 * </p>
 * 通过putFile();添加要文件发送
 * </p>
 * 数据设置结束过后记得调用send()方法,开始网络post传输,在发送之前可设置OnSendListener监听，实现ui的更新。
 * <p/>
 * </p>
 * 亦可主动调用sendInBack(),手动开支线程，完成请求！
 * <p/>
 * Created by yuan-pc on 2016/05/31.
 */
public class HttpPost {
    URL mUrl;
    final String BOUNDARY = "adss--ssad";
    private final StringBuilder mBuilder = new StringBuilder();

    ArrayList<FileItem> files;

    /**
     * 通过 url 直接获得HttpPost对象
     *
     * @param url
     */
    public HttpPost(URL url) {
        mUrl = url;

    }

    ;

    /**
     * 通过 解析url地址获得一个HttpPost对象;
     *
     * @param url
     * @return HttpPost
     * @throws MalformedURLException url 不规范抛出
     */
    public static HttpPost parseUrl(String url) throws MalformedURLException {
        URL u = new URL(url);
        return new HttpPost(u);
    }

    /**
     * POST 方式传输的一组表单数据, key值不能为空，为空put无效！
     * </p>
     * 例：
     * </p>
     * post.putString("name","jack");
     *
     * @param k
     * @param v
     */
    @SuppressLint("NewApi")
    public void putString(String k, String v) {
        if (k == null || k.isEmpty()) {
            return;
        }
        mBuilder.append("--" + BOUNDARY + "\r\n");
        mBuilder.append("Content-Disposition: form-data; name=\"" + k + "\"");
        mBuilder.append("\r\n\r\n");
        mBuilder.append(v + "\r\n");
    }

    /**
     * POST 方式传输的多组表单数据,key值不能为空，为空的那条传输无效！
     * </p>
     * Map<String, String> map=new HashMap();
     * </p>
     * map.put("name","jack");
     * </p>
     * map.put("password","123456");
     * </p>
     * post.putMap(map);
     *
     * @param map
     */
    public void putMap(Map<String, String> map) {
        if (map != null) {
            for (Map.Entry<String, String> set : map.entrySet()) {
                putString(set.getKey(), set.getValue());
            }
        }
    }

    /**
     * @param key     必须不为空，否则无效
     * @param file    传输的文件,必须存在且不为空，否则无效
     * @param newName 新名字，可为空
     * @param type    传输的文件类型声明,注意格式，可为空,默认 image/jpeg;
     */
    @SuppressLint("NewApi")
    public void putFile(String key, File file, String newName, String type) {
        if (key != null && !key.isEmpty() && file != null && file.exists()) {
            if (files == null) {
                files = new ArrayList<FileItem>();
            }
            FileItem item = new FileItem();
            item.fileKey = key;
            item.sendFile = file;
            if (newName == null || newName.isEmpty()) {
                item.fileName = file.getName();
            } else {
                item.fileName = newName;
            }
            if (type == null || type.isEmpty()) {
                item.fileType = "image/jpeg";
            } else {
                item.fileType = type;
            }
            item.buidler.append("--" + BOUNDARY + "\r\n");
            item.buidler.append("Content-Disposition: form-data; name=\"" + item.fileKey + "\"; filename=\""
                    + item.fileName + "\"\r\n");
            item.buidler.append("Content-Type: " + item.fileType + "\r\n\r\n");
            files.add(item);
        }
    }

    /**
     * 开始执行post请求,注意：不需开支线程请求！
     * </p>
     * 在发送之前可设置OnSendListener监听，在监听的end(String result)方法里实现ui的更新。
     */
    public void send() {
        if (mListener != null)
            mListener.start();
        new MyAyckTast().execute();

    }

    /**
     * 开始执行发送，须在子线程操作！
     *
     * @return -1》》io异常！-2》》请求异常
     */
    @SuppressLint("NewApi")
    public String sendInBack() {

        try {
            byte end[] = ("--" + BOUNDARY + "--\r\n").getBytes("UTF-8");
            /*
			 * long dataLength = 0; byte end[] = ("--" + BOUN DARY +
			 * "--\r\n").getBytes("UTF-8"); if (files != null &&
			 * !files.isEmpty()) { for (FileItem item : files) { dataLength +=
			 * item.buidler.toString().length(); dataLength +=
			 * item.sendFile.length(); dataLength += "\r\n".length(); } }
			 *
			 * dataLength = mBuilder.toString().length() + end.length +
			 * dataLength;
			 */
            HttpURLConnection conn = (HttpURLConnection) mUrl.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            // conn.setRequestProperty("Content-Length",
            // String.valueOf(dataLength));
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            OutputStream oStream = conn.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(oStream);
            bos.write(mBuilder.toString().getBytes("UTF-8"));
            bos.flush();
            InputStream in;
            if (files != null && !files.isEmpty()) {
                for (FileItem item : files) {
                    Log.i("file", item.buidler.toString());
                    bos.write(item.buidler.toString().getBytes("UTF-8"));
                    bos.flush();
                    in = new FileInputStream(item.sendFile);
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = in.read(buf)) != -1) {
                        bos.write(buf, 0, len);
                    }
                    bos.flush();
                    in.close();
                    bos.write("\r\n".getBytes("UTF-8"));
                }
            }
            bos.write(end);
            bos.flush();
            bos.close();
            oStream.close();
            StringBuilder result = new StringBuilder();
            if (conn.getResponseCode() == 200) {
                in = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line = reader.readLine();
                while ((line != null && !line.isEmpty())) {
                    result.append(line);
                    line = reader.readLine();
                }
                reader.close();
                in.close();

                return result.toString();
            } else {
                Log.w("conn code", conn.getResponseCode() + "");
                return "-2";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "-1";
        }
    }

    private OnSendListener mListener;


    /**
     * 设置请求监听
     * </p>
     * 重写start() end()两个方法 实现ui更新
     *
     * @param mListener
     */
    public void setOnSendListener(OnSendListener mListener) {
        this.mListener = mListener;
    }

    /**
     * post网络请求监听
     *
     * @author kk0927
     */
    public static interface OnSendListener {
        /**
         * 服务器请求开始的时候调用
         */
        void start();

        /**
         * 服务器请求结束的时候调用
         *
         * @param result -1》》io异常！-2》》请求异常
         */
        void end(String result);
    }

    private class MyAyckTast extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {

            return sendInBack();
        }

        @Override
        protected void onPostExecute(String result) {
            if (mListener != null)
                mListener.end(result);
        }
    }

    private class FileItem {
        File sendFile;// 须传输的文件
        String fileKey;// 传输文件的key
        String fileName;// 文件名
        String fileType;// 声明文件的类型
        StringBuilder buidler = new StringBuilder();

    }
}

