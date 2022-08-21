package com.example.proyectosegundobimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val btn_start_app = findViewById<Button>(R.id.btn_start_app)
        btn_start_app.setOnClickListener {
            val openSelectTypeLogin =  Intent(this, GUI_SelectTypeLogin::class.java)
            startActivity(openSelectTypeLogin)
        }

    }

}