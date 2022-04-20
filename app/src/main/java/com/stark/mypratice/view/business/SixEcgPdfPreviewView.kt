/**
 * Copyright (C), 2007-2022, 未来穿戴有限公司
 * FileName: SixEcgView
 * Author: lpq
 * Date: 2022/4/8 11:10
 * Description: 用一句话描述下
 */
package com.stark.mypratice.view.business

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.stark.mypratice.dp

/**
 *
 * @ProjectName: SKG
 * @Package: com.skg.device.watch.r6.activity
 * @ClassName: SixEcgView
 * @Description: 用一句话描述下
 * @Author: lpq
 * @CreateDate: 2022/4/8 11:10
 */
class SixEcgPdfPreviewView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var drawWidth = 0f
    var drawHeight = 0f

    // 每一个ECG占的大格数
    val EVERY_ECG_GRID_SIZE = 10

    // 点的间隔
    val POINT_INTERVAL = 0.5f.dp / 2f

    // 每一个小网格宽度
    val MINI_GRID_WIDTH = 4.5f.dp / 2f

    // 每一个大网格宽度
    val BIG_GRID_WIDTH = MINI_GRID_WIDTH * 5

    // 网格线的宽度
    val GRID_LINE_WIDTH = 0.5f.dp / 2f

    // 心电曲线的宽度
    val LINE_WIDTH = 1f.dp / 2f

    // 左侧字符线宽度
    val LEFT_CHAR_WIDTH = 1f.dp

    // 网格粗线
    private val mGridLine3Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 网格中线
    private val mGridLine2Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 网格细线
    private val mGridLine1Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    // 字符线Paint
    private val mCharPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val drawLeftMarkPath = Path()

    // 心电线
    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    val ecgPath = Path()

    // 数据
    val MAX_PARAMS = 8000
    private var ecgDataList = mutableListOf<List<Int>>()
    var ecgStartX = 0f

    val textArray = arrayOf("Ⅰ", "Ⅱ", "Ⅲ", "aVR", "aVL", "aVF")

    init {
        mGridLine3Paint.color = Color.parseColor("#FF8C8C")
        mGridLine3Paint.strokeWidth = LINE_WIDTH
        mGridLine2Paint.color = Color.parseColor("#FFCCCC")
        mGridLine2Paint.strokeWidth = GRID_LINE_WIDTH
        mGridLine1Paint.color = Color.parseColor("#FFE6E6")
        mGridLine1Paint.strokeWidth = GRID_LINE_WIDTH
        mCharPaint.color = Color.parseColor("#000000")
        mCharPaint.strokeWidth = LEFT_CHAR_WIDTH
        mCharPaint.typeface = Typeface.DEFAULT_BOLD
        mCharPaint.textSize = 4.5f.dp
        mPaint.color = Color.parseColor("#575E66")
        mPaint.strokeWidth = LINE_WIDTH
        mPaint.style = Paint.Style.STROKE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        Log.i("lpq", "onMeasure: width = $width")
        setMeasuredDimension(width, (BIG_GRID_WIDTH * EVERY_ECG_GRID_SIZE * 6).toInt())
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        caclPath(ecgDataList)
        Log.i("lpq", "onSizeChanged: drawHeight = $drawHeight drawWidth = $drawWidth")
    }

    /**
     * 计算ecgPath
     */
    private fun caclPath(data: List<List<Int>>) {
        ecgStartX = BIG_GRID_WIDTH * 3
        ecgDataList.addAll(data)
        drawWidth = width.toFloat()
        drawHeight = BIG_GRID_WIDTH * EVERY_ECG_GRID_SIZE * 6
        val scale = drawHeight / 6 * 0.8f / MAX_PARAMS / 2
        var centerY = drawHeight / 6 / 2
        var tempY = 0f
        ecgPath.reset()
        drawWidth = width.toFloat()
        Log.i("lpq", "caclPath: ecgStartX = $ecgStartX drawWidth = $drawWidth")
        ecgPath.moveTo(ecgStartX, centerY)
        for (ecgData in ecgDataList) {
            for ((index, item) in ecgData.withIndex()) {
                if (ecgStartX <= drawWidth) {
                    tempY = item * scale + centerY
                    ecgStartX += POINT_INTERVAL
                    ecgPath.lineTo(ecgStartX, tempY)
                    Log.i("lpq", "caclPath: 已画点数 = $index")
                } else {
                    Log.i("lpq", "drawEcgLine: 已画点数 = $index")
                    break
                }
            }
            ecgStartX = BIG_GRID_WIDTH * 3
            centerY += BIG_GRID_WIDTH * 10
            ecgPath.moveTo(ecgStartX, centerY)
        }
    }

    /**
     * 获取单条ECG可绘制的点数
     */
    fun getNumberOfDrawablePoints(): Int {
        Log.i("lpq", "可绘制点数: width = $width height = $height")
        val number = ((width - BIG_GRID_WIDTH * 3) / POINT_INTERVAL).toInt()
        Log.i("lpq", "可绘制点数: number = $number")
        return number
    }

    /**
     * 静态心电图
     */
    fun setData(data: List<List<Int>>) {
        ecgDataList.clear()
        caclPath(data)
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        Log.i("lpq", "onDraw: ")
        // 1、画底部网格线
        // 2、画心电线
        drawBgLine(canvas)
        drawLeftMark(canvas)
        drawEcgLine(canvas)
    }

    /**
     * 画ECG线
     */
    private fun drawEcgLine(canvas: Canvas) {
        canvas.drawPath(ecgPath, mPaint)
    }

    /**
     * 画左侧心电标识
     */
    private fun drawLeftMark(canvas: Canvas) {
        // 画字符
        val x = BIG_GRID_WIDTH / 2
        var y = BIG_GRID_WIDTH * 5
        for (i in 0..5) {
            drawLeftMarkPath.reset()
            drawLeftMarkPath.moveTo(x, y)
            drawLeftMarkPath.lineTo(x + BIG_GRID_WIDTH / 2, y)
            drawLeftMarkPath.lineTo(x + BIG_GRID_WIDTH / 2, y - BIG_GRID_WIDTH * 2)
            drawLeftMarkPath.lineTo(x + BIG_GRID_WIDTH / 2 + BIG_GRID_WIDTH, y - BIG_GRID_WIDTH * 2)
            drawLeftMarkPath.lineTo(x + BIG_GRID_WIDTH / 2 + BIG_GRID_WIDTH, y)
            drawLeftMarkPath.lineTo(x + BIG_GRID_WIDTH * 2, y)
            canvas.drawPath(drawLeftMarkPath, mPaint)
            canvas.drawText(textArray[i], x + BIG_GRID_WIDTH * 2, y - BIG_GRID_WIDTH, mCharPaint)
            y += BIG_GRID_WIDTH * EVERY_ECG_GRID_SIZE
        }
    }

    /**
     * 画背景线
     */
    private fun drawBgLine(canvas: Canvas) {
        Log.i("lpq", "drawBgLine: ")
        var curY = 0f + LINE_WIDTH
        // 画横线
        var i = 0
        while (curY <= drawHeight) {
            if (i == 0) {
                canvas.drawLine(0f, curY, drawWidth, curY, mGridLine3Paint)
            } else if (i % 5 == 0) {
                canvas.drawLine(0f, curY, drawWidth, curY, mGridLine2Paint)
            } else {
                canvas.drawLine(0f, curY, drawWidth, curY, mGridLine1Paint)
            }
            i++
            curY += MINI_GRID_WIDTH
        }
        curY -= MINI_GRID_WIDTH
        canvas.drawLine(0f, curY, drawWidth, curY, mGridLine3Paint)
        // 画纵线
        var curX = 0f + LINE_WIDTH
        var k = 0
        while (curX <= drawWidth) {
            if (k % 25 == 0) {
                canvas.drawLine(curX, 0f, curX, drawHeight, mGridLine3Paint)
            } else if (k % 5 == 0) {
                canvas.drawLine(curX, 0f, curX, drawHeight, mGridLine2Paint)
            } else {
                canvas.drawLine(curX, 0f, curX, drawHeight, mGridLine1Paint)
            }
            k++
            curX += MINI_GRID_WIDTH
        }
    }

}