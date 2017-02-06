package apk.zeffect.cn.calligraphycopy.utils;

import android.content.Context;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import apk.zeffect.cn.calligraphycopy.MyApp;
import apk.zeffect.cn.calligraphycopy.R;
import apk.zeffect.cn.calligraphycopy.bean.Calligraphy;
import apk.zeffect.cn.calligraphycopy.bean.FontBean;

/**
 * Created by Administrator on 2017/2/5.
 */

public class CalligraphyUtils {
    public static List<Calligraphy> getAllCalligraphy(Context pContext) {
        List<Calligraphy> retuList = new LinkedList<>();
        if (pContext != null) {
            retuList.add(new Calligraphy().setText(pContext.getResources().getString(R.string.default_text)));
            List<Calligraphy> tempList = MyApp.getInstance().getLiteOrm().query(Calligraphy.class);
            if (tempList != null) {
                retuList.addAll(tempList);
            }
        }
        return retuList;
    }


    // 遍历接收一个文件路径，然后把文件子目录中的所有文件遍历并输出来
    public static List<FontBean> getAllFiles(File root) {
        List<FontBean> ttfNames = new LinkedList<>();
        File files[] = root.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                } else {
                    if (f.getName().contains(".ttf")) {
                        ttfNames.add(new FontBean().setName(f.getName()).setPath(f.getAbsolutePath()).setSite(1));
                    }
                }
            }
        }
        return ttfNames;
    }
}
