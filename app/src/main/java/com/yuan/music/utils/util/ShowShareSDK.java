package com.yuan.music.utils.util;

import android.content.Context;

import com.yuan.music.R;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * sharesdk的一个分享工具，
 * Created by YUAN on 2016/9/5.
 */
public class ShowShareSDK {

    private Context context;

    public ShowShareSDK(Context context) {
        this.context = context;
    }

    /**
     * @param title    分享标题
     * @param titleUrl 标题网址
     * @param text     分享内容
     * @param weiUrl   微信中文件内容
     * @param comment  分享评论
     * @param qqUrl    qq中文件内容
     */
    public void ShowShare(String title, String titleUrl, String text, String weiUrl, String comment, String qqUrl) {
        ShareSDK.initSDK(context);
        OnekeyShare oks = new OnekeyShare();
        // 分享时Notification的图标和文字

        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(title);
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(titleUrl);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(text);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(titleUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(comment);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(titleUrl);
        oks.setImageUrl(titleUrl);
        //filePath是待分享应用程序的本地路劲，仅在微信（易信）好友和Dropbox中使用，否则可以不提供
        //  oks.setFilePath("/sdcard/test-pic.jpg");
        oks.setComment("分享"); //我对这条分享的评论，仅在人人网和QQ空间使用，否则可以不提供
        oks.setSite(context.getString(R.string.app_name));  //QZone分享完之后返回应用时提示框上显示的名称
        oks.setSiteUrl("http://www.oschina.net/question/1993021_165904");//QZone分享参数
        oks.setVenueName("najia");
        oks.setVenueDescription("This is a beautiful place!");
        // 启动分享GUI
        oks.show(context);
    }
}
