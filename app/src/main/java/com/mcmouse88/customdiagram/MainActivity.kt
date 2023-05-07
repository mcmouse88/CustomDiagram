package com.mcmouse88.customdiagram

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.mcmouse88.customdiagram.ui.DiagramView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val diagram = findViewById<DiagramView>(R.id.diagram)
        val button = findViewById<Button>(R.id.animateButton)
        diagram.data = listOf(
            500f,
            500f,
            500f,
            500f
        )

        button.setOnClickListener {
            diagram.update()
        }
    }
}