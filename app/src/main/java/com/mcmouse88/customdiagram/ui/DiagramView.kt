package com.mcmouse88.customdiagram.ui

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.core.content.withStyledAttributes
import com.mcmouse88.customdiagram.R
import com.mcmouse88.customdiagram.utils.AndroidUtils
import kotlin.math.min
import kotlin.random.Random

class DiagramView @JvmOverloads constructor(
    context: Context,
    attrSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrSet, defStyleAttr, defStyleRes) {

    private var radius = 0f
    private var center = PointF()
    private var lineWidth = AndroidUtils.dp(context, 5)
    private var oval = RectF()
    private var textSize = AndroidUtils.dp(context, 20).toFloat()
    private var colors = emptyList<Int>()

    private var progress = 1f
    private var angle = -90f
    private var animator: ValueAnimator? = null
    private var rotateAnimator: ValueAnimator? = null


    var data: List<Float> = emptyList()
        set(value) {
            val sum = value.sum()
            val percentList = value.map {
                it / sum
            }
            field = percentList
            update()
            invalidate()
        }

    init {
        context.withStyledAttributes(attrSet, R.styleable.DiagramView) {
            textSize = getDimension(R.styleable.DiagramView_textSize, textSize)
            lineWidth = getDimension(R.styleable.DiagramView_lineWidth, lineWidth.toFloat()).toInt()

            colors = listOf(
                getColor(R.styleable.DiagramView_colorTopRight, generateRandomColor()),
                getColor(R.styleable.DiagramView_colorBottomRight, generateRandomColor()),
                getColor(R.styleable.DiagramView_colorBottomLeft, generateRandomColor()),
                getColor(R.styleable.DiagramView_colorTopLeft, generateRandomColor())
            )
        }
    }

    private val paintArc = Paint(
        Paint.ANTI_ALIAS_FLAG
    ).apply {
        style = Paint.Style.STROKE
        strokeWidth = lineWidth.toFloat()
        strokeCap = Paint.Cap.ROUND
    }

    private val paintText = Paint(
        Paint.ANTI_ALIAS_FLAG
    ).apply {
        textSize = this@DiagramView.textSize
        style = Paint.Style.FILL
        textAlign = Paint.Align.CENTER
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        radius = min(w, h) / 2f - lineWidth
        center = PointF(w / 2f, h / 2f)
        oval = RectF(
            center.x - radius,
            center.y - radius,
            center.x + radius,
            center.y + radius
        )
    }

    override fun onDraw(canvas: Canvas) {
        if (data.isEmpty()) return
        data.forEachIndexed { index, data ->
            val angle = data * 360f
            paintArc.color = colors.getOrElse(index) { generateRandomColor() }
            canvas.drawArc(oval, this.angle, angle * progress, false, paintArc)
            this.angle += angle
        }

        paintArc.color = colors.first()

        canvas.drawText(
            "%.2f%%".format(data.sum() * 100),
            center.x,
            center.y + paintText.textSize / 4,
            paintText
        )
    }

    fun update() {
        animator?.let {
            it.removeAllUpdateListeners()
            it.cancel()
        }

        progress = 0f

        animator = ValueAnimator.ofFloat(0f, 1f).apply {
            addUpdateListener { anim ->
                progress = anim.animatedValue as Float
                invalidate()
            }
            duration = 5_000
            interpolator = LinearInterpolator()
            start()
        }

        rotateAnimator = ValueAnimator.ofFloat(-90f, 270f).apply {
            addUpdateListener { anim ->
                angle = anim.animatedValue as Float
                invalidate()
            }
            duration = 5_000
            interpolator = LinearInterpolator()
            start()
        }
    }

    private fun generateRandomColor(): Int {
        return Random.nextInt(0xFF000000.toInt(), 0xFFFFFFFF.toInt())
    }
}