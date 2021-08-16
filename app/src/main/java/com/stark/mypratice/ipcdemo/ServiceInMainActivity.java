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

import androidx.appcompat.app.AppCompatActivity;

import com.stark.mypratice.R;

import java.util.List;

public class ServiceInMainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ServiceInMainAct";

    private Intent serviceInMain;

    private IBookManager iBookManager;

    private Button btnStartService;
    private Button btnBindService;
    private Button btnUnbindService;
    private Button btnStopService;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iBookManager = IBookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private Button btnAddBook;
    private Button btnGetBooklist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_in_main);
        initView();

        serviceInMain = new Intent(ServiceInMainActivity.this, ServiceInMain.class);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_service:
                startService(serviceInMain);
                break;
            case R.id.btn_bind_service:
                bindService(serviceInMain, serviceConnection, BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind_service:
                unbindService(serviceConnection);
                break;
            case R.id.btn_stop_service:
                stopService(serviceInMain);
                break;
            case R.id.btn_add_book:
                addBook();
                break;
            case R.id.btn_get_booklist:
                getBookList();
                break;
        }
    }

    private int count = 0;
    private void addBook() {
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
            bookList = iBookManager.getBookList();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        if (bookList != null) {
            for (int i = 0; i < bookList.size(); ++i) {
                Log.d(TAG + "lpq",  bookList.get(i).toString());
            }
        }
    }
}
