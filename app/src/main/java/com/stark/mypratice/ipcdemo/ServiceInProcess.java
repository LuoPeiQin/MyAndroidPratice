package com.stark.mypratice.ipcdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.stark.mypratice.ipcdemo.utils.ProcessUtils;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

public class ServiceInProcess extends Service {

    private static final String TAG = "ServiceInProcess";

    private CopyOnWriteArrayList<Book> bookList;
//    private ArrayList<Book> bookList;

    public ServiceInProcess() {
        Log.d(TAG + "lpq", "ServiceInProcess: ");
        bookList = new CopyOnWriteArrayList<>();
    }

    @Override
    public void onCreate() {
        Log.d(TAG + "lpq", "onCreate: ");
        super.onCreate();
    }

    Timer timer = new Timer();
    int count = 0;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG + "lpq", "onStartCommand: ");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                count++;
                Log.d(TAG + "lpq", "run: 发通知书了");
                onNewBookArrived(new Book("通知书" + count, count));
            }
        }, 3000, 8000);
        return super.onStartCommand(intent, flags, startId);
    }

    public void stopTimer() {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
    }

    @Override
    public void onRebind(Intent intent) {
        Log.d(TAG + "lpq", "onRebind: ");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG + "lpq", "onUnbind: ");
        stopTimer();
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG + "lpq", "onDestroy: ");
        stopTimer();
        super.onDestroy();
    }

    public void onNewBookArrived(Book book) {
        int listenerCount = listenerList.beginBroadcast();
        Log.d(TAG + "lpq", "onNewBookArrived: listenerCount = " + listenerCount);
        for (int i = 0; i < listenerCount; ++i) {
            try {
                IOnNewBookArrivedListener broadcastItem = listenerList.getBroadcastItem(i);
                Log.d(TAG + "lpq", "onNewBookArrived: Process Name = " + ProcessUtils.getProcessName());
                Log.d(TAG + "lpq", "onNewBookArrived: Thread Name = " + Thread.currentThread().getName());
                broadcastItem.onNewBookArrived(book);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        listenerList.finishBroadcast();
    }

//    public void onNewBookArrived(Book book){
//        if (listenerList != null && listenerList.() >  0) {
//            for (int i = 0; i < listenerList.size(); ++i) {
//                try {
//                    Log.d(TAG + "lpq", "onNewBookArrived: Thread Name = " + Thread.currentThread().getName());
//                    listenerList.get(i).onNewBookArrived(book);
//                } catch (RemoteException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

    private RemoteCallbackList<IOnNewBookArrivedListener> listenerList = new RemoteCallbackList<>();

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG + "lpq", "onBind: ");
        // TODO: Return the communication channel to the service.
        return iBinder;
    }

    private IBinder iBinder = new IBookManager.Stub() {
        @Override
        public void addBook(Book book) throws RemoteException {
            synchronized (bookList) {
                Log.d(TAG + "lpq", "addBook: Thread Name = " + Thread.currentThread().getName());
                bookList.add(book);
                if (bookList.size() != book.getBookNumber()) {
                    Log.d(TAG + "lpq", "addBook: size = " + bookList.size() + "  bookNumber = " + book.getBookNumber());
                }
            }
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
            if (listener != null) {
                listenerList.register(listener);
            }
        }

        @Override
        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
            if (listener != null) {
                listenerList.unregister(listener);
            }
        }

//        @Override
//        public void registerListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (listener != null && !listenerList.contains(listener)) {
//                listenerList.add(listener);
//                Log.d(TAG + "lpq", "registerListener: 添加了监听");
//            }
//            Log.d(TAG + "lpq", "unregisterListener: size = " + listenerList.size());
//        }
//
//        @Override
//        public void unregisterListener(IOnNewBookArrivedListener listener) throws RemoteException {
//            if (listener != null && listenerList.contains(listener)) {
//                listenerList.remove(listener);
//                Log.d(TAG + "lpq", "unregisterListener: 解注册成功");
//            }
//            Log.d(TAG + "lpq", "unregisterListener: size = " + listenerList.size());
//        }
    };
}
