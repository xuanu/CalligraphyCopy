package apk.zeffect.cn.calligraphycopy.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;

import java.io.File;

/**
 * Created by Administrator on 2017/2/5.
 */

public class TypefaceUtils {
    /**
     * 得到字体从Assets中
     *
     * @param context 上下文
     * @param path    路径
     * @return 字体
     */
    public static Typeface getFromAssets(Context context, String path) {
        if (TextUtils.isEmpty(path)) {
            path = "fonts/defaultttf1.ttf";
        }
        return Typeface.createFromAsset(context.getAssets(), path);
    }

    /**
     * 从文件中读取字体
     *
     * @param context 上下文
     * @param path    路径
     * @return 字体
     */
    public static Typeface getFromFile(Context context, String path) {
        if (TextUtils.isEmpty(path)) {
            return getFromAssets(context, path);
        }
        return getFromFile(context, new File(path));
    }

    /***
     * 从文件中读取字体
     *
     * @param context 上下文
     * @param file    文件
     * @return 字体
     */
    public static Typeface getFromFile(Context context, File file) {
        if (file == null || !file.exists()) {
            return getFromAssets(context, "");
        }
        return Typeface.createFromFile(file);
    }

}
