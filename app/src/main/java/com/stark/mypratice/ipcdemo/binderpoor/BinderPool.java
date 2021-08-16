package com.stark.mypratice.ipcdemo.binderpoor;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


import androidx.annotation.NonNull;

import com.stark.mypratice.ipcdemo.BinderPoolService;
import com.stark.mypratice.ipcdemo.IBinderPool;

import java.util.concurrent.CountDownLatch;

public class BinderPool {

    private static final String TAG = "BinderPool";

    private Context mContext;
    private IBinderPool iBinderPool;
    private static BinderPool mInstance;

    private CountDownLatch mCountDownLatch;

    private BinderPool(Context context) {
        mContext = context.getApplicationContext();
        connectBinderPoolService();
    }

    /**
     * 全局单例
     * @param context
     * @return
     */
    public static BinderPool getInstance(@NonNull Context context) {
        if (mInstance == null) {
            synchronized (BinderPool.class) {
                if (mInstance == null) {
                    mInstance = new BinderPool(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 连接到线程池Service
     */
    private synchronized void connectBinderPoolService() {
        mCountDownLatch = new CountDownLatch(1);
        Intent intent = new Intent(mContext, BinderPoolService.class);
        mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
        try {
            mCountDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Binder死亡重连
     */
    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (iBinderPool != null) {
                iBinderPool.asBinder().unlinkToDeath(deathRecipient, 0);
            }
            iBinderPool = null;
            connectBinderPoolService();
        }
    };

    /**
     * 连接参数
     * 不管调用绑定service的函数是在主线程还是子线程运行的
     * onServiceConnected 和 onServiceDisconnected都会在主线程中运行
     */
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG + "lpq", "onServiceConnected: ");
            iBinderPool = IBinderPool.Stub.asInterface(service);
            try {
                iBinderPool.asBinder().linkToDeath(deathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            mCountDownLatch.countDown();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    public IBinder queryBinder(int binderCode) {
        IBinder binder = null;
        try {
            binder = iBinderPool.queryBinder(binderCode);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return binder;
    }

}
