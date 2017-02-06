package apk.zeffect.cn.calligraphycopy.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import apk.zeffect.cn.calligraphycopy.MyApp;

/**
 * Created by Administrator on 2017/2/5.
 */

public class FontTextView extends TextView {
    public FontTextView(Context context) {
        super(context);
        init();
    }

    public FontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public FontTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setTypeface(MyApp.getInstance().getDefaultTypeface());
    }

}
