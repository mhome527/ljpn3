package vn.sjpn3.jlptn3.view.grammar.detail;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.TextView;

import butterknife.BindView;
import vn.sjpn3.jlptn3.Constant;
import vn.sjpn3.jlptn3.R;
import vn.sjpn3.jlptn3.entity.GrammarEntity;
import vn.sjpn3.jlptn3.utils.Log;
import vn.sjpn3.jlptn3.view.BaseActivity;
import vn.sjpn3.jlptn3.view.ICallback;
import vn.sjpn3.jlptn3.view.custom.DividerLineDecoration;

/**
 * Created by HuynhTD on 12/26/2016.
 */

public class GrammarDetailActivity extends BaseActivity<GrammarDetailActivity> {

    private static final String TAG = "GrammarDetailActivity";

    @BindView(R.id.tvGrammar)
    TextView tvGrammar;

    @BindView(R.id.tvRomaji)
    TextView tvRomaji;

    @BindView(R.id.tvMean)
    TextView tvMean;

    @BindView(R.id.tvFormation)
    TextView tvFormation;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @Override
    protected int getLayout() {
        return R.layout.grammar_detail_layout;
    }

    @Override
    protected void initView() {

        setupView();

        int level = getIntent().getIntExtra(Constant.INTENT_DETAIL_LEVEL, 0);
        int num = getIntent().getIntExtra(Constant.INTENT_DETAIL_NUM, 0);

        setTitle("N" + level);

        GrammarDetailPresenter presenter = new GrammarDetailPresenter(activity);
        presenter.loadItem(level, num, new ICallback<GrammarEntity>() {
            @Override
            public void onCallback(final GrammarEntity data) {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tvGrammar.setText(data.getJp());
                        tvRomaji.setText(data.getRomaji());
                        tvMean.setText(data.getMean());
                        tvFormation.setText(data.getFormation());
                        GrammarDetailAdapter adapter = new GrammarDetailAdapter(data.getDetails());
                        recyclerView.setAdapter(adapter);
                    }
                });
            }

            @Override
            public void onFail(String err) {
                Log.e(TAG, "Error:" + err);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                activity.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupView() {
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.getLayoutManager().setAutoMeasureEnabled(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerLineDecoration(activity, R.drawable.line_divider));
    }

}
