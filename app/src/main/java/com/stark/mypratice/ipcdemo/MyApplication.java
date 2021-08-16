package com.stark.mypratice.ipcdemo;

import android.app.Application;
import android.util.Log;

import com.stark.mypratice.ipcdemo.utils.ProcessUtils;

public class MyApplication extends Application {

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG + "lpq", "onCreate: ");
    }

    public MyApplication() {
        super();
        Log.d(TAG + "lpq", "process name: " + ProcessUtils.getProcessName());
        Log.d(TAG + "lpq", "thread name: " + Thread.currentThread().getName());
    }
}
