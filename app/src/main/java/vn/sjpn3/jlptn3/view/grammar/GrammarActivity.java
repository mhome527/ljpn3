package vn.sjpn3.jlptn3.view.grammar;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import vn.sjpn3.jlptn3.Constant;
import vn.sjpn3.jlptn3.R;
import vn.sjpn3.jlptn3.entity.GrammarEntity;
import vn.sjpn3.jlptn3.utils.Log;
import vn.sjpn3.jlptn3.view.BaseActivity;
import vn.sjpn3.jlptn3.view.ICallback;
import vn.sjpn3.jlptn3.view.IClickListener;
import vn.sjpn3.jlptn3.view.grammar.detail.GrammarDetailActivity;

/**
 * Created by Administrator on 8/18/2017.
 */

public class GrammarActivity extends BaseActivity<GrammarActivity> implements IClickListener {

    private final String TAG = "GrammarActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    GrammarAdapter adapter;

    GrammarPresenter presenter;
    int level = 2;

    @Override
    protected int getLayout() {
        return R.layout.grammar_fragment;
    }

    @Override
    protected void initView() {
        setTitle(getString(R.string.title_n_grammar));

        presenter = new GrammarPresenter(this);
        setupView();

        loadData(level);
    }


    public void setupView() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration2(activity, R.drawable.line_divider));

    }

    public void loadData(int level) {
        Log.i(TAG, "loadData");
        presenter.loadData(level, new ICallback() {
            @Override
            public void onCallback(Object list) {
                List<GrammarEntity> listData = (List<GrammarEntity>) list;
                adapter = new GrammarAdapter(listData, activity);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail");
            }
        });

    }

    @Override
    public void onClick(View view, int position) {
        Intent i = new Intent(activity, GrammarDetailActivity.class);
        i.putExtra(Constant.INTENT_DETAIL_LEVEL, level);
        i.putExtra(Constant.INTENT_DETAIL_NUM, adapter.getItem(position).getNum());
        activity.startActivity(i);
    }

    @Override
    public void onLongClick(View view, int position) {

    }
}
