package com.example.tp2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener

const val NAME_KEY = "name"
const val BIRTH_YEAR_KEY = "birthYear"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val nameView = findViewById<TextView>(R.id.nameText)
        val nameEdit = findViewById<EditText>(R.id.nameEditText)
        nameEdit.addTextChangedListener {
            nameView.text = nameEdit.text
        }

        val confirmButton = findViewById<Button>(R.id.confirmButton)
        val birthYearEdit = findViewById<EditText>(R.id.birthYearEdit)
        confirmButton.setOnClickListener {
            if (nameEdit.text.isEmpty()) {
                val toast = Toast.makeText(
                    this,
                    resources.getText(R.string.name_empty_error),
                    Toast.LENGTH_SHORT
                )
                toast.show()
            } else {
                val intent = Intent(this, InfosActivity::class.java)
                intent.putExtra(NAME_KEY, nameEdit.text.toString())
                intent.putExtra(BIRTH_YEAR_KEY, birthYearEdit.text.toString().toInt())
                startActivity(intent)
            }
        }
    }
}