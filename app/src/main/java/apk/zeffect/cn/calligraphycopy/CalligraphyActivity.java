package apk.zeffect.cn.calligraphycopy;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.LinkedList;
import java.util.List;

import apk.zeffect.cn.calligraphycopy.bean.Calligraphy;
import apk.zeffect.cn.calligraphycopy.views.panel.SketchPadView;

/**
 * 用来写书法的页面
 * Created by Administrator on 2017/2/6.
 */

public class CalligraphyActivity extends Activity {
    public static final String KEY_KEY = "key";
    private CalligraphyAdapter mAdapter;
    private SketchPadView mDrawView;
    private RecyclerView mChoseRecv;
    private List<String> mDatas = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calligraphy);
        Calligraphy calligraphy = (Calligraphy) getIntent().getSerializableExtra(KEY_KEY);
        if (calligraphy == null) {
            Toast.makeText(this, "数据传输过程中出现了问题", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            mDatas.clear();
            for (int i = 0; i < calligraphy.getText().length(); i++) {
                mDatas.add(String.valueOf(calligraphy.getText().charAt(i)));
            }
            mAdapter = new CalligraphyAdapter(this, R.layout.item_recy, mDatas);
            //
            findViewById(R.id.ac_back_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            findViewById(R.id.ac_clear_btn).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawView.clear();
                }
            });
            mDrawView = (SketchPadView) findViewById(R.id.ac_draw_sv);
            mDrawView.setLastShowText(mDatas.get(0));
            mChoseRecv = (RecyclerView) findViewById(R.id.ac_chosetext_rv);
            mChoseRecv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
            mChoseRecv.setAdapter(mAdapter);
            mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                    mDrawView.setLastShowText(mDatas.get(position));
                }

                @Override
                public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                    return false;
                }
            });
            //
        }
    }


    class CalligraphyAdapter extends CommonAdapter<String> {

        public CalligraphyAdapter(Context context, int layoutId, List<String> datas) {
            super(context, layoutId, datas);
        }

        @Override
        protected void convert(ViewHolder holder, String s, int position) {
            holder.setText(R.id.item_show_text_ftv, s);
        }
    }

}
