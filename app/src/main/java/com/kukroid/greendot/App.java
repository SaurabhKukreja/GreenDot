package com.kukroid.greendot;

import android.app.Application;
import android.content.Context;

/**
 * Created by kukresa on 1/15/2019.
 */

public class App extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }
}
