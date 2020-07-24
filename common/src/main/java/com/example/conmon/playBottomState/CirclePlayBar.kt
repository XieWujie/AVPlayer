package com.example.conmon.playBottomState

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class CirclePlayBar :View{

    private var radius = 0f
    private lateinit var info:PaintInfo

    constructor(context:Context):super(context)
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet)
    private val redPaint:Paint = Paint()
    private val grayPaint:Paint = Paint()
    private var angle = 0f
    private var allTime = 0f
    private var isPlaying = false

    init {
        redPaint.color = Color.BLACK
        redPaint.strokeWidth = 5.0f
        redPaint.style = Paint.Style.STROKE
        redPaint.isDither = true
        redPaint.isAntiAlias = true
        grayPaint.color = Color.GRAY
        grayPaint.strokeWidth = 5.0f
        grayPaint.isDither = true
        grayPaint.isAntiAlias = true
        grayPaint.style = Paint.Style.STROKE
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var r = Math.min(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec))
        radius = r.toFloat()
        cacheResult(radius)
        r = MeasureSpec.makeMeasureSpec(r,MeasureSpec.EXACTLY)
        super.onMeasure(r, r)
    }


    fun setTime(time:Int){
       val  angle = (time.toFloat()/allTime) *360
        post {
            this.angle = angle
            invalidate()
        }
    }
    fun setAllTime(time: Int){
        post {
            allTime = time.toFloat()
            angle = 0f
            invalidate()
        }
    }

    fun setIsPlaying(state:Boolean){
        post {
            isPlaying = state
        }
    }
    fun getPlayState() = isPlaying

    private fun cacheResult(radius:Float){
        val b = radius/10
        val left = b*4
        val right = b*6
        val top = b*3
        val bottom = b*7
        info = PaintInfo(left,top,bottom,right)
    }

    override fun onDraw(canvas: Canvas) {
        if(isPlaying){
            canvas.drawPath(info.playingPath,redPaint)
        }else{
            canvas.drawPath(info.pausePath,redPaint)
        }
        canvas.drawArc(0f,0f,radius,radius,0f,angle,false,redPaint)
        canvas.drawArc(0f,0f,radius,radius,angle,360f,false,redPaint)
    }


    private inner class PaintInfo(left:Float, top:Float,bottom:Float,right:Float){
        val pausePath:Path = Path()
        val playingPath = Path()

        init {
            pausePath.moveTo(left,top)
            pausePath.lineTo(left,bottom)
            pausePath.lineTo(right,radius/2)
            pausePath.lineTo(left,top)
            playingPath.moveTo(left,top)
            playingPath.lineTo(left,bottom)
            playingPath.moveTo(right,top)
            playingPath.lineTo(right,bottom)
        }
    }
}