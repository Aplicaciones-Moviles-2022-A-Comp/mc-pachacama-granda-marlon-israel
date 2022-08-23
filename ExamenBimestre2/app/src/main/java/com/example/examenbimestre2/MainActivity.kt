package com.example.examenbimestre2

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // JugadorBaseDeDatos.TablaJugador= ESqliteHelperJugador(this)
        //EquipoBaseDeDatos.TablaEquipo= ESqliteHelperEquipo_Jugador(this)
        //Registros.arregloEquipos_Jugadores
        val btnIniciar = findViewById<Button>(R.id.btn_iniciarExamen)


        btnIniciar.setOnClickListener{
            val intent = Intent(this, InicioBibliotecas::class.java)
            startActivity(intent)
        }

    }
}