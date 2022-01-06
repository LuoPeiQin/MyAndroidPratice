/**
 * Copyright (C), 2007-2022, 未来穿戴有限公司
 * FileName: WeeklyScoreView
 * Author: lpq
 * Date: 2022/1/6 9:14
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.business

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.stark.mypratice.dp

/**
 *
 * @ProjectName: MyPratice
 * @Package: com.stark.mypratice.view.business
 * @ClassName: WeeklyScoreView
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2022/1/6 9:14
 */
class WeeklyScoreView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    // 四周间距
    private val PADDING = 2.dp
    // 点的大小
    private val POINT_WIDTH = 6.dp
    // 线的宽度
    private val LINE_WIDTH = 2.dp
    // 坐标无效值
    private val INVALID_VALUE = -1

    // 画笔
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mDashPathPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    // 虚线path
    private val dashPathEffect = DashPathEffect(floatArrayOf(4.dp, 1.dp), 0f)

    // 需要绘画的有值点坐标
    private var pointList: MutableList<PointF> = mutableListOf()
    // 需要绘画的无值点坐标
    private var noValuePointList: MutableList<PointF> = mutableListOf()
    private val normalColor = Color.parseColor("#D2D5D9")
    private val selectedColor = Color.parseColor("#42D7C8")


    // 默认可显示点的数量
    private val defaultNumber = 7
    // 需要计算的原始数值
    private val valueList: MutableList<Int> = mutableListOf()
    // 最小/最大值
    private var minLimit = 0
    private var maxLimit = 10
    // 画线时需要用到的变量
    private val lastPoint = PointF()
    private val curPoint = PointF()

    init {
        mDashPathPaint.strokeWidth = LINE_WIDTH
        mDashPathPaint.pathEffect = dashPathEffect
        mDashPathPaint.color = selectedColor
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        refreshPoint(w, h)
        val widthSpace = (w - 2 * PADDING - POINT_WIDTH) / (defaultNumber - 1)
        for (i in 0 until defaultNumber) {
            noValuePointList.add(PointF(i * widthSpace + PADDING + POINT_WIDTH / 2, height / 2f))
        }
    }

    override fun onDraw(canvas: Canvas) {
        drawLine(canvas)
        drawPoint(canvas)
    }

    /**
     * 刷新点位参数
     */
    private fun refreshPoint(w: Int, h: Int) {
        // 计算点列表的坐标
        val widthSpace = (w - 2 * PADDING - POINT_WIDTH) / (defaultNumber - 1)
        val heightSpace = (h - 2 * PADDING - POINT_WIDTH) / (maxLimit - minLimit)
        pointList.clear()
        if (valueList.size > 0) {
            for (i in valueList.indices) {
                if (valueList[i] != INVALID_VALUE) {
                    pointList.add(
                        PointF(
                            i * widthSpace + PADDING + POINT_WIDTH / 2,
                            h - valueList[i] * heightSpace - PADDING - POINT_WIDTH / 2
                        )
                    )
                } else {
                    pointList.add(PointF(INVALID_VALUE.toFloat(), INVALID_VALUE.toFloat()))
                }
            }
        }

    }

    /**
     * 通过设置空值，刷新画面
     */
    fun setNoValue() {
        valueList.clear()
        refreshPoint(width, height)
        invalidate()
    }

    /**
     * 通过设置图标参数，更新画面
     * @param list 每个点的有效值，空值传入-1
     */
    fun setValue(list: List<Int>, minLimit: Int = 0, maxLimit: Int = 10) {
        if (list.size != 7) {
            throw IllegalArgumentException("list的size必须为7，空值填-1")
        }
        list.forEach {
            if (it != -1 && (it > maxLimit || it < minLimit)) {
                throw IllegalArgumentException("list的值必须在minLimit和maxLimit范围内: value = $it minLimit = $minLimit maxLimit = $maxLimit")
            }
        }
        this.minLimit = minLimit
        this.maxLimit = maxLimit
        valueList.clear()
        valueList.addAll(list)
        refreshPoint(width, height)
        invalidate()
    }

    /**
     * 画实线和虚线
     */
    private fun drawLine(canvas: Canvas) {
        mPaint.strokeWidth = LINE_WIDTH
        mPaint.color = selectedColor
        if (pointList.size > 0) {
            var lastIndex = -1
            var curIndex = -1
            lastPoint.set(0f, 0f)
            curPoint.set(0f, 0f)
            var hasStart = true
            var hasEnd = true
            for ((index, point) in pointList.withIndex()) {
                if (point.x.toInt() != INVALID_VALUE) {
                    curIndex = index
                    curPoint.set(point.x, point.y)
                } else {
                    if (index == 0) {
                        curIndex = index
                        hasStart = false
                        curPoint.set(noValuePointList[index].x, noValuePointList[index].y)
                    } else if (index == defaultNumber - 1) {
                        curIndex = index
                        hasEnd = false
                        curPoint.set(noValuePointList[index].x, noValuePointList[index].y)
                    }
                }
                // 如果没有前一个点的坐标
                if (lastPoint.x != 0f && lastPoint.x != 0f) {
                    // 判断是画实线还是画虚线
                    val isSolidLine = if (curIndex - lastIndex == 1) {
                        if (curIndex == 1) {
                            hasStart
                        } else if (curIndex == defaultNumber - 1) {
                            hasEnd
                        } else {
                            // 画实线
                            true
                        }
                    } else {
                        // 画虚线
                        false
                    }
                    // 画线
                    if (isSolidLine) {
                        canvas.drawLine(lastPoint.x, lastPoint.y, curPoint.x, curPoint.y, mPaint)
                    } else {
                        canvas.drawLine(
                            lastPoint.x,
                            lastPoint.y,
                            curPoint.x,
                            curPoint.y,
                            mDashPathPaint
                        )
                    }
                    // 更新
                    lastPoint.set(curPoint)
                    lastIndex = curIndex
                } else {
                    if (curPoint.x != 0f) {
                        lastPoint.set(curPoint)
                        lastIndex = curIndex
                    } else {
                        curPoint.set(0f, 0f)
                    }
                }
            }
        }
    }

    /**
     * 画点
     */
    private fun drawPoint(canvas: Canvas) {
        mPaint.strokeWidth = POINT_WIDTH
        mPaint.strokeCap = Paint.Cap.ROUND
        if (pointList.size > 0) {
            for ((index, point) in pointList.withIndex()) {
                if (point.x.toInt() != INVALID_VALUE) {
                    mPaint.color = selectedColor
                    canvas.drawPoint(point.x, point.y, mPaint)
                } else {
                    if (index == 0 || index == defaultNumber - 1) {
                        mPaint.color = normalColor
                        canvas.drawPoint(
                            noValuePointList[index].x,
                            noValuePointList[index].y,
                            mPaint
                        )
                    }
                }
            }
        } else {
            for (i in 0 until defaultNumber) {
                mPaint.color = normalColor
                canvas.drawPoint(noValuePointList[i].x, noValuePointList[i].y, mPaint)
            }
        }
    }

}