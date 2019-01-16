package com.kukroid.greendot.Utils;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;

/**
 * Created by kukresa on 12/26/2018.
 */

public class Util {
    static  String TAG = "GREEN_DOT";

    public static void log(String msg){
        Log.d(TAG,msg);
    }

    public static void showSnackBar(View view, int message){

        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
