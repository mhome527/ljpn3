package vn.sjpn3.jlptn3.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import vn.sjpn3.jlptn3.view.IClickListener;
import vn.sjpn3.jlptn3.view.RecyclerTouchListener;


/**
 * Created by Administrator on 1/25/2017.
 */

public class Common {
    public static void setupRecyclerView(Context context, RecyclerView recyclerView, IClickListener iClickListener) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Add event
        if (iClickListener != null)
            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context, recyclerView, iClickListener));
    }

    public static void setupRecyclerViewGrid(Context context, RecyclerView recyclerView, IClickListener iClickListener) {
//        GridLayoutManager lLayout = new GridLayoutManager(context, 2);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(lLayout);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        //Add event
//        if (iClickListener != null)
//            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context, recyclerView, iClickListener));
        setupRecyclerViewGrid(context, recyclerView, 2, iClickListener);
    }

    public static void setupRecyclerViewGrid(Context context, RecyclerView recyclerView, int column, IClickListener iClickListener) {
        GridLayoutManager lLayout = new GridLayoutManager(context, column);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(lLayout);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //Add event
        if (iClickListener != null)
            recyclerView.addOnItemTouchListener(new RecyclerTouchListener(context, recyclerView, iClickListener));
    }

    public static boolean isTablet(Context ctx) {
        return (ctx.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
