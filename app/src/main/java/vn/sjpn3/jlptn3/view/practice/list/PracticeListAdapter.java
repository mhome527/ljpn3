package vn.sjpn3.jlptn3.view.practice.list;

import android.view.View;

import java.util.List;

import vn.sjpn3.jlptn3.R;
import vn.sjpn3.jlptn3.entity.PracticeEntity;
import vn.sjpn3.jlptn3.view.BaseAdapterView;

/**
 * Created by Administrator on 7/10/2017.
 */

public class PracticeListAdapter extends BaseAdapterView<PracticeListHolder> {

    List<PracticeEntity> items;

    public PracticeListAdapter(List<PracticeEntity> items) {
        this.items = items;
    }

    @Override
    protected int getHeaderLayout() {
        return 0;
    }

    @Override
    protected int getFooterLayout() {
        return 0;
    }

    @Override
    protected int getItemLayout() {
        return R.layout.practice_list_item;
    }

    @Override
    protected PracticeListHolder getHeaderView(View view) {
        return null;
    }

    @Override
    protected PracticeListHolder getFooterView(View view) {
        return null;
    }

    @Override
    protected PracticeListHolder getItemView(View view) {
        return new PracticeListHolder(view);
    }

    @Override
    protected List getListData() {
        return items;
    }

    @Override
    public void onViewHolder(PracticeListHolder holder, int position) {
        holder.bind(items.get(position));
    }


}
