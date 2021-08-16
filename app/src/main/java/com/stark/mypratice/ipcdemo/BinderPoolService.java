package com.stark.mypratice.ipcdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.stark.mypratice.ipcdemo.binderpoor.BinderPoolImpl;

public class BinderPoolService extends Service {

    public BinderPoolService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new BinderPoolImpl();
    }

}
