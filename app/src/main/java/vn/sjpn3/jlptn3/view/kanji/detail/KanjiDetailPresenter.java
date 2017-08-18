package vn.sjpn3.jlptn3.view.kanji.detail;

import vn.sjpn3.jlptn3.entity.KanjiEntity;
import vn.sjpn3.jlptn3.view.BasePresenter;
import vn.sjpn3.jlptn3.view.ICallback;
import vn.sjpn3.jlptn3.view.kanji.KanjiDao;

/**
 * Created by HuynhTD on 01/18/2017.
 */

public class KanjiDetailPresenter extends BasePresenter<KanjiDetailActivity> {
    public KanjiDetailPresenter(KanjiDetailActivity activity) {
        super(activity);
    }


    public void getData(final int num, ICallback<KanjiEntity> callback) {
        loadData(callback, new ILoadData() {
            @Override
            public Object onBackground() {
                try {
                    KanjiEntity entity = KanjiDao.getData(activity, num);

                    return entity;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

}
