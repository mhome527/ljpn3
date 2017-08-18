package vn.sjpn3.jlptn3.view.kanji;

import android.view.View;

import java.util.List;

import vn.sjpn3.jlptn3.R;
import vn.sjpn3.jlptn3.entity.KanjiEntity;
import vn.sjpn3.jlptn3.view.BaseAdapter;
import vn.sjpn3.jlptn3.view.IClickListener;

/**
 * Created by HuynhTD on 10/17/2016.
 */

public class KanjiAdapter extends BaseAdapter<KanjiHolder> {

    private static String TAG = "KanjiAdapter";

    private List<KanjiEntity> listData;
    private IClickListener iClickListener;
    public String text = "";

    public KanjiAdapter(IClickListener iClickListener) {
        this.iClickListener = iClickListener;
    }

    @Override
    public int getItemLayout() {
        return R.layout.kanji_item;
    }


    @Override
    public KanjiHolder onCreateView(View view) {
        return new KanjiHolder(view, iClickListener);
    }

    @Override
    public void onBindViewHolder(KanjiHolder holder, int position) {
        holder.bind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return (listData == null || listData.size() == 0) ? 0 : listData.size();
    }

    public KanjiEntity getItem(int pos) {
        return listData.get(pos);
    }

    public void setData(List<KanjiEntity> listData, String text) {
        this.listData = listData;
        this.text = text;
    }

}
