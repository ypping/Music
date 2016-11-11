package com.yuan.music.utils.okHttpUtils;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.yuan.music.utils.UtilLog;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * Created by YUAN on 2016/10/25.
 * </p>
 * 自己封装的一个okHttp工具类
 */

public class OkHttpUtils {
    /**
     * client链接
     */
    private static final OkHttpClient client = new OkHttpClient();
    /**
     * 编码方式以及请求的数据类型
     */
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * 同步get请求
     *
     * @param url
     * @throws IOException
     */
    public static Response getRequestSynchronous(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        return response;
    }

    /**
     * 异步get请求可以添加一个请求头
     *
     * @param url
     * @param callback
     */
    private static void getRequestAsynchronous(String url, Callback callback, @Nullable String headerName, @Nullable String headerValue) {
        Request request = new Request.Builder().addHeader(headerName, headerValue).url(url).build();
        UtilLog.w("OkHttpUtils", "请求的内容为----->>" + request.toString());
        client.newCall(request).enqueue(callback);
    }

    /**
     * 异步get请求
     *
     * @param url              url地址
     * @param headerName       可传入的请求头名字
     * @param headerValue      可传入的请求头的值
     * @param responseData     返回的类
     * @param responseCallBack 自定义的一个接口，将内容返回到视图
     */
    public static void getRequest(String url, @Nullable String headerName, @Nullable String headerValue, final Class<?> responseData, final ResponseCallBack responseCallBack) {
        OkHttpUtils.getRequestAsynchronous(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //返回失败请求
                responseCallBack.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() == null) {
                    String requestBody = response.message();
                    if (requestBody != null && responseCallBack != null) {
                        //当返回主体为空且有错误信息时调用
                        responseCallBack.onFailure(requestBody);
                    }
                } else {
                    String str = response.body().string();
                    UtilLog.w("httpRequest", responseData.getName() + "接口返回的数据-------》》" + str);
                    responseCallBack.onSuccess(new Gson().fromJson(str, responseData));
                    response.body().close();
                }
            }
        }, headerName, headerValue);
    }

    /**
     * post请求的具体内容
     *
     * @param url
     * @param callback
     * @param json
     */
    private static void postRequest(String url, Callback callback, String json) {
        //请求体的内容
        RequestBody requestBody = RequestBody.create(JSON, json);
        UtilLog.w("OkHttpUtils", "请求的内容为----->>" + json.toString());
        //构造一个请求体
        Request request = new Request.Builder().url(url).post(requestBody).build();
        //发起请求
        client.newCall(request).enqueue(callback);
    }

    /**
     * 提供给外部调用的post请求。
     *
     * @param url              接口地址，未封装
     * @param request          请求内容
     * @param responseCallBack 制定的一个回调接口，将信息回调到试图
     * @param responseData     接收到的内容
     */
    public static void post(String url, @Nullable final Object request, final Class<?> responseData, final ResponseCallBack responseCallBack) {
        String re = new Gson().toJson(request);//将请求体转换成json格式
        OkHttpUtils.postRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //返回失败请求
                responseCallBack.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.body() == null) {
                    String requestBody = response.message();
                    if (requestBody != null && responseCallBack != null) {
                        //当返回主体为空且有错误信息时调用
                        responseCallBack.onFailure(requestBody);
                    }
                } else {
                    String str = response.body().string();
                    UtilLog.w("httpRequest", responseData.getName() + "接口返回的数据-------》》" + str);
                    responseCallBack.onSuccess(new Gson().fromJson(str, responseData));
                    response.body().close();
                }
            }
        }, re);

    }

    /**
     * 关于请求返回的内容的接口
     */
    public interface ResponseCallBack {
        /**
         * 返回成功
         *
         * @param response
         */
        void onSuccess(Object response);

        /**
         * 返回失败
         *
         * @param e
         */
        void onFailure(String e);
    }
}
