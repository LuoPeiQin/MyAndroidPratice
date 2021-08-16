package com.stark.mypratice.ipcdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.stark.mypratice.R;

public class IPCDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnServiceInMain;
    private Button btnServiceInProcess;
    private Button btnBinderPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipc_demo);
        initView();
    }

    private void initView() {
        btnServiceInMain = (Button) findViewById(R.id.btn_service_in_main);
        btnServiceInProcess = (Button) findViewById(R.id.btn_service_in_process);

        btnServiceInMain.setOnClickListener(this);
        btnServiceInProcess.setOnClickListener(this);
        btnBinderPool = (Button) findViewById(R.id.btn_binder_pool);
        btnBinderPool.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_service_in_main:
                Intent serviceInMainActivity = new Intent(IPCDemoActivity.this, ServiceInMainActivity.class);
                startActivity(serviceInMainActivity);
                break;
            case R.id.btn_service_in_process:
                Intent serviceInProcessActivity = new Intent(IPCDemoActivity.this, ServiceInProcessActivity.class);
                startActivity(serviceInProcessActivity);
                break;
            case R.id.btn_binder_pool:
                Intent binderPoolActivity = new Intent(IPCDemoActivity.this, BinderPoolActivity.class);
                startActivity(binderPoolActivity);
                break;
        }
    }
}
