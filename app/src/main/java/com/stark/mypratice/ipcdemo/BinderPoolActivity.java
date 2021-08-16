package com.stark.mypratice.ipcdemo;

import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.stark.mypratice.R;
import com.stark.mypratice.ipcdemo.binderpoor.BinderPool;
import com.stark.mypratice.ipcdemo.binderpoor.BinderPoolImpl;
import com.stark.mypratice.ipcdemo.binderpoor.ComputeSumImpl;
import com.stark.mypratice.ipcdemo.binderpoor.PrintImpl;

public class BinderPoolActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "BinderPoolActivity";

    private BinderPool binderPool;

    private Button btnGetBinderPool;
    private Button btnComputeSumTest;
    private Button btnPrintTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binder_pool);
        initView();
    }

    private void initView() {
        btnGetBinderPool = (Button) findViewById(R.id.btn_get_binder_pool);
        btnComputeSumTest = (Button) findViewById(R.id.btn_compute_sum_test);
        btnPrintTest = (Button) findViewById(R.id.btn_print_test);

        btnGetBinderPool.setOnClickListener(this);
        btnComputeSumTest.setOnClickListener(this);
        btnPrintTest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_get_binder_pool:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        binderPool = BinderPool.getInstance(BinderPoolActivity.this);
                    }
                }).start();
                break;
            case R.id.btn_compute_sum_test:
                IBinder computeSumBinder = binderPool.queryBinder(BinderPoolImpl.POOL_COMPUTE_SUM);
                IComputeSum computeSumImpl = ComputeSumImpl.asInterface(computeSumBinder);
                try {
                    Log.d(TAG + "lpq", "onClick: computeSum = " + computeSumImpl.computeSum(4, 6));;
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_print_test:
                IBinder printBinder = binderPool.queryBinder(BinderPoolImpl.POOL_PRINT);
                IPrint print = PrintImpl.asInterface(printBinder);
                try {
                    print.print("好的，我打印了");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
