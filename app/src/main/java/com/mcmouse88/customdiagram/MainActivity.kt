package com.mcmouse88.customdiagram

import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.Scene
import androidx.transition.TransitionManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.animateButton)
        val root = findViewById<ViewGroup>(R.id.root)

        val scene = Scene.getSceneForLayout(root, R.layout.end_scene, this)

        button.setOnClickListener {
            TransitionManager.go(scene)
        }
    }
}