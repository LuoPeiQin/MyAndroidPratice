package com.stark.mypratice.ipcdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;


import java.util.ArrayList;
import java.util.List;

public class ServiceInMain extends Service {

    private static final String TAG = "ServiceInMain";

    private List<Book> bookList;

    public ServiceInMain() {
        Log.d(TAG + ": lpq", "ServiceInMain: ");
        bookList = new ArrayList<>();
    }

    @Override
    public void onCreate() {
        Log.d(TAG + "lpq", "onCreate: ");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG + "lpq", "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG + "lpq", "onRebind: ");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG + "lpq", "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG + "lpq", "onDestroy: ");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG + "lpq", "onBind: ");
        // TODO: Return the communication channel to the service.
        return iBinder;
    }

    private final IBinder iBinder = new IBookManager.Stub() {
        @Override
        public void addBook(Book book) throws RemoteException {
            bookList.add(book);
        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return bookList;
        }

        @Override
        public int addBookAndGetNumber(Book book) throws RemoteException {
            bookList.add(book);
            return bookList.size();
        }

        @Override
        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {

        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {

        }
    };


}
