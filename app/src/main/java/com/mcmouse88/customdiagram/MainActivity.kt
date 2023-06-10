package com.mcmouse88.customdiagram

import android.animation.LayoutTransition
import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.BounceInterpolator
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.animateButton)
        val root = findViewById<ViewGroup>(R.id.root)
        root.layoutTransition = LayoutTransition().apply {
            setDuration(2_000L)
            setInterpolator(LayoutTransition.CHANGE_APPEARING, BounceInterpolator())
        }

        button.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.diagram_view, root, false)
            root.addView(view, 0)
        }
    }
}