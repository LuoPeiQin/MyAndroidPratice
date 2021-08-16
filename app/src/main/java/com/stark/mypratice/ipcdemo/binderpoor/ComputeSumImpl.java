package com.stark.mypratice.ipcdemo.binderpoor;

import android.os.RemoteException;

import com.stark.mypratice.ipcdemo.IComputeSum;


public class ComputeSumImpl extends IComputeSum.Stub {
    @Override
    public int computeSum(int a, int b) throws RemoteException {
        return a + b;
    }
}
