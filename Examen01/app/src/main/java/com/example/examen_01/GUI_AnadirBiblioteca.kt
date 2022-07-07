package com.example.examen_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class GUI_AnadirBiblioteca : AppCompatActivity() {

    var nextId = 0
    var lastId = 0
    var nombreBiblioteca = ""
    var yearFundacion= ""
    var ciudad= ""
    var direccion= ""
    var telefono= ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_anadir_biblioteca)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

        var longitudListaBiblioteca = BBaseDeDatosMemoria.arregloBiblioteca.lastIndex

        BBaseDeDatosMemoria.arregloBiblioteca.forEachIndexed{ indice: Int, biblioteca : BBiblioteca ->
            Log.i("testExamen","${biblioteca.idBiblioteca} -> ${biblioteca.nombreBiblioteca}")
            if (indice == longitudListaBiblioteca){
                lastId = biblioteca.idBiblioteca
            }
        }

        nextId = lastId+1

        var txtInNombreBiblioteca = findViewById<TextInputEditText>(R.id.txtIn_NombreBiblioteca)
        var txtInYearFundacion = findViewById<TextInputEditText>(R.id.txtIn_YearFundacion)
        var txtInCiudad = findViewById<TextInputEditText>(R.id.txtIn_Ciudad)
        var txtInDireccion = findViewById<TextInputEditText>(R.id.txtln_Direccion)
        var txtInTelefono = findViewById<TextInputEditText>(R.id.txtln_Categoria)



        var btnAddbiblioteca = findViewById<Button>(R.id.btn_AddBiblioteca)
        btnAddbiblioteca.setOnClickListener {
            nombreBiblioteca = txtInNombreBiblioteca.text.toString()
            yearFundacion = txtInYearFundacion.text.toString()
            ciudad = txtInCiudad.text.toString()
            direccion = txtInDireccion.text.toString()
            telefono = txtInTelefono.text.toString()
            BBaseDeDatosMemoria.arregloBiblioteca.add(
                BBiblioteca(nextId,nombreBiblioteca, yearFundacion,ciudad,direccion,telefono)
            )
            val intentAddSucces = Intent(this, GUI_Home::class.java)
            startActivity(intentAddSucces)
        }

        var btnCancelarbiblioteca = findViewById<Button>(R.id.btn_CancelBiblioteca)
        btnCancelarbiblioteca.setOnClickListener {
            val intentAddCancel = Intent(this, GUI_Home::class.java)
            startActivity(intentAddCancel)
        }
    }

}