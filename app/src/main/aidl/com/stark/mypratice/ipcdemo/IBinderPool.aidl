// IBinderPool.aidl
package com.stark.mypratice.ipcdemo;
import com.stark.mypratice.ipcdemo.IBookManager;
// Declare any non-default types here with import statements

interface IBinderPool {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    IBinder queryBinder(int binderCode);
}
