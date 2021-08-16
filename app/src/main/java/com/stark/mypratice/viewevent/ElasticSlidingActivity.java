package com.stark.mypratice.viewevent;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.stark.mypratice.R;
import com.stark.mypratice.viewevent.elastic_sliding.LinearLayoutAnimator;
import com.stark.mypratice.viewevent.elastic_sliding.LinearLayoutDelay;
import com.stark.mypratice.viewevent.elastic_sliding.LinearLayoutScroller;

public class ElasticSlidingActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ElasticSliding";

    private LinearLayoutScroller llsScroller;
    private Button btnScrollerSliding;
    private Button btnAnimatorSliding;
    private Button btnDelaySliding;
    private LinearLayoutAnimator llaAnimator;
    private LinearLayoutDelay lldDelay;
    private Button btnAnimatorScrollToSliding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elastic_sliding);
        initView();
    }

    private void initView() {
        llsScroller = (LinearLayoutScroller) findViewById(R.id.lls_scroller);


        btnScrollerSliding = (Button) findViewById(R.id.btn_scroller_sliding);
        btnScrollerSliding.setOnClickListener(this);
        btnAnimatorSliding = (Button) findViewById(R.id.btn_animator_sliding);
        btnAnimatorSliding.setOnClickListener(this);
        btnDelaySliding = (Button) findViewById(R.id.btn_delay_sliding);
        btnDelaySliding.setOnClickListener(this);

        llaAnimator = (LinearLayoutAnimator) findViewById(R.id.lla_animator);
        llaAnimator.setOnClickListener(this);
        lldDelay = (LinearLayoutDelay) findViewById(R.id.lld_delay);
        lldDelay.setOnClickListener(this);
        btnAnimatorScrollToSliding = (Button) findViewById(R.id.btn_animator_scroll_to_sliding);
        btnAnimatorScrollToSliding.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scroller_sliding:
                llsScroller.smoothScrollTo(llsScroller.getScrollX() - 100, 0);
                break;
            case R.id.btn_animator_sliding:
                ObjectAnimator.ofFloat(llaAnimator, "translationX", llaAnimator.getTranslationX(), llaAnimator.getTranslationX() + 100).setDuration(1000).start();
                break;
            case R.id.btn_animator_scroll_to_sliding:
                //动画 + scrollTo
                final int startX = llaAnimator.getScrollX();
                final int dx = 100;
                ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 1).setDuration(1000);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float fraction = animation.getAnimatedFraction();
                        Log.d(TAG + "lpq", "onAnimationUpdate: fraction = " + fraction);
                        llaAnimator.scrollTo(startX - (int) (dx * fraction), 0);
                    }
                });
                valueAnimator.start();
                break;
            case R.id.btn_delay_sliding:
                //延时机制
                currentScrollX = lldDelay.getScrollX();
                handler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, 0);
                break;

        }
    }

    private static final int MESSAGE_SCROLL_TO = 1;
    private static final int FRAME_COUNT = 30;
    private static final int DELAY_TIME = 33;

    private int count = 0;
    private int currentScrollX = 0;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_SCROLL_TO:
                    ++count;
                    if (count > FRAME_COUNT) {
                        count = 0;
                        break;
                    }
                    int deltaX = (int) ((float)count / FRAME_COUNT * 100);
                    Log.d(TAG + "lpq", "handleMessage: delataX = " + deltaX);
                    lldDelay.scrollTo(currentScrollX - deltaX, 0);
                    handler.sendEmptyMessageDelayed(MESSAGE_SCROLL_TO, DELAY_TIME);
                    break;
            }
        }
    };
}
