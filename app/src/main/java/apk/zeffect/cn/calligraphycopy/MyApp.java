package apk.zeffect.cn.calligraphycopy;

import android.app.Application;
import android.graphics.Typeface;
import android.text.TextUtils;

import com.litesuits.orm.LiteOrm;

import apk.zeffect.cn.calligraphycopy.utils.TypefaceUtils;
import common.zeffect.cn.library.sharedprepreferences.PreferencesUtils;

/**
 * Created by Administrator on 2017/2/5.
 */

public class MyApp extends Application {
    /**
     * 单例
     */
    private static MyApp instance;
    /**
     * 全局使用的默认字体
     */
    private Typeface mDefaultTypeface;
    /**
     * 数据库操作
     */
    private LiteOrm liteOrm;

    public static final String SITE_KEY = "site", PATH_KEY = "path";

    @Override
    public void onCreate() {
        super.onCreate();
        //
        int site = PreferencesUtils.getInt(this, SITE_KEY, 0);
        String path = PreferencesUtils.getString(this, PATH_KEY, "");
        if (TextUtils.isEmpty(path)) {
            MyApp.getInstance().setDefaultTypeface(TypefaceUtils.getFromAssets(this, "fonts/defaultttf1.ttf"));
        } else {
            if (site == 0) {
                MyApp.getInstance().setDefaultTypeface(TypefaceUtils.getFromAssets(this, path));
            } else if (site == 1) {
                MyApp.getInstance().setDefaultTypeface(TypefaceUtils.getFromFile(this, path));
            }
        }
    }

    public static MyApp getInstance() {
        if (instance == null) {
            synchronized (MyApp.class) {
                if (instance == null) {
                    instance = new MyApp();
                }
            }
        }
        return instance;
    }

    public Typeface getDefaultTypeface() {
        return mDefaultTypeface;
    }

    public void setDefaultTypeface(Typeface mDefaultTypeface) {
        this.mDefaultTypeface = mDefaultTypeface;
    }

    public LiteOrm getLiteOrm() {
        return liteOrm;
    }

    public void setLiteOrm(LiteOrm liteOrm) {
        this.liteOrm = liteOrm;
    }
}
