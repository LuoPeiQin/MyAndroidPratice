package com.stark.mypratice.viewevent.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.appcompat.widget.AppCompatTextView;

public class AnimatorSlideView extends AppCompatTextView {

    private static final String TAG = "AnimatorSlideView";

    public AnimatorSlideView(Context context) {
        this(context, null);
    }

    public AnimatorSlideView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimatorSlideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    float lastX;
    float lastY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG + "lpq", "onTouchEvent: x = " + getX());
        Log.d(TAG + "lpq", "onTouchEvent: y = " + getY());
        Log.d(TAG + "lpq", "onTouchEvent: translateX = " + getTranslationX());
        Log.d(TAG + "lpq", "onTouchEvent: translateY = " + getTranslationY());
        Log.d(TAG + "lpq", "onTouchEvent: left = " + getLeft());
        Log.d(TAG + "lpq", "onTouchEvent: right = " + getRight());
        Log.d(TAG + "lpq", "onTouchEvent: top = " + getTop());
        Log.d(TAG + "lpq", "onTouchEvent: bottom = " + getBottom());
        Log.d(TAG + "lpq", "onTouchEvent: scrollX = " + getScrollX());
        Log.d(TAG + "lpq", "onTouchEvent: scrollY = " + getScrollY());


//        float x = event.getX();
//        float y = event.getY();
        //不能使用getX和getY，因为滑块做了属性动画之后，触摸的相对位置也发生了改变，这个时候就会导致动画混乱
        float x = (float) event.getRawX();
        float y = (float) event.getRawY();
        Log.d(TAG + "lpq", "onTouchEvent: x = " + x + " y = " + y);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                float deltaX = x - lastX;
                float deltaY = y - lastY;
                float translationX = (float) (getTranslationX() + deltaX);
                float translationY = (float) (getTranslationY() + deltaY);
                setTranslationX(translationX);
                setTranslationY(translationY);
//                ObjectAnimator objectAnimatorX = ObjectAnimator.ofFloat(this, "translationX", lastX, x);
//                ObjectAnimator objectAnimatorY = ObjectAnimator.ofFloat(this, "translationY", lastY, y);
//                AnimatorSet animatorSet = new AnimatorSet();
//                animatorSet.play(objectAnimatorX).with(objectAnimatorY);
//                animatorSet.start();
//                int translationX =
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }
}
