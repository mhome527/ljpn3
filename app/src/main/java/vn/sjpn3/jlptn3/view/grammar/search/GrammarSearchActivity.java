package vn.sjpn3.jlptn3.view.grammar.search;

import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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

/*
Created by HuynhTD on 1/17/2017.
        */

public class GrammarSearchActivity extends BaseActivity<GrammarSearchActivity> implements IClickListener, SearchView.OnQueryTextListener {

    private final String TAG = "GrammarSearchActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    GrammarSearchAdapter adapter;

    GrammarSearchPresenter presenter;

    @Override
    protected int getLayout() {
        return R.layout.grammar_search_layout;
    }

    @Override
    protected void initView() {
        adapter = new GrammarSearchAdapter(activity);
        presenter = new GrammarSearchPresenter(activity);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setHomeButtonEnabled(false); // disable the button
//            actionBar.setDisplayHomeAsUpEnabled(false); // remove the left caret
//            actionBar.setDisplayShowHomeEnabled(false); // remove the icon
//            actionBar.setDisplayShowTitleEnabled(false); // remove title
//
//        } else
//            Log.e(TAG, "initView actionBar NULL!!!!");
        setupView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search_grammar, menu);

        MenuItem searchItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.onActionViewExpanded();
        if (android.os.Build.VERSION.SDK_INT >= 17)
            searchView.setPaddingRelative(30, 0, 0, 0);
        else
            searchView.setPadding(15, 0, 0, 0);

        searchView.setQueryHint(getString(R.string.menu_search_hint)); // fill in the search term by default
//        searchView.clearFocus(); // close the keyboard on load
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
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
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View view, int position) {
        Intent i = new Intent(activity, GrammarDetailActivity.class);
        i.putExtra(Constant.INTENT_DETAIL_LEVEL, adapter.getItem(position).getLevel());
        i.putExtra(Constant.INTENT_DETAIL_NUM, adapter.getItem(position).getNum());
        activity.startActivity(i);
    }

    @Override
    public void onLongClick(View view, int position) {

    }

    // ============ OnQueryTextListener =============
    @Override
    public boolean onQueryTextSubmit(String query) {
        filter(query.trim());
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        filter(newText.trim());
        return false;
    }
    /// ========= end OnQueryTextListener ===============

    public void filter(final String text) {
        if (text.trim().equals("")) {
            adapter.clearData();
            adapter.notifyDataSetChanged();
            return;
        }
        presenter.searchData(text, new ICallback() {
            @Override
            public void onCallback(Object data) {
                adapter.setData((List<GrammarEntity>) data, text);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "fail err:" + err);
            }
        });
    }
}
