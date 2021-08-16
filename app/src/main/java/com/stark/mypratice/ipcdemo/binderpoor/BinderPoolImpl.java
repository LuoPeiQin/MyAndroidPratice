package com.stark.mypratice.ipcdemo.binderpoor;

import android.os.IBinder;
import android.os.RemoteException;

import com.stark.mypratice.ipcdemo.IBinderPool;

public class BinderPoolImpl extends IBinderPool.Stub {

    public static final int POOL_NONE = -1;
    public static final int POOL_COMPUTE_SUM = 0;
    public static final int POOL_PRINT = 1;

    @Override
    public IBinder queryBinder(int binderCode) throws RemoteException {
        IBinder binder = null;
        switch (binderCode) {
            case POOL_NONE:
                binder = null;
                break;
            case POOL_COMPUTE_SUM:
                binder = new ComputeSumImpl();
                break;
            case POOL_PRINT:
                binder = new PrintImpl();
                break;
        }
        return binder;
    }
}
