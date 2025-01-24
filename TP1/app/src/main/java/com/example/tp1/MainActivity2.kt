package com.example.tp1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val intent = intent
        if (intent != null) {
            findViewById<TextView>(R.id.textView2).text = intent.getStringExtra(EXTRA_TEXT)
        }

        findViewById<Button>(R.id.previous).setOnClickListener {
            finish()
        }
    }
}