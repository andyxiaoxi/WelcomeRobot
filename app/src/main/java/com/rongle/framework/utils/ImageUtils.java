package com.rongle.framework.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by tony on 17-6-20.
 */

public class ImageUtils {

    private static final int ERROR_IMAGE = 0;
    private static final int AVATAR_IMAGE = 0;

    /**
     * 显示图片
     *
     * @param context   上下文
     * @param imageView 图片view
     * @param uri       图片URL
     */
    public static void displayImage(Context context, ImageView imageView, String uri) {
        display(context, imageView, uri, null, false);
    }


    /**
     * 显示资源图片
     *
     * @param context    上下文
     * @param imageView  图片view
     * @param resourceId 图片资源id
     */
    public static void displayImage(Context context, final ImageView imageView, int resourceId) {
        Glide.with(context)
                .load(resourceId)
                .centerCrop()
                .placeholder(ERROR_IMAGE)
                .error(ERROR_IMAGE)
                .dontAnimate()
                .into(imageView);
    }

    /**
     * 显示图片
     *
     * @param context              上下文
     * @param imageView            图片view
     * @param uri                  图片路径
     * @param bitmapTransformation bitmap转换器
     */
    private static void display(Context context, final ImageView imageView, String uri, BitmapTransformation bitmapTransformation, boolean isAvatar) {
        DrawableTypeRequest<String> drawableTypeRequest = Glide.with(context).load(uri);
        drawableTypeRequest
                .centerCrop()
                .placeholder(ERROR_IMAGE)
                .error(isAvatar ? AVATAR_IMAGE : ERROR_IMAGE)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate();

        // 添加转换器
        if (null != bitmapTransformation) {
            drawableTypeRequest.bitmapTransform(bitmapTransformation);
        }

        // 设置图片
        drawableTypeRequest.into(imageView);
    }
}
