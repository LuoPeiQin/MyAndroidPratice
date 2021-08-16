package com.stark.mypratice.viewevent;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.stark.mypratice.R;

public class ViewEventActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnViewSlideActivity;
    private Button btnThreeWayActivity;
    private Button btnElasticSlidingActivity;
    private Button btnCustomViewActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);
        initView();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private void initView() {
        btnViewSlideActivity = (Button) findViewById(R.id.btn_view_slide_activity);

        btnViewSlideActivity.setOnClickListener(this);
        btnThreeWayActivity = (Button) findViewById(R.id.btn_three_way_activity);
        btnThreeWayActivity.setOnClickListener(this);
        btnElasticSlidingActivity = (Button) findViewById(R.id.btn_elastic_sliding_activity);
        btnElasticSlidingActivity.setOnClickListener(this);
        btnCustomViewActivity = (Button) findViewById(R.id.btn_custom_view_activity);
        btnCustomViewActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_view_slide_activity:
                Intent viewSlideActivityIntent = new Intent(ViewEventActivity.this, ViewSlideActivity.class);
                startActivity(viewSlideActivityIntent);
                break;
            case R.id.btn_three_way_activity:
                Intent threeWaySlideActivityIntent = new Intent(ViewEventActivity.this, ThreeWaySlideActivity.class);
                startActivity(threeWaySlideActivityIntent);
                break;
            case R.id.btn_elastic_sliding_activity:
                Intent elasticSlidingActivityIntent = new Intent(ViewEventActivity.this, ElasticSlidingActivity.class);
                startActivity(elasticSlidingActivityIntent);
                break;
            case R.id.btn_custom_view_activity:
                Intent customViewActivityIntent = new Intent(ViewEventActivity.this, CustomViewActivity.class);
                startActivity(customViewActivityIntent);
                break;
        }
    }


}
