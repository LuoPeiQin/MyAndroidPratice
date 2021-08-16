package com.stark.mypratice.viewevent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.stark.mypratice.R;

public class CustomViewActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnOverwriteOnDraw;
    private Button btnExtendViewGroup;
    private Button btnHorizontalScrollView;
    private Button btnHorizontalScrollViewInner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_costom_view);
        initView();
    }

    private void initView() {
        btnOverwriteOnDraw = (Button) findViewById(R.id.btn_overwrite_onDraw);
        btnExtendViewGroup = (Button) findViewById(R.id.btn_extend_viewGroup);

        btnOverwriteOnDraw.setOnClickListener(this);
        btnExtendViewGroup.setOnClickListener(this);
        btnHorizontalScrollView = (Button) findViewById(R.id.btn_horizontal_scroll_view);
        btnHorizontalScrollView.setOnClickListener(this);
        btnHorizontalScrollViewInner = (Button) findViewById(R.id.btn_horizontal_scroll_view_inner);
        btnHorizontalScrollViewInner.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_overwrite_onDraw:

                break;
            case R.id.btn_extend_viewGroup:

                break;
            case R.id.btn_horizontal_scroll_view:
                Intent horizontalScrollViewActivityIntent = new Intent(CustomViewActivity.this, HorizontalScrollViewActivity.class);
                startActivity(horizontalScrollViewActivityIntent);
                break;
            case R.id.btn_horizontal_scroll_view_inner:
                Intent horizontalScrollView2ActivityIntent = new Intent(CustomViewActivity.this, HorizontalScrollView2Activity.class);
                startActivity(horizontalScrollView2ActivityIntent);
                break;
        }
    }
}
