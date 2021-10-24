package com.example.androidkotlin.day2.practice

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.example.androidkotlin.R

class CustomCircleView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr) {

    // init paint, radius, halfWidth, halfHeight
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var halfWidth = 0
    private var halfHeight = 0
    private var radius = 0

    // init attributes specified in xml
    private var circleColor = 0
    private var borderColor = 0
    private var borderWidth = 0.0f

    //getting attributes specified in xml
    init {

    }

    // calcualte halfHeight, halfWidth, radius
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {

    }

    // draw circle and border
    override fun onDraw(canvas: Canvas) {
        //drawing the circle

        //drawing circle border
    }
}