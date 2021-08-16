package com.stark.mypratice.ipcdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.stark.mypratice.R;
import com.stark.mypratice.ipcdemo.utils.ProcessUtils;

import java.util.List;

public class ServiceInProcessActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ServiceInProcess";

    private Intent serviceInProcess;

//    private com.tony.ipcdemo.manuallyAidl.IBookManager iBookManager;

    private IBookManager iBookManager;
    private Button btnStartService;
    private Button btnBindService;
    private Button btnUnbindService;
    private Button btnStopService;

    private IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            if (iBookManager == null) {
                return;
            }
            iBookManager.asBinder().unlinkToDeath(deathRecipient, 0);
            iBookManager = null;
            Log.d(TAG + "lpq", "binderDied: rebind");
            bindService(serviceInProcess, serviceConnection, BIND_AUTO_CREATE);
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBookManager = IBookManager.Stub.asInterface(service);
            try {
                iBookManager.registerListener(mOnNewBookArrivedListener);
//                service.linkToDeath(deathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iBookManager = null;
        }
    };

    private Button btnAddBook;
    private Button btnGetBooklist;
    private Button btnAddManyBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_in_main);
        initView();

        serviceInProcess = new Intent(ServiceInProcessActivity.this, ServiceInProcess.class);
    }

    private void initView() {
        btnStartService = (Button) findViewById(R.id.btn_start_service);
        btnBindService = (Button) findViewById(R.id.btn_bind_service);
        btnUnbindService = (Button) findViewById(R.id.btn_unbind_service);
        btnStopService = (Button) findViewById(R.id.btn_stop_service);

        btnStartService.setOnClickListener(this);
        btnBindService.setOnClickListener(this);
        btnUnbindService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnAddBook = (Button) findViewById(R.id.btn_add_book);
        btnAddBook.setOnClickListener(this);
        btnGetBooklist = (Button) findViewById(R.id.btn_get_booklist);
        btnGetBooklist.setOnClickListener(this);
        btnAddManyBook = (Button) findViewById(R.id.btn_add_many_book);
        btnAddManyBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                startService(serviceInProcess);
                break;
            case R.id.btn_bind_service:
                bindService(serviceInProcess, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                unbindService(serviceConnection);
                break;
            case R.id.btn_stop_service:
                stopService(serviceInProcess);
                break;
            case R.id.btn_add_book:
                addBook();
                break;
            case R.id.btn_get_booklist:
                getBookList();
                break;
            case R.id.btn_add_many_book:
                addManyBook(100);
                break;
        }
    }

    int sendCount = 0;

    private void addManyBook(int number) {
        for (int i = 0; i < number; ++i) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    int num = sendCount++;
                    Book book = new Book("数学" + num, num);
                    try {
                        iBookManager.addBook(book);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }

    private int count = 0;

    private void addBook() {
        Log.d(TAG + "lpq", "addBook: Thread Name = " + Thread.currentThread().getName());
        ++count;
        Book book = new Book("英语" + count, count);
        try {
            iBookManager.addBook(book);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void getBookList() {
        List<Book> bookList = null;
        try {
            iBookManager.unregisterListener(mOnNewBookArrivedListener);
            bookList = iBookManager.getBookList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (bookList != null) {
            for (int i = 0; i < bookList.size(); ++i) {
                Log.d(TAG + "lpq", bookList.get(i).toString());
            }
        }
    }

    private IOnNewBookArrivedListener mOnNewBookArrivedListener = new IOnNewBookArrivedListener.Stub() {

        @Override
        public void onNewBookArrived(Book book) throws RemoteException {
            Log.d(TAG + "lpq", "onNewBookArrived: 书接收到了 " + book.toString());
            Log.d(TAG + "lpq", "onNewBookArrived: Process Name = " + ProcessUtils.getProcessName());
            Log.d(TAG + "lpq", "onNewBookArrived: Thread Name = " + Thread.currentThread().getName());
            Toast.makeText(ServiceInProcessActivity.this, book.getBookName(), Toast.LENGTH_SHORT).show();
        }
    };

//    private class OnNewBookArrivedListener implements IOnNewBookArrivedListener.Stub{
//        @Override
//        public void onNewBookArrived(Book book) throws RemoteException {
//            Log.d(TAG + "lpq", "onNewBookArrived: 书接收到了 " + book.toString());
////        Toast.makeText(ServiceInProcessActivity.this, book.getBookName(), Toast.LENGTH_SHORT).show();
//        }
//
//        @Override
//        public IBinder asBinder() {
//            return this.asBinder();
//        }
//    }

}
