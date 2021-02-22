package com.dar.testvirtualapk;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.didi.virtualapk.PluginManager;

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        long start = System.currentTimeMillis();
        PluginManager.getInstance(base).init();
        Log.d("ryg", "use time:" + (System.currentTimeMillis() - start));
    }
}
