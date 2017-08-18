package vn.sjpn3.jlptn3.view.grammar.search;

import vn.sjpn3.jlptn3.view.BasePresenter;
import vn.sjpn3.jlptn3.view.ICallback;
import vn.sjpn3.jlptn3.view.grammar.GrammarDao;

/**
 * Created by Administrator on 1/17/2017.
 */
public class GrammarSearchPresenter extends BasePresenter<GrammarSearchActivity> {

    public GrammarSearchPresenter(GrammarSearchActivity activity) {
        super(activity);
    }

    public void searchData(final String text, ICallback callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public Object onBackground() {
                        return GrammarDao.searchData(activity, text);
                    }
                }
        );

    }
}
