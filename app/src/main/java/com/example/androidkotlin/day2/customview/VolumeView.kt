package com.example.androidkotlin.day2.customview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.withStyledAttributes
import com.example.androidkotlin.R
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

private enum class VolumeSteps(val label: Int){
    OFF(R.string.volume_off),
    LOW(R.string.volume_low),
    MEDIUM(R.string.volume_medium),
    HIGH(R.string.volume_high);

    fun next() = when (this) {
        OFF -> LOW
        LOW -> MEDIUM
        MEDIUM -> HIGH
        HIGH -> OFF
    }
}

private const val RADIUS_OFFSET_LABEL = 30
private const val RADIUS_OFFSET_INDICATOR = -35

public class VolumeView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr) {

    private var radius = 0.0f
    private var volumeStep = VolumeSteps.OFF
    private val pointPosition: PointF = PointF(0.0f, 0.0f)

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
        textSize = 60.0f
        typeface = Typeface.create("", Typeface.BOLD)
    }

    private var volumeStepLowColor = 0
    private var volumeStepMediumColor = 0
    private var volumeStepMaxColor = 0

    init {
        isClickable = true
        context.withStyledAttributes(attrs, R.styleable.VolumeView){
            volumeStepLowColor = getColor(R.styleable.VolumeView_volColor1, 0)
            volumeStepMediumColor = getColor(R.styleable.VolumeView_volColor2, 0)
            volumeStepMaxColor = getColor(R.styleable.VolumeView_volColor3, 0)
        }
    }

    override fun performClick(): Boolean {
        if (super.performClick()) return true

        volumeStep = volumeStep.next()
        contentDescription = resources.getString(volumeStep.label)

        invalidate()
        return true
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = (min(w, h) / 2.0 * 0.8).toFloat()
    }

    private fun PointF.computeXYForSteps(pos: VolumeSteps, radius: Float){
        val startAngel = Math.PI * (9 / 8.0)
        val angel = startAngel + pos.ordinal * (Math.PI / 4)
        x = (radius * cos(angel).toFloat()) + width / 2
        y = (radius * sin(angel).toFloat()) + width / 2
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // set background color
        paint.color = when(volumeStep){
            VolumeSteps.OFF -> Color.GRAY
            VolumeSteps.LOW -> volumeStepLowColor
            VolumeSteps.MEDIUM -> volumeStepMediumColor
            VolumeSteps.HIGH -> volumeStepMaxColor
            else -> Color.GRAY
        } as Int

        // draw volume circle
        canvas.drawCircle((width / 2).toFloat(), (height / 2).toFloat(), radius, paint)

        // Draw the indicator circle.
        val markerRadius = radius + RADIUS_OFFSET_INDICATOR
        pointPosition.computeXYForSteps(volumeStep, markerRadius)
        paint.color = Color.BLACK
        canvas.drawCircle(pointPosition.x, pointPosition.y, radius / 12, paint)

        // Draw label
        val labelRadius = radius + RADIUS_OFFSET_LABEL
        for (i in VolumeSteps.values()){
            pointPosition.computeXYForSteps(i, labelRadius)
            val label = resources.getString(i.label)
            canvas.drawText(label, pointPosition.x, pointPosition.y, paint)
        }
    }

}