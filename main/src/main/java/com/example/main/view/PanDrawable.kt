package com.example.main.view

import android.graphics.*
import android.graphics.drawable.Drawable

class PanDrawable :Drawable(){

    private val paintBlack = Paint()
    private val paintWhite = Paint()
    private var x = 1f
    init {
        paintBlack.color = Color.BLACK
        paintBlack.style = Paint.Style.FILL
        paintWhite.color = Color.WHITE
        paintWhite.style = Paint.Style.FILL
    }
    override fun draw(canvas: Canvas) {
        val width = canvas.width.toFloat()
        val real = width*(1-x)
        val height = canvas.height.toFloat()
        canvas.drawRect(0f,0f,real,height,paintBlack)
        canvas.drawRect(real,0f,width,height,paintWhite)
    }



    fun updateX(x:Float){
       this.x = x
        invalidateSelf()
    }
    override fun setAlpha(alpha: Int) {
        paintBlack.alpha = alpha
        paintWhite.alpha = alpha
        invalidateSelf()
    }

    override fun getOpacity(): Int {
      return PixelFormat.TRANSLUCENT
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
        paintBlack.colorFilter = colorFilter
        paintWhite.colorFilter = colorFilter
        invalidateSelf()
    }


}