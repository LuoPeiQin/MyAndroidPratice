package com.stark.mypratice.ipcdemo.binderpoor;

import android.os.RemoteException;
import android.util.Log;

import com.stark.mypratice.ipcdemo.IPrint;

public class PrintImpl extends IPrint.Stub {

    private static final String TAG = "PrintImpl";

    @Override
    public void print(String src) throws RemoteException {
        Log.d(TAG + "lpq", "print: src = " + src);
    }
}
