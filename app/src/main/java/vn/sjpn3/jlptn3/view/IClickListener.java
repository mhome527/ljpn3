package vn.sjpn3.jlptn3.view;

import android.view.View;

/**
 * Created by HuynhTD on 10/18/2016.
 */

public interface IClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
