package vn.sjpn3.jlptn3.view.grammar;


import vn.sjpn3.jlptn3.view.BasePresenter;
import vn.sjpn3.jlptn3.view.ICallback;

/**
 * Created by HuynhTD on 12/21/2016.
 */

public class GrammarPresenter extends BasePresenter<GrammarActivity> {
    public GrammarPresenter(GrammarActivity activity) {
        super(activity);
    }

    public void loadData(final int level, ICallback callback) {
        loadData(callback, new ILoadData() {
                    @Override
                    public Object onBackground() {
                        GrammarDao dao = new GrammarDao(activity);
                        return dao.getListData(level);
                    }
                }
        );

    }

    public void loadItem(final int level, final int num, ICallback callback) {
        loadData(callback, new ILoadData() {
            @Override
            public Object onBackground() {
                return GrammarDao.getItemData(activity, level, num);
            }
        });
    }

}
