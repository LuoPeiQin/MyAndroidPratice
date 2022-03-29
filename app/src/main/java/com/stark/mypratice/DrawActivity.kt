package com.stark.mypratice

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.stark.mypratice.view.business.WeeklyScoreView
import java.util.*

class DrawActivity : AppCompatActivity() {

    private lateinit var weeklyScoreView: WeeklyScoreView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ecg_layout)
    }

//    var i = 0
//    @Override
//    fun onClick(v: View) {
////        weeklyScoreView.animate().scaleX(0.8f).start()
//        val value = i % 7
//        when (value) {
//            0 -> weeklyScoreView.setNoValue()
//            1 -> weeklyScoreView.setValue(listOf(-1, -1, -1, 7, 8, 4, 2))
//            2 -> weeklyScoreView.setValue(listOf(5, -1, 6, -1, 4, 5, 3))
//            3 -> weeklyScoreView.setValue(listOf(-1, -1, -1, -1, -1, -1, 5))
//            4 -> weeklyScoreView.setValue(listOf(-1, -1, -1, 5, -1, -1, -1))
//            5 -> weeklyScoreView.setValue(listOf(5, 3, 4, 7, 8, 4, 3))
//            6 -> weeklyScoreView.animate().scaleX(0.8f).start()
//        }
//        i++
//
//    }

    override fun onResume() {
        super.onResume()
    }

//    fun onClick(view: View) {
////        findViewById<View>(R.id.mfImageView1).visibility = View.INVISIBLE
////        findViewById<View>(R.id.mfImageView2).visibility = View.INVISIBLE
////        findViewById<View>(R.id.mfImageView3).visibility = View.INVISIBLE
////        when(view.id) {
////            R.id.btn1 -> {
////                findViewById<View>(R.id.mfImageView1).visibility = View.VISIBLE
////            }
////            R.id.btn2 -> {
////                findViewById<View>(R.id.mfImageView2).visibility = View.VISIBLE
////            }
////            R.id.btn3 -> {
////                findViewById<View>(R.id.mfImageView3).visibility = View.VISIBLE
////            }
////        }
//    }


    /**
     * 第15节课的测试代码
     */
    fun classTest15() {
        // 15 课
        // 1、圆放大
//        val view_1 = findViewById<View>(R.id.view_1)
//        val animator = ObjectAnimator.ofFloat(view_1, "radius", 60.dp)
//        animator.startDelay = 1000
//        animator.duration = 2000
//        animator.start()
//
//        // 2、点移动
//        val view_2 = findViewById<PointView>(R.id.view_2)
//        val animator2 = ObjectAnimator.ofObject(view_2, "point", object : TypeEvaluator<PointF> {
//            override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
//                Log.i("lpq", "evaluate: fraction = $fraction startValue = $startValue endValue = $endValue")
//                val curX = startValue.x + (endValue.x - startValue.x) * fraction
//                val curY = startValue.y + (endValue.y - startValue.y) * fraction
//                return PointF(curX, curY)
//            }
//        }, PointF(100.dp, 50.dp))
//        animator2.startDelay = 1000
//        animator2.duration = 2000
//        animator2.start()
//
//        // 3、图翻转
//        val view_3 = findViewById<CameraView>(R.id.view_3)
//        val animator3_1 = ObjectAnimator.ofFloat(view_3, "rotatex", 60f)
//        val animator3_2 = ObjectAnimator.ofFloat(view_3, "degrees", 270f)
//        val animator3_3 = ObjectAnimator.ofFloat(view_3, "topRotateX", -60f)
//
//        val animatorSet = AnimatorSet()
//        animatorSet.playSequentially(animator3_1, animator3_2, animator3_3)
//        animatorSet.startDelay = 1000
//        animatorSet.duration = 2000
//        animatorSet.start()
//
//        // 4、文字轮播
//        val view_4 = findViewById<ContentTextView>(R.id.view_4)
//        val animator4 = ObjectAnimator.ofObject(view_4, "text", view_4.stringType, "10208")
////        val key1 = Keyframe.ofFloat(0.2f, 0.4f)
////        val key2 = Keyframe.ofFloat(0.6f, 0.2f)
////        val key3 = Keyframe.ofFloat(0.2f, 0.4f)
////        val valuesHolder = PropertyValuesHolder.ofKeyframe("text", key1, key2, key3)
////        val animator4 = ObjectAnimator.ofPropertyValuesHolder(view_4, valuesHolder)
//        animator4.interpolator = AccelerateDecelerateInterpolator()
//        animator4.startDelay = 1000
//        animator4.duration = 2000
//        animator4.start()
    }

}