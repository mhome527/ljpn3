package vn.sjpn3.jlptn3.view.practice.list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import vn.sjpn3.jlptn3.BuildConfig;
import vn.sjpn3.jlptn3.Constant;
import vn.sjpn3.jlptn3.R;
import vn.sjpn3.jlptn3.db.table.PracticeTable;
import vn.sjpn3.jlptn3.entity.PracticeEntity;
import vn.sjpn3.jlptn3.utils.Common;
import vn.sjpn3.jlptn3.utils.Log;
import vn.sjpn3.jlptn3.view.BaseActivity;
import vn.sjpn3.jlptn3.view.ICallback;
import vn.sjpn3.jlptn3.view.IClickListener;
import vn.sjpn3.jlptn3.view.practice.dialog.PracticeDialog;
import vn.sjpn3.jlptn3.view.practice.kanji.PracticeKanJiActivity;
import vn.sjpn3.jlptn3.view.practice.reading.PracticeReadingActivity;

import static vn.sjpn3.jlptn3.BaseApplication.mFirebaseAnalytics;

/**
 * Created by Administrator on 7/7/2017.
 */

public class PracticeListActivity extends BaseActivity<PracticeListActivity> implements IClickListener {
    private final String TAG = "PracticeListActivity";

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    List<PracticeEntity> items;
    PracticeListAdapter adapter;
    PracticeListPresenter presenter;
    int kind;
    boolean isSort = true;
    int v1;
    int v2;
    private int mTotalScrolled = 0;

    @Override
    protected int getLayout() {
        return R.layout.practice_list_layout;
    }

    @Override
    protected void initView() {
        Common.setupRecyclerView(activity, recyclerView, this);
        kind = getIntent().getIntExtra(Constant.INTENT_KIND, PracticeTable.TYPE_GRAMMAR);
//        level = getIntent().getIntExtra(Constant.INTENT_LEVEL, PracticeTable.LEVEL_N5);

        v1 = getIntent().getIntExtra(Constant.INTENT_V1, 0);
        v2 = getIntent().getIntExtra(Constant.INTENT_V2, 0);


        presenter = new PracticeListPresenter(this, kind);
        setTitleQ(v1, v2);

        ///////
        if (!BuildConfig.DEBUG) {
            Bundle params = new Bundle();

            if (kind == PracticeTable.TYPE_GRAMMAR)
                params.putString("Practice", "GRAMMAR");
            else if (kind == PracticeTable.TYPE_READING)
                params.putString("Practice", "READING");
            else if (kind == PracticeTable.TYPE_LISTENING)
                params.putString("Practice", "LISTENING");
            else if (kind == PracticeTable.TYPE_KANJI)
                params.putString("Practice", "KANJI");
            else
                params.putString("Practice", "VOCABULARY");

            mFirebaseAnalytics.logEvent("SCREEN", params);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.putPosHistory(recyclerView.computeVerticalScrollOffset());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_practice_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            case R.id.menuBookmark:
                presenter.putPosHistory(recyclerView.computeVerticalScrollOffset());
                Intent i = new Intent(activity, PracticeBookmarkActivity.class);
                i.putExtra(Constant.INTENT_KIND, kind);
                i.putExtra(Constant.INTENT_V1, v1);
                i.putExtra(Constant.INTENT_V2, v2);
                startActivity(i);
                return true;

            case R.id.menuSort:
                isSort = !isSort;
                loadData();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //   ==============  IClickListener - item click
    @Override
    public void onClick(View view, int position) {
        PracticeEntity item = items.get(position);


        if (kind == PracticeTable.TYPE_READING) {
            presenter.putPosHistory(recyclerView.computeVerticalScrollOffset());
//            setPositionScroll2();
            Intent i = new Intent(activity, PracticeReadingActivity.class);
            i.putExtra(Constant.INTENT_NUM, item.getNum());
            i.putExtra(Constant.INTENT_BOOKMARK, item.getBookmarks());
            i.putExtra(Constant.INTENT_DETAIL_NUM, item.getNumId());
            i.putExtra(Constant.INTENT_TITLE_Q, item.getQuestion());
            i.putExtra(Constant.INTENT_V1, v1);
            i.putExtra(Constant.INTENT_V2, v2);

            startActivity(i);
        } else {
            if (kind == PracticeTable.TYPE_KANJI
                    && item.getNumId() > 200) { //truong hop ngoai le la kanji co man hinh rieng
                presenter.putPosHistory(recyclerView.computeVerticalScrollOffset());
                Intent i = new Intent(activity, PracticeKanJiActivity.class);
                i.putExtra(Constant.INTENT_NUM, item.getNum());
                i.putExtra(Constant.INTENT_BOOKMARK, item.getBookmarks());
                i.putExtra(Constant.INTENT_DETAIL_NUM, item.getNumId());
                i.putExtra(Constant.INTENT_TITLE_Q, item.getQuestion());
                i.putExtra(Constant.INTENT_V1, v1);
                i.putExtra(Constant.INTENT_V2, v2);

                startActivity(i);
            } else {

                PracticeDialog dialog = new PracticeDialog(activity, position, items, iPracticeInterface);
                dialog.show();
            }
        }

    }

    @Override
    public void onLongClick(View view, int position) {

    }
//   ============= END IClickListener

    //    IPracticeInterface -- dialog clicked
    IPracticeInterface iPracticeInterface = new IPracticeInterface() {

        @Override
        public void onBookmark(int pos, int value) {
            PracticeEntity item = items.get(pos);
            item.setBookmarks(value);
            presenter.updateBookmark(item.getNum(), value);
        }

        @Override
        public void onAns(int pos, int value) {
            PracticeEntity item = items.get(pos);
            presenter.updateAnswer(item.getNum(), value);
            item.setReview(value);
            int correct = presenter.countCorrect();
            setTitleQ(correct);
            adapter.notifyItemChanged(pos);
        }
    };


    private void loadData() {
        presenter.getItems(isSort, new ICallback<List<PracticeEntity>>() {
            @Override
            public void onCallback(List<PracticeEntity> data) {
                items = data;
                adapter = new PracticeListAdapter(data);

                recyclerView.setAdapter(adapter);
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();

                    }
                });
                int correct = presenter.countCorrect();
                setTitleQ(correct);

//                setPositionScroll();
                mTotalScrolled = presenter.getPosHistory();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.scrollBy(0, mTotalScrolled);
                    }
                }, 20);
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "onFail err:" + err);
            }
        });
    }

    private void setTitleQ(int value) {
        setTitleQ(value, items.size());
    }

    private void setTitleQ(int v1, int v2) {
        setTitle(presenter.getTitle(v1, v2));
    }

}
