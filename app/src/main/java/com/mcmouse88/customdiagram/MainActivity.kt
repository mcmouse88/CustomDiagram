package com.mcmouse88.customdiagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import com.mcmouse88.customdiagram.ui.DiagramView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.tv_info)
        val diagram = findViewById<DiagramView>(R.id.diagram)
        val button = findViewById<Button>(R.id.animateButton)
        diagram.data = listOf(
            500f,
            500f,
            500f,
            500f
        )

        val onAnimateClick = View.OnClickListener {
            diagram.startAnimation(
                AnimationUtils.loadAnimation(this, R.anim.animation).apply {
                    setAnimationListener(object : Animation.AnimationListener {

                        override fun onAnimationStart(animation: Animation?) {
                            textView.text = "On Animation Start"
                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            textView.text = "On Animation End"
                        }

                        override fun onAnimationRepeat(animation: Animation?) {
                            textView.text = "On Animation Repeat"
                        }
                    })
                }
            )
        }

        button.setOnClickListener(onAnimateClick)
    }
}