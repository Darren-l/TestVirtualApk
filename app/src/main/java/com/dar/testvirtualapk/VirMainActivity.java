package com.dar.testvirtualapk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.didi.virtualapk.PluginManager;
import java.io.File;

public class VirMainActivity extends AppCompatActivity {

    private final String TAG = VirMainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vir_activity_main);

        //加载插件。
        loadPlugin(this);

        initView();

        initLis();
    }

    private void initLis() {
        jumPlu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String classNa = "com.dar.pluginmodule.MyMainActivity";
                intent.setClassName(VirMainActivity.this,classNa);
                startActivity(intent);
            }
        });

        callPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        loadPlugin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPlugin(VirMainActivity.this);
            }
        });
    }

    private Button jumPlu1;
    private Button callPlay;
    private Button loadPlugin;
    private void initView() {
        jumPlu1 = (Button) findViewById(R.id.jump_plugin1);
        callPlay = (Button) findViewById(R.id.call_player);
        loadPlugin = (Button) findViewById(R.id.load_plugin);
    }


    private void loadPlugin(Context base) {
        PluginManager pluginManager = PluginManager.getInstance(base);
        File apk = MyPluginManager.getInstance(this).initData();
        if (apk.exists()) {
            try {
                pluginManager.loadPlugin(apk);
                Log.i(TAG, "Loaded plugin from apk: " + apk);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{
            Log.e(TAG, "Loaded plugin from apk error!! apk unexit!");
        }
    }


}