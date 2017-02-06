package apk.zeffect.cn.calligraphycopy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ListView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.WhereBuilder;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import apk.zeffect.cn.calligraphycopy.adapter.ContentAdapter;
import apk.zeffect.cn.calligraphycopy.bean.Calligraphy;
import apk.zeffect.cn.calligraphycopy.bean.FontBean;
import apk.zeffect.cn.calligraphycopy.utils.CalligraphyUtils;
import apk.zeffect.cn.calligraphycopy.utils.TypefaceUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import common.zeffect.cn.library.sharedprepreferences.PreferencesUtils;

public class MainActivity extends Activity {
    @BindView(R.id.am_showtext_lv)
    ListView mShowContent;

    @OnClick(R.id.am_menu_btn)
    void show() {
        new MaterialDialog.Builder(this).title("使用说明：").content("字体使用说明：网上下载相关字休.ttf结尾的，放到/sdcard/android/data/apk.zeffect.cn.calligraphycopy/files/fonts/文件夹下，即可切换字体").positiveText("确定").show();
    }

    @OnClick(R.id.am_add_text_btn)
    void addText() {
        new MaterialDialog.Builder(this)
                .title(R.string.input_want_to_copy_content)
                .inputRangeRes(1, 65535, R.color.red)
                .input(null, null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        MyApp.getInstance().getLiteOrm().save(new Calligraphy().setText(input.toString()));
                        resetData();
                    }
                }).negativeText("取消").show();
    }

    @OnClick(R.id.am_switch_btn)
    void switchFonts() {
        final List<FontBean> tempFonts = new LinkedList<>();
        tempFonts.add(new FontBean().setName("田英章楷书").setPath("fonts/defaultttf1.ttf").setSite(0));
        tempFonts.add(new FontBean().setName("田英章行书").setPath("fonts/defaultttf2.ttf").setSite(0));
        tempFonts.addAll(CalligraphyUtils.getAllFiles(getExternalFilesDir("fonts")));
        String[] strings = new String[tempFonts.size()];
        for (int i = 0; i < tempFonts.size(); i++) {
            strings[i] = tempFonts.get(i).getName();
        }
        new AlertDialog.Builder(this).setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FontBean fontBean = tempFonts.get(which);
                if (fontBean.getSite() == 0) {
                    MyApp.getInstance().setDefaultTypeface(TypefaceUtils.getFromAssets(MainActivity.this, fontBean.getPath()));
                } else if (fontBean.getSite() == 1) {
                    MyApp.getInstance().setDefaultTypeface(TypefaceUtils.getFromFile(MainActivity.this, fontBean.getPath()));
                }
                PreferencesUtils.putInt(MainActivity.this, MyApp.SITE_KEY, fontBean.getSite());
                PreferencesUtils.putString(MainActivity.this, MyApp.PATH_KEY, fontBean.getPath());
                mAdapter.notifyDataSetChanged();
                dialog.dismiss();
            }
        }).create().show();
    }


    @OnItemLongClick(R.id.am_showtext_lv)
    boolean delRecord(final int position) {
        new MaterialDialog.Builder(MainActivity.this).title("删除！").content("确定要删除本条记录码？").onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                MyApp.getInstance().getLiteOrm().delete(new WhereBuilder(Calligraphy.class).andEquals("id", mDatas.get(position).getId()));
                resetData();
            }
        }).positiveText("确定").negativeText("取消").show();
        return true;
    }

    @OnItemClick(R.id.am_showtext_lv)
    void gotoCalligraphy(int position) {
        Intent intent = new Intent(this, CalligraphyActivity.class);
        intent.putExtra(CalligraphyActivity.KEY_KEY, mDatas.get(position));
        startActivity(intent);
    }

    private ContentAdapter mAdapter;

    private List<Calligraphy> mDatas = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyApp.getInstance().setLiteOrm(LiteOrm.newCascadeInstance(this, "calligraphy.db"));
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mAdapter = new ContentAdapter(this, mDatas);
        mShowContent.setAdapter(mAdapter);
        resetData();
    }

    private void resetData() {
        mDatas.clear();
        mDatas.addAll(CalligraphyUtils.getAllCalligraphy(this));
        mAdapter.notifyDataSetChanged();
    }

}
