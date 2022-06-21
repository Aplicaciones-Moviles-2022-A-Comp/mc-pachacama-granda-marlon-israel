package com.example.movcompmipg2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Contador : AppCompatActivity() {

    var numero = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contador)

        val txtContador = findViewById<TextView>(R.id.txt_contador)
        txtContador.text = "0"

        val botonContador = findViewById<Button>(R.id.btn_contador)
        botonContador.setOnClickListener{
            incrementarTotal()
        }
    }

    fun incrementarTotal() {
        numero += 1
        val textViewContador = findViewById<TextView>(R.id.txt_contador)
        textViewContador.text = numero.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            // GUARDAR LAS VARIABLES
            // SOLO PRIMITIVOS
            putInt("numeroGuardado", numero)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val numeroRecuperado:Int? = savedInstanceState.getInt("numeroGuardado")
        if(numeroRecuperado != null){
            numero = numeroRecuperado
            val txtContador = findViewById<TextView>(R.id.btn_contador)
            txtContador.text = numero.toString()
        }
    }
}