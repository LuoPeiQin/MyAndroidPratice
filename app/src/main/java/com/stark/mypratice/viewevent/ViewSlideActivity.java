package com.stark.mypratice.viewevent;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.stark.mypratice.R;

public class ViewSlideActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ViewSlideActivity";

    private Button btnScrollToX;
    private Button btnScrollToY;
    private Button btnScrollByX;
    private Button btnScrollByY;
    private TextView tvX;
    private TextView tvY;
    private TextView tvTranslationX;
    private TextView tvTranslationY;
    private TextView tvScrollX;
    private TextView tvScrollY;

    private TextView viewSlide;
    private TextView tvLeft;
    private TextView tvTop;
    private TextView tvRight;
    private TextView tvBottom;
    private Button btnAnimatorX1;
    private Button btnAnimatorX2;
    private Button btnAnimatorY1;
    private Button btnAnimatorY2;
    private Button btnParams1;
    private Button btnParams2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_slide);
        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showSlideData();
    }

    private void showSlideData() {
        tvX.setText((int) viewSlide.getX() + "");
        tvY.setText(viewSlide.getY() + "");
        tvTranslationX.setText(viewSlide.getTranslationX() + "");
        tvTranslationY.setText(viewSlide.getTranslationY() + "");

        tvLeft.setText(viewSlide.getLeft() + "");
        tvRight.setText(viewSlide.getRight() + "");
        tvTop.setText(viewSlide.getTop() + "");
        tvBottom.setText(viewSlide.getBottom() + "");

        tvScrollX.setText(viewSlide.getScrollX() + "");
        tvScrollY.setText(viewSlide.getScrollY() + "");
    }

    private void initView() {
        btnScrollToX = (Button) findViewById(R.id.btn_scroll_to_x);
        btnScrollToY = (Button) findViewById(R.id.btn_scroll_to_y);
        btnScrollByX = (Button) findViewById(R.id.btn_scroll_by_x);
        btnScrollByY = (Button) findViewById(R.id.btn_scroll_by_y);

        tvX = (TextView) findViewById(R.id.tv_x);
        tvY = (TextView) findViewById(R.id.tv_y);
        tvTranslationX = (TextView) findViewById(R.id.tv_translation_x);
        tvTranslationY = (TextView) findViewById(R.id.tv_translation_y);
        tvScrollX = (TextView) findViewById(R.id.tv_scroll_x);
        tvScrollY = (TextView) findViewById(R.id.tv_scroll_y);
        //滑块
        viewSlide = (TextView) findViewById(R.id.view_slide);
        viewSlide.setOnClickListener(this);

        btnScrollToX.setOnClickListener(this);
        btnScrollToY.setOnClickListener(this);
        btnScrollByX.setOnClickListener(this);
        btnScrollByY.setOnClickListener(this);

        tvLeft = (TextView) findViewById(R.id.tv_left);
        tvTop = (TextView) findViewById(R.id.tv_top);
        tvRight = (TextView) findViewById(R.id.tv_right);
        tvBottom = (TextView) findViewById(R.id.tv_bottom);
        btnAnimatorX1 = (Button) findViewById(R.id.btn_animator_x1);
        btnAnimatorX1.setOnClickListener(this);
        btnAnimatorX2 = (Button) findViewById(R.id.btn_animator_x2);
        btnAnimatorX2.setOnClickListener(this);
        btnAnimatorY1 = (Button) findViewById(R.id.btn_animator_y1);
        btnAnimatorY1.setOnClickListener(this);
        btnAnimatorY2 = (Button) findViewById(R.id.btn_animator_y2);
        btnAnimatorY2.setOnClickListener(this);
        btnParams1 = (Button) findViewById(R.id.btn_params_1);
        btnParams1.setOnClickListener(this);
        btnParams2 = (Button) findViewById(R.id.btn_params_2);
        btnParams2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        Log.d(TAG + "lpq", "onClick: ");
        switch (v.getId()) {
            case R.id.view_slide:
                Log.d(TAG + "lpq", "onClick: ");
                break;
            case R.id.btn_scroll_to_x:
                viewSlide.scrollTo(-100, (int) viewSlide.getY());
                break;
            case R.id.btn_scroll_to_y:
                viewSlide.scrollTo((int) viewSlide.getX(), -100);
                break;
            case R.id.btn_scroll_by_x:
                viewSlide.scrollBy(-20, 0);
                break;
            case R.id.btn_scroll_by_y:
                viewSlide.scrollBy(0, -20);
                break;
            case R.id.btn_animator_x1:
                Animation animation1 = AnimationUtils.loadAnimation(ViewSlideActivity.this, R.anim.end_100);
                viewSlide.startAnimation(animation1);
                break;
            case R.id.btn_animator_x2:
                Animation animation2 = AnimationUtils.loadAnimation(ViewSlideActivity.this, R.anim.start_100);
                viewSlide.startAnimation(animation2);
                break;
            case R.id.btn_animator_y1:
                ObjectAnimator.ofFloat(viewSlide, "translationX", viewSlide.getX(), viewSlide.getX() + 100).setDuration(300).start();
//                ObjectAnimator.ofFloat(viewSlide, "translationY", 0, 200).setDuration(300).start();
                break;
            case R.id.btn_animator_y2:
                ObjectAnimator.ofFloat(viewSlide, "translationX", viewSlide.getX(), viewSlide.getX() - 100).setDuration(300).start();
//                ObjectAnimator.ofFloat(viewSlide, "translationY", 200, 0).setDuration(300).start();
                break;
            case R.id.btn_params_1:
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) viewSlide.getLayoutParams();
                params.leftMargin += 100;
//                params.width += 100;
                viewSlide.requestLayout();
                break;
            case R.id.btn_params_2:
                ViewGroup.MarginLayoutParams params2 = (ViewGroup.MarginLayoutParams) viewSlide.getLayoutParams();
                params2.leftMargin += 100;
//                params2.width = 100;
                viewSlide.setLayoutParams(params2);
                break;
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showSlideData();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
