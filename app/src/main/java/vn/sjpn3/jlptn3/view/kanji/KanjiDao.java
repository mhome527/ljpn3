package vn.sjpn3.jlptn3.view.kanji;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.sjpn3.jlptn3.db.dao.BaseDao;
import vn.sjpn3.jlptn3.db.table.KanjiTable;
import vn.sjpn3.jlptn3.entity.KanjiEntity;
import vn.sjpn3.jlptn3.utils.Log;

/**
 * Created by huynhtran on 01/18/17.
 */
public class KanjiDao extends BaseDao<KanjiEntity> {

    private static final String TAG = "KanjiDao";

    public KanjiDao(Context context) {
        super(context);
    }

    @Override
    public KanjiEntity fetch(Cursor cursor) {
        KanjiEntity entity = new KanjiEntity();
        entity.setNum(cursor.getInt(cursor.getColumnIndex(KanjiTable.COL_NUM)));
        entity.setKanji(cursor.getString(cursor.getColumnIndex(KanjiTable.COL_KANJI)));
        entity.setJp1(cursor.getString(cursor.getColumnIndex(KanjiTable.COL_JP1)));
        entity.setJp2(cursor.getString(cursor.getColumnIndex(KanjiTable.COL_JP2)));
        entity.setRomaji(cursor.getString(cursor.getColumnIndex(KanjiTable.COL_ROMAJI)));
        entity.setImgPath(cursor.getString(cursor.getColumnIndex(KanjiTable.COL_IMG_PATH)));
        entity.setOt(cursor.getString(cursor.getColumnIndex(KanjiTable.COL_OT)));
        entity.setExample(cursor.getString(cursor.getColumnIndex(KanjiTable.COL_EX)));
        return entity;
    }

    public static KanjiEntity getData(Context context, int num) {
        String sql = "SELECT * "
                + " FROM " + KanjiTable.TABLE_NAME
                + " WHERE " + KanjiTable.COL_NUM + " =" + num;

        Log.i(TAG, "kanji: sql=" + sql);
        KanjiDao dao = new KanjiDao(context);
        return dao.fetch(sql);
    }

    public static List<KanjiEntity> getListData(Context context) {
        String sql = "SELECT * "
                + " FROM " + KanjiTable.TABLE_NAME
                + " ORDER BY " + KanjiTable.COL_NUM;
        Log.i(TAG, "kanji: sql=" + sql);
        KanjiDao dao = new KanjiDao(context);
        return dao.fetchAll(sql);
    }


    public static List<KanjiEntity> searchData(Context context, String text) {
        String sql = "SELECT * FROM " + KanjiTable.TABLE_NAME
                + " WHERE " + KanjiTable.COL_JP1 + " like '%" + text + "%'"
                + " OR " + KanjiTable.COL_JP2 + " like '%" + text + "%'"
                + " OR " + KanjiTable.COL_ROMAJI + " like '%" + text + "%'"
                + " OR " + KanjiTable.COL_OT + " like '%" + text + "%'";
        Log.i(TAG, "searchData: sql=" + sql);
        KanjiDao dao = new KanjiDao(context);
        return dao.fetchAll(sql);
    }


}
