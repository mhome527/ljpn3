package vn.sjpn3.jlptn3.view.grammar;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vn.sjpn3.jlptn3.R;
import vn.sjpn3.jlptn3.view.IClickListener;

/**
 * Created by HuynhTD on 12/20/2016.
 */

public class GrammarHolder extends RecyclerView.ViewHolder {
    public TextView tvGrammar;
    public TextView tvMean;

    public GrammarHolder(final View itemView, final IClickListener iClickListener) {
        super(itemView);
        tvGrammar = (TextView) itemView.findViewById(R.id.tvGrammar);
        tvMean = (TextView) itemView.findViewById(R.id.tvMean);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickListener.onClick(v, getAdapterPosition());
            }
        });
    }
}
