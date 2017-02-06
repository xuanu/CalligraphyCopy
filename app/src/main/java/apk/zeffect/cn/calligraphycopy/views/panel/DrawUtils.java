package apk.zeffect.cn.calligraphycopy.views.panel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.TypedValue;

/**
 * Created by zeffect on 2017/2/6.
 *
 * @author zzx
 */

public class DrawUtils {
    /***
     * 画辅助线
     *
     * @param pCanvas 图
     * @param width   宽度
     * @param height  高度
     * @param pPaint  画笔
     */
    public static void drawSupLine(Canvas pCanvas, Paint pPaint, int width, int height, String showString) {
        Paint.FontMetrics fm = pPaint.getFontMetrics();
        if (pCanvas == null || pPaint == null) {
            return;
        }
        if (TextUtils.isEmpty(showString)) {
            showString = "郑";
        }
        float centerX = width * 1f / 2, centerY = height * 1f / 2;
        float textWidth = pPaint.measureText(showString);
        float textHeight = (int) Math.ceil(pPaint.getFontMetrics().descent - pPaint.getFontMetrics().ascent);
        float textX = centerX - textWidth * 1f / 2, textY = centerY - fm.descent + textHeight * 1f / 2;//字显示的坐标
        float maxXY = textWidth > textHeight ? textWidth * 1f / 2 : textHeight * 1f / 2;
        float left = centerX - maxXY, top = centerY - maxXY, right = centerX + maxXY, bottom = centerY + maxXY;
        pPaint.setColor(Color.RED);
        pPaint.setStyle(Paint.Style.STROKE);
        pPaint.setStrokeWidth(1);
        pCanvas.drawText(showString, textX, textY, pPaint);
        pCanvas.drawLine(left, top, right, top, pPaint);
        pCanvas.drawLine(left, top, left, bottom, pPaint);
        pCanvas.drawLine(left, bottom, right, bottom, pPaint);
        pCanvas.drawLine(right, top, right, bottom, pPaint);
//        pPaint.setPathEffect(new DashPathEffect(new float[]{20, 10}, 0));
//        pCanvas.drawLine(left, top, right, bottom, pPaint);
//        pCanvas.drawLine(left, bottom, right, top, pPaint);
    }

    /**
     * sp转px
     *
     * @param context 上下文
     * @param spVal   sp值
     * @return px值
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, spVal, context.getResources().getDisplayMetrics());
    }
}
