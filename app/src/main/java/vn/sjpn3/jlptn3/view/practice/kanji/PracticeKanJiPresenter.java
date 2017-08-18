package vn.sjpn3.jlptn3.view.practice.kanji;


import vn.sjpn3.jlptn3.view.BasePresenter;
import vn.sjpn3.jlptn3.view.ICallback;

/**
 * Created by Administrator on 7/17/2017.
 */

public class PracticeKanJiPresenter extends BasePresenter<PracticeKanJiActivity> {

    int level;
    int idRef;

    PracticeKanJiDao dao;

    public PracticeKanJiPresenter(PracticeKanJiActivity activity, int level, int idRef) {
        super(activity);
        this.level = level;
        this.idRef = idRef;
        dao = new PracticeKanJiDao(activity, level);
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

    public void updateBookmark(int num, int bookmark, int numId) {
        dao.updateBookmark(num, bookmark, numId);
    }
}
