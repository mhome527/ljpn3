package vn.sjpn3.jlptn3.view.grammar;

import android.content.Intent;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
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
import vn.sjpn3.jlptn3.view.grammar.search.GrammarSearchActivity;

import static android.R.attr.level;

/**
 * Created by Administrator on 8/18/2017.
 */

public class GrammarActivity extends BaseActivity<GrammarActivity> implements IClickListener {

    private final String TAG = "GrammarActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    GrammarAdapter adapter;

    GrammarPresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.grammar_fragment;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "initView");
        setTitle(getString(R.string.title_grammar));

        presenter = new GrammarPresenter(this);
        setupView();

        loadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_grammar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                Intent iSearch = new Intent(activity, GrammarSearchActivity.class);
                startActivity(iSearch);
//                Toast.makeText(this, "You have selected Bookmark Menu", Toast.LENGTH_SHORT).show();
                return true;

            case android.R.id.home:
                activity.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setupView() {

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.addItemDecoration(new DividerItemDecoration2(activity, R.drawable.line_divider));

    }

    public void loadData() {
        Log.i(TAG, "loadData...");
        presenter.loadData(new ICallback<List<GrammarEntity>>() {
            @Override
            public void onCallback(List<GrammarEntity> list) {
                Log.i(TAG, "list grammar size:" + list.size());
                adapter = new GrammarAdapter(list, activity);
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
