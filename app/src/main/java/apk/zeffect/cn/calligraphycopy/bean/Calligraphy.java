package apk.zeffect.cn.calligraphycopy.bean;

import com.litesuits.orm.db.annotation.Table;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/2/5.
 */
@Table("calligraphy")
public class Calligraphy implements Serializable {
    private int id;
    private String text;
    private int version;

    public int getId() {
        return id;
    }

    public Calligraphy setId(int id) {
        this.id = id;
        return this;
    }

    public String getText() {
        return text;
    }

    public Calligraphy setText(String text) {
        this.text = text;
        return this;
    }

    public int getVersion() {
        return version;
    }

    public Calligraphy setVersion(int version) {
        this.version = version;
        return this;
    }
}
