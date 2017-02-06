package apk.zeffect.cn.calligraphycopy.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import apk.zeffect.cn.calligraphycopy.R;
import apk.zeffect.cn.calligraphycopy.bean.Calligraphy;
import apk.zeffect.cn.calligraphycopy.views.FontTextView;

/**
 * Created by Administrator on 2017/2/5.
 */

public class ContentAdapter extends BaseAdapter {
    private List<Calligraphy> mDatas;
    private Context mContext;

    public ContentAdapter(Context pContext, List<Calligraphy> pList) {
        mContext = pContext;
        this.mDatas = pList;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHodler hodler;
        if (convertView == null) {
            hodler = new MyHodler();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_content, null);
            hodler.textView = (FontTextView) convertView.findViewById(R.id.item_show_text_ftv);
            convertView.setTag(hodler);
        } else {
            hodler = (MyHodler) convertView.getTag();
        }
        hodler.textView.setText(mDatas.get(position).getText());
        return convertView;
    }

    static class MyHodler {
        FontTextView textView;
    }

}
