package vn.sjpn3.jlptn3.view.kanji;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vn.sjpn3.jlptn3.R;
import vn.sjpn3.jlptn3.entity.KanjiEntity;
import vn.sjpn3.jlptn3.view.IClickListener;

/**
 * Created by HuynhTD on 01/18/2017.
 */

public class KanjiHolder extends RecyclerView.ViewHolder {
    public TextView tvKanji;
    public TextView tvJp1;
    public TextView tvJp2;
    public TextView tvMean;

    public KanjiHolder(final View itemView, final IClickListener iClickListener) {
        super(itemView);
        tvKanji = (TextView) itemView.findViewById(R.id.tvKanji);
        tvJp1 = (TextView) itemView.findViewById(R.id.tvJp1);
        tvJp2 = (TextView) itemView.findViewById(R.id.tvJp2);
        tvMean = (TextView) itemView.findViewById(R.id.tvMean);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickListener.onClick(v, getAdapterPosition());
            }
        });
    }

    public void bind(KanjiEntity entity) {
        tvKanji.setText(entity.getKanji());
        tvJp1.setText(entity.getJp1());
        tvJp2.setText(entity.getJp2());
        tvMean.setText(entity.getOt());
    }
}
