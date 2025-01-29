package com.example.tp2

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp2.controller.AgeCalculator

class InfosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_infos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val intent = intent
        if (intent != null) {
            val infosText = findViewById<TextView>(R.id.infosText)
            val name = intent.getStringExtra(NAME_KEY)!!.uppercase()
            val age = AgeCalculator().calculateAge(intent.getIntExtra(BIRTH_YEAR_KEY, 0))
            infosText.text = getString(R.string.hello, name, age)
        }
    }
}