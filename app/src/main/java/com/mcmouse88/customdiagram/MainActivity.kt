package com.mcmouse88.customdiagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mcmouse88.customdiagram.ui.DiagramView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<DiagramView>(R.id.diagram).data = listOf(
            500f,
            500f,
            500f,
            500f
        )
    }
}