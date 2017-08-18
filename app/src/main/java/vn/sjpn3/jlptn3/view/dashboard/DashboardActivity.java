package vn.sjpn3.jlptn3.view.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.OnClick;
import vn.sjpn3.jlptn3.BuildConfig;
import vn.sjpn3.jlptn3.Constant;
import vn.sjpn3.jlptn3.R;
import vn.sjpn3.jlptn3.db.table.PracticeTable;
import vn.sjpn3.jlptn3.utils.Log;
import vn.sjpn3.jlptn3.utils.Utility;
import vn.sjpn3.jlptn3.view.BaseActivity;
import vn.sjpn3.jlptn3.view.grammar.GrammarActivity;
import vn.sjpn3.jlptn3.view.kanji.KanjiActivity;
import vn.sjpn3.jlptn3.view.practice.list.PracticeListActivity;

import static vn.sjpn3.jlptn3.BaseApplication.mFirebaseAnalytics;


public class DashboardActivity extends BaseActivity<DashboardActivity> {

    private String TAG = "DashboardActivity";

    @Override
    protected int getLayout() {
        return R.layout.dashboard_layout;
    }

    @Override
    protected void initView() {
        Log.i(TAG, "initView text: " + Constant.MY_TEXT);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume---------------------");

    }

    @OnClick(R.id.btnGrammar)
    public void actionGrammar() {
        startActivity2(GrammarActivity.class);
        //////////
        if (!BuildConfig.DEBUG) {
            // [START custom_event]
            Bundle params = new Bundle();
            params.putString("Name", "Grammar");
//                    params.putString("Language", );
            mFirebaseAnalytics.logEvent("SCREEN", params);
        }
    }

    @OnClick(R.id.btnKanji)
    public void actionKanji() {
        startActivity2(KanjiActivity.class);

        if (!BuildConfig.DEBUG) {
            // [START custom_event]
            Bundle params = new Bundle();
            params.putString("Name", "Kanji");
//                    params.putString("Language", );
            mFirebaseAnalytics.logEvent("SCREEN", params);
        }
    }

    @OnClick(R.id.btnTestGrammar)
    public void actionTestGrammar() {
        Intent i = new Intent(activity, PracticeListActivity.class);
        i.putExtra(Constant.INTENT_KIND, PracticeTable.TYPE_GRAMMAR);
        startActivity(i);

        if (!BuildConfig.DEBUG) {
            // [START custom_event]
            Bundle params = new Bundle();
            params.putString("Name", "TestGrammar");
//                    params.putString("Language", );
            mFirebaseAnalytics.logEvent("SCREEN", params);
        }
    }

    @OnClick(R.id.btnTestReading)
    public void actionTestReading() {
        Intent i = new Intent(activity, PracticeListActivity.class);
        i.putExtra(Constant.INTENT_KIND, PracticeTable.TYPE_READING);
        startActivity(i);

        if (!BuildConfig.DEBUG) {
            // [START custom_event]
            Bundle params = new Bundle();
            params.putString("Name", "TestReading");
//                    params.putString("Language", );
            mFirebaseAnalytics.logEvent("SCREEN", params);
        }
    }

    @OnClick(R.id.btnTestVocabulary)
    public void actionTestVocabulary() {
        Intent i = new Intent(activity, PracticeListActivity.class);
        i.putExtra(Constant.INTENT_KIND, PracticeTable.TYPE_VOCABULARY);
        startActivity(i);

        if (!BuildConfig.DEBUG) {
            // [START custom_event]
            Bundle params = new Bundle();
            params.putString("Name", "TestVocabulary");
//                    params.putString("Language", );
            mFirebaseAnalytics.logEvent("SCREEN", params);
        }
    }

    @OnClick(R.id.btnTestKanji)
    public void actionTestKanji() {
        Intent i = new Intent(activity, PracticeListActivity.class);
        i.putExtra(Constant.INTENT_KIND, PracticeTable.TYPE_KANJI);
        startActivity(i);

        if (!BuildConfig.DEBUG) {
            // [START custom_event]
            Bundle params = new Bundle();
            params.putString("Name", "TestKanji");
//                    params.putString("Language", );
            mFirebaseAnalytics.logEvent("SCREEN", params);
        }
    }

    @OnClick(R.id.llOtherApp)
    public void actionOtherApp() {
//        if (BuildConfig.DEBUG) {
//            Intent i = new Intent(activity, AndroidDatabaseManager.class);
//            startActivity(i);
//        } else
        Utility.installVnApp(activity);
        Bundle params = new Bundle();
        params.putString("Name", "APP_FULL");
        mFirebaseAnalytics.logEvent("SCREEN", params);

    }


    public void runActivity(Class<?> cls, View view) {
        startActivity2(cls);
    }

}
