package vn.sjpn3.jlptn3.view.practice.kanji;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import vn.sjpn3.jlptn3.Constant;
import vn.sjpn3.jlptn3.db.table.PracticeTable;
import vn.sjpn3.jlptn3.entity.PracticeEntity;
import vn.sjpn3.jlptn3.view.practice.BasePracticeDao;

/**
 * Created by Administrator on 7/17/2017.
 */

public class PracticeKanJiDao extends BasePracticeDao {

    @Override
    protected int getLevel() {
        return Constant.LEVEL;
    }

    @Override
    protected int getKind() {
        return PracticeTable.TYPE_KANJI;
    }

    public PracticeKanJiDao(Context context) {
        super(context);
    }

    @Override
    protected PracticeEntity fetch(Cursor cursor) {
        PracticeEntity entity = new PracticeEntity();
        entity.setNum(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_NUM)));
        entity.setNumId(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_NUM_ID)));
        entity.setBookmarks(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_BOOKMARKS)));
        entity.setKind(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_KIND)));
        entity.setQuestion(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_QUESTION)));
        entity.setQ1(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q1)));
        entity.setQ2(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q2)));
        entity.setQ3(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q3)));
        entity.setQ4(cursor.getString(cursor.getColumnIndex(PracticeTable.COL_Q4)));
        entity.setAns(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_ANS)));
        entity.setReview(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_REVIEW)));
        entity.setRef(cursor.getInt(cursor.getColumnIndex(PracticeTable.COL_REF)));
        return entity;
    }

    public List<PracticeEntity> getItems(int idRef) {
        String sql = "Select * From " + getTableName() + " where "
                + PracticeTable.COL_KIND + "=" + getKind()
                + " And " + PracticeTable.COL_REF + "=" + idRef;
        return fetchAll(sql);
    }

    public void updateStatus(int numId) {
        updateReview(getKind(), numId);
    }

}
