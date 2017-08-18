package vn.sjpn3.jlptn3.view.practice.list;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import vn.sjpn3.jlptn3.BaseApplication;
import vn.sjpn3.jlptn3.R;
import vn.sjpn3.jlptn3.db.table.PracticeTable;
import vn.sjpn3.jlptn3.entity.PracticeEntity;
import vn.sjpn3.jlptn3.view.BaseViewHolder;

/**
 * Created by Administrator on 7/10/2017.
 */

public class PracticeListHolder extends BaseViewHolder {

    @BindView(R.id.tvNum)
    TextView tvNum;

    @BindView(R.id.tvContent)
    TextView tvContent;

//
//    @BindView(R.id.imgLike)
//    ImageView imgLike;


    public PracticeListHolder(View itemView) {
        super(itemView);
    }

    public void bind(PracticeEntity item) {
        if (item.getKind() == PracticeTable.TYPE_READING)
            tvContent.setMaxLines(2);
        else if (item.getKind() == PracticeTable.TYPE_LISTENING)
            tvContent.setMaxLines(1);
        else
            tvContent.setMaxLines(2);

        tvNum.setText(item.getNum() + "");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            tvContent.setText(Html.fromHtml(item.getQuestion(), Html.FROM_HTML_MODE_LEGACY));
        else
            tvContent.setText(Html.fromHtml(item.getQuestion()));


        if (item.getReview() == -1) //answer wrong
            tvContent.setTextColor(BaseApplication.getInstance().
                    getResources().getColor(R.color.red));
        else if (item.getReview() == 1)
            tvContent.setTextColor(BaseApplication.getInstance().
                    getResources().getColor(R.color.gray));
        else
            tvContent.setTextColor(BaseApplication.getInstance().
                    getResources().getColor(R.color.black));

    }
}
