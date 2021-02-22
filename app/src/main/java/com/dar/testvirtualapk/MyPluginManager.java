package com.dar.testvirtualapk;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MyPluginManager {

    private static final String TAG = MyPluginManager.class.getSimpleName();

    private static MyPluginManager pluginManager;

    private Context context;

    public static MyPluginManager getInstance(Context context) {
        if (pluginManager == null) {
            synchronized (MyPluginManager.class) {
                if (pluginManager == null) {
                    pluginManager = new MyPluginManager(context);
                }
            }
        }
        return pluginManager;
    }

    private MyPluginManager(Context context) {
        this.context = context;
    }


    /**
     * DarrenAdd20210125：准备测试数据。
     *
     */
    private File apk;
    public File initData() {
        apk = new File(context.getCacheDir() + "/plugin.apk");
        apk.delete();
        if (!apk.exists()) {
            try {
                InputStream is = context.getAssets().open("apk/plugin.apk");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                FileOutputStream fos = new FileOutputStream(apk);
                fos.write(buffer);
                fos.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return apk;
    }

}
