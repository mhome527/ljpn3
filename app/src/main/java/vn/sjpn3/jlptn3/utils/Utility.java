package vn.sjpn3.jlptn3.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import vn.sjpn3.jlptn3.Constant;
import vn.sjpn3.jlptn3.R;


public class Utility {
//    private static String TAG = "Utility";
//    public static int parseInt(final String num) {
//        try {
//            return Integer.parseInt(num);
//        } catch (final Exception e) {
//            return -1;
//        }
//    }

//    public static String ArrToString(String[] arr) {
//        String str = "";
//        if (arr == null || arr.length == 0)
//            return "";
//        if (arr.length == 1) {
//            str = arr[0];
//        } else {
//            for (int i = 0; i < arr.length - 1; i++) {
//                if (!arr[i].contains("ttt"))
//                    str += arr[i] + " - ";
//            }
//            str += arr[arr.length - 1];
//        }
//        return str;
//    }

//    public static AlertDialog dialogWifi(final Activity activity) {
//        // 1. Instantiate an AlertDialog.Builder with its constructor
//        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
//
//        // create layout for dialog
//        LinearLayout layout = new LinearLayout(activity);
//        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        layout.setOrientation(LinearLayout.VERTICAL);
//        layout.setLayoutParams(parms);
//        layout.setGravity(Gravity.CLIP_VERTICAL);
//        layout.setPadding(2, 2, 2, 2);
//
//        TextView tv = new TextView(activity);
//        tv.setText(activity.getString(R.string.connect_wifi));
//        tv.setPadding(10, 30, 10, 30);
//        tv.setGravity(Gravity.CENTER);
//        tv.setTextSize(16);
//
//        // create layout for textview
//        LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        layout.addView(tv, tvParams);
//
//        builder.setView(layout);
//        builder.setPositiveButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                dialog.dismiss();
//            }
//        });
//        builder.setNegativeButton(activity.getString(R.string.turn_on_wifi), new DialogInterface.OnClickListener() {
//            public void onClick(DialogInterface dialog, int id) {
//                // User cancelled the dialog
//                dialog.dismiss();
//                activity.startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
//            }
//        });
//        // 3. Get the AlertDialog from create()
//        AlertDialog dialog = builder.create();
//        dialog.show();
//        return dialog;
//    }

    // Check if Internet Network is active
//    public static boolean checkNetwork(Activity activity) {
//        boolean wifiDataAvailable = false;
//        boolean mobileDataAvailable = false;
//        ConnectivityManager conManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo[] networkInfo = conManager.getAllNetworkInfo();
//        for (NetworkInfo netInfo : networkInfo) {
//            if (netInfo.getTypeName().equalsIgnoreCase("WIFI"))
//                if (netInfo.isConnected())
//                    wifiDataAvailable = true;
//            if (netInfo.getTypeName().equalsIgnoreCase("MOBILE"))
//                if (netInfo.isConnected())
//                    mobileDataAvailable = true;
//        }
//        return wifiDataAvailable || mobileDataAvailable;
//    }

//    public static boolean createDirIfNotExists(String path) {
//        boolean ret = true;
//
//        File file = new File(path);
//        if (!file.exists()) {
//            if (!file.mkdirs()) {
//                Log.e("Utility", "Problem creating Image folder");
//                ret = false;
//            }
//        }
//        return ret;
//    }

    public static int getResourcesID(Context context, String name) {
        try {
            if (name.equals(""))
                return -1;
            return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
        } catch (Exception e) {
            Log.e("Utility", "get resource error:" + e.getMessage());
            return -1;
        }
    }
//
//    public static int randomPos(int lenght, int... params) {
//        Random r;
//        int i;
//        boolean b = false;
//
//        for (int j = 0; j < 100; j++) {
//            r = new Random();
//            i = r.nextInt(lenght);
//            for (int value : params) {
//                if (i == value) {
//                    b = false;
//                    break;
//                } else
//                    b = true;
//            }
//            if (b)
//                return i;
//        }
//        return 0;
//    }

    /**
     * Asking the permission for installing lvn app. If permission granted – sent user to Google Play
     *
     * @param context – Activity, that initialized installing
     */
    public static void installVnApp(final Context context) {

        // creating a dialog asking user if he want to install the Voice Search
        Dialog dialog = new AlertDialog.Builder(context).setMessage(context.getString(R.string.msg_content_vn))
                .setTitle(context.getString(R.string.msg_title_vn))
                .setPositiveButton(context.getString(R.string.install), new DialogInterface.OnClickListener() {

                    // Install Button click handler
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            // creating an Intent for opening applications page in Google Play
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Constant.PACKAGE_LVN));
                            // setting flags to avoid going in application history (Activity call stack)
                            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                            // sending an Intent
                            context.startActivity(intent);
                        } catch (Exception ex) {
                            // if something going wrong doing nothing
                        }
                    }
                }).setIcon(R.mipmap.ic_launcher).setNegativeButton(context.getString(R.string.cancel), null).create();

        dialog.show();
    }


    public static int dpToPx(final float dp) {
        return Math.round(dp * (Resources.getSystem().getDisplayMetrics().xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }


    public static void setWidthGrid(GridView gridView) {
        ListAdapter gridViewAdapter = gridView.getAdapter();
        if (gridViewAdapter == null) {
            return;
        }
        int totalWidth;
        int items = gridViewAdapter.getCount();
        View listItem = gridViewAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalWidth = listItem.getMeasuredWidth();
        totalWidth = totalWidth*items;
        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.width = totalWidth;
        gridView.setLayoutParams(params);
    }
}
