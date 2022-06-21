package com.example.movcompmipg2022a

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import io.sentry.Sentry
import io.sentry.SentryLevel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Sentry.captureMessage("testing SDK setup", SentryLevel.INFO)
        Sentry.captureMessage("testing SDK setup", SentryLevel.INFO)

        val botonCicloVida = findViewById<Button>/*se castea indicando que es un tipo Button*/(R/*indica la carpeta recursos*/.id/*muestra todos los ids*/.btn_ciclo_vida)
        botonCicloVida.setOnClickListener{
            irActividad(ACicloVida::class.java)
        }

        val botonContador = findViewById<Button>(R.id.btn_contador)
        botonContador.setOnClickListener {
            irActividad(Contador::class.java)
        }

        val botonListView = findViewById<Button>(R.id.btn_ir_list_view)
        botonListView.setOnClickListener {
            irActividad(BListView::class.java)
        }
    }

    fun irActividad (
        clase: Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent) // esta función se encuentra dentro de la clase de la cual se esta heredando (en este caso "AppCompatActivity()")
    }
}