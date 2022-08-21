package com.example.proyectosegundobimestre

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GUI_SelectTypeLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_select_type_login)
    }

    override fun onStart() {
        super.onStart()

        val btn_type_passwor = findViewById<ImageButton>(R.id.btn_pass_option)
        btn_type_passwor.setOnClickListener {
            val openLoginPassword = Intent(this, GUI_LoginPassword::class.java)
            startActivity(openLoginPassword)
        }

    }

}