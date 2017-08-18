package vn.sjpn3.jlptn3.view.practice;

import vn.sjpn3.jlptn3.view.BaseActivity;
import vn.sjpn3.jlptn3.view.BasePresenter;
import vn.sjpn3.jlptn3.view.practice.list.PracticeDao;

/**
 * Created by Administrator on 7/7/2017.
 */

public class PracticePresenter extends BasePresenter<BaseActivity> {

    PracticeDao dao;

    public PracticePresenter(BaseActivity activity, int level, int kind) {
        super(activity);
        dao = new PracticeDao(activity, level, kind);
    }

    public int countAll(int kind) {
        dao.setKind(kind);
        return dao.countAll();
    }

    public int countCorrect(int kind) {
        dao.setKind(kind);
        return dao.countCorrect();
    }
}
