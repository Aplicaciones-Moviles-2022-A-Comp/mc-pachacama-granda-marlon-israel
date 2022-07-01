package com.example.examen_01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity() : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnIniciar = findViewById<Button>(R.id.btn_iniciarApp)
        btnIniciar.setOnClickListener{
            val intent = Intent(this, GUI_Home::class.java)
            startActivity(intent)
        }

    }

}