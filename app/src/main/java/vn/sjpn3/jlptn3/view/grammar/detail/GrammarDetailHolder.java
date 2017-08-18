package vn.sjpn3.jlptn3.view.grammar.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vn.sjpn3.jlptn3.R;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class GrammarDetailHolder extends RecyclerView.ViewHolder {
    TextView tvJp;
    TextView tvRomaji;
    TextView tvOt;

    public GrammarDetailHolder(final View itemView) {
        super(itemView);
        tvJp = (TextView) itemView.findViewById(R.id.tvJp);
        tvRomaji = (TextView) itemView.findViewById(R.id.tvRomaji);
        tvOt = (TextView) itemView.findViewById(R.id.tvOt);

    }
}
