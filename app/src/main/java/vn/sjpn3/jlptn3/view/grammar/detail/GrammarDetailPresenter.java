package vn.sjpn3.jlptn3.view.grammar.detail;


import vn.sjpn3.jlptn3.entity.GrammarEntity;
import vn.sjpn3.jlptn3.view.BasePresenter;
import vn.sjpn3.jlptn3.view.ICallback;
import vn.sjpn3.jlptn3.view.grammar.GrammarDao;

/**
 * Created by HuynhTD on 12/21/2016.
 */

public class GrammarDetailPresenter extends BasePresenter<GrammarDetailActivity> {
    public GrammarDetailPresenter(GrammarDetailActivity activity) {
        super(activity);
    }


    public void loadItem(final int level, final int num, ICallback<GrammarEntity> callback) {
        loadData(callback, new ILoadData() {
            @Override
            public Object onBackground() {
                try {
                    GrammarEntity grammarEntity = GrammarDao.getItemData(activity, level, num);
                    grammarEntity.formatData();
                    return grammarEntity;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

}
