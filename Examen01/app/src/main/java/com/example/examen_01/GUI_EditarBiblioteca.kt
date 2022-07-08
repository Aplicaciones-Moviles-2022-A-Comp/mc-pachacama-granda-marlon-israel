package com.example.examen_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class GUI_EditarBiblioteca : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo-vida", "onCreate")
        setContentView(R.layout.activity_gui_editar_biblioteca)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val posicionbiblioteca = intent.getIntExtra("posicionEditar",1)

        var txtInNombreBiblioteca = findViewById<TextInputEditText>(R.id.txtIn_NombreBiblioteca)
        var txtInYearFundacion = findViewById<TextInputEditText>(R.id.txtIn_YearFundacion)
        var txtInCiudad = findViewById<TextInputEditText>(R.id.txtIn_Ciudad)
        var txtInDireccion = findViewById<TextInputEditText>(R.id.txtln_Direccion)
        var txtInTelefono = findViewById<TextInputEditText>(R.id.txtln_Telefono)

        BBaseDeDatosMemoria.arregloBiblioteca.forEachIndexed{ indice: Int, biblioteca : BBiblioteca ->
            Log.i("testExamen","${biblioteca.idBiblioteca} -> ${biblioteca.nombreBiblioteca}")
            if (indice == posicionbiblioteca){
                txtInNombreBiblioteca.setText(biblioteca.nombreBiblioteca)
                txtInYearFundacion.setText(biblioteca.yearFundacion)
                txtInCiudad.setText(biblioteca.ciudad)
                txtInDireccion.setText(biblioteca.direccion)
                txtInTelefono.setText(biblioteca.telefono)

            }
        }

        val btnActualizarEditar = findViewById<Button>(R.id.btn_ActualizarEditar)
        btnActualizarEditar.setOnClickListener {
            BBaseDeDatosMemoria.arregloBiblioteca.forEachIndexed{ indice: Int, biblioteca: BBiblioteca ->
                if (indice == posicionbiblioteca){
                    Log.i("editar","${txtInNombreBiblioteca.text.toString()}")
                    biblioteca.nombreBiblioteca = (txtInNombreBiblioteca.text.toString())
                    biblioteca.yearFundacion = (txtInYearFundacion.text.toString())
                    biblioteca.ciudad = (txtInCiudad.text.toString())
                    biblioteca.direccion = (txtInDireccion.text.toString())
                    biblioteca.telefono = (txtInTelefono.text.toString())
                }
            }
            val intentEditSucces = Intent(this, GUI_Home::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelarEditar = findViewById<Button>(R.id.btn_CancelarEditar)
        btnCancelarEditar.setOnClickListener{
            val intentEditCancel = Intent(this, GUI_Home::class.java)
            startActivity(intentEditCancel)
        }

    }
}