package vn.sjpn3.jlptn3.view.practice.reading;

import vn.sjpn3.jlptn3.view.BasePresenter;
import vn.sjpn3.jlptn3.view.ICallback;

/**
 * Created by Administrator on 7/17/2017.
 */

public class PracticeReadingPresenter extends BasePresenter<PracticeReadingActivity> {

    int level;
    int idRef;

    PracticeReadingDao dao;

    public PracticeReadingPresenter(PracticeReadingActivity activity, int idRef) {
        super(activity);
        this.idRef = idRef;
        dao = new PracticeReadingDao(activity);
    }

    public void load(ICallback iCallback) {

        loadData(iCallback, new ILoadData() {
            @Override
            public Object onBackground() {
                return dao.getItems(idRef);
            }
        });
    }

    public void updateAns(int num, int review) {
        dao.updateAnswer(num, review, idRef);
        dao.updateStatus(idRef);
    }

    public void updateBookmark(int num, int bookmark) {
        dao.updateBookmark(num, bookmark);
    }
}
