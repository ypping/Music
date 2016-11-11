package com.yuan.music.utils.util;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;

/**
 * <pre>
 *     4.4以上获取图片信息的接口
 * </pre>
 * Created by YUAN on 2016/11/4.
 */

public class PhotoUtils {
    /**
     * 获得图片的回调
     */
    public interface PhotoBack {
        void onSuccess(String picturePath);// 拿取相片成功

        void onFailure();// 拿取相片失败
    }

    /**
     * 处理图片返回地址的方法
     *
     * @param context
     * @param data
     * @param photoBack
     */
    public static void getPhotoURLByAlbum(Context context, Intent data, PhotoBack photoBack) {
        if (data == null) {
            photoBack.onFailure();
            return;
        }
        final Uri selectedImage = data.getData();
        if (selectedImage == null) {
            photoBack.onFailure();
            return;
        }
        String picturePath = "";   // 关于Android4.4的图片路径获取，如果回来的Uri的格式有两种
        if (Build.VERSION.SDK_INT >= 19 && DocumentsContract.isDocumentUri(context, selectedImage)) {
            String wholeID = DocumentsContract.getDocumentId(selectedImage);
            String id = wholeID.split(":")[1];
            String[] column = {MediaStore.Images.Media.DATA};
            String sel = MediaStore.Images.Media._ID + "=?";
            Cursor cursor = context.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel, new String[]{id}, null);
            if (cursor.moveToNext()) {
                int columnIndex = cursor.getColumnIndex(column[0]);
                picturePath = cursor.getString(columnIndex);
                photoBack.onSuccess(picturePath);// 获取图片路径
            }
            cursor.close();
        } else {
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(selectedImage, projection, null, null, null);
            if (cursor.moveToNext()) {
                int column_index = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                picturePath = cursor.getString(column_index);
                photoBack.onSuccess(picturePath);// 获取图片路径
                Log.e("aa", "aa" + picturePath + "  photoBack" + photoBack);
            }
            cursor.close();
        }
    }
}
