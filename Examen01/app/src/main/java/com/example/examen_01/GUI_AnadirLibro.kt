package com.example.examen_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.google.android.material.textfield.TextInputEditText

class GUI_AnadirLibro : AppCompatActivity() {

    var nextId = 0
    var lastId = 0
    var nombreBiblioteca = ""
    var idTipoLibroSeleccionado = 0
    var posicionLibro = 0
    var idBibliotecaOwner = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_anadir_libro)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

        posicionLibro = intent.getIntExtra("posicionLibro",-1)

        Log.i("posBiblioteca","${posicionLibro}")

        val availablreLibros = arrayListOf<String>()

        BBaseDeDatosMemoria.arregloLibro.forEachIndexed{ indice: Int, libro : BLibro ->
            //Log.i("testExamen","${biblioteca.idbiblioteca} -> ${biblioteca.nombreBiblioteca}")
            availablreLibros.add(libro.nombreBiblioteca.toString())
        }

        val spinnerTipolibro = findViewById<Spinner>(R.id.id_spinner_tipo_pokemon)
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,availablreLibros)
        spinnerTipolibro.adapter = adaptador

        spinnerTipolibro.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, positionTipolibro: Int, p3: Long) {
                idTipoLibroSeleccionado = positionTipolibro + 1
                Log.i("libro seleccionado","${idTipoLibroSeleccionado}")
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        BBaseDeDatosMemoria.arregloBiblioteca.forEachIndexed{ indice: Int, biblioteca : BBiblioteca ->
            //Log.i("testExamen","${biblioteca.idbiblioteca} -> ${biblioteca.nombreBiblioteca}")
            if (indice == posicionLibro){
                idBibliotecaOwner = biblioteca.idBiblioteca
            }
        }

        var longitudListalibro = BBaseDeDatosMemoria.arregloBibliotecaXLibro.lastIndex

        BBaseDeDatosMemoria.arregloBibliotecaXLibro.forEachIndexed{ indice: Int, bibliotecaXlibro : BBibliotecaXLibro ->
            Log.i("testExamen","${bibliotecaXlibro.nombreBilbiotecaXLibro} -> ${bibliotecaXlibro.idLibro}")
            if (indice == longitudListalibro){
                lastId = bibliotecaXlibro.idBibliotecarXLibro
            }
        }

        nextId = lastId+1

        var txtInNombreBiblioteca = findViewById<TextInputEditText>(R.id.txtIn_NombreLibro)

        var btnAddlibro= findViewById<Button>(R.id.btn_AddBiblioteca)
        btnAddlibro.setOnClickListener {
            var nombreBiblioteca = txtInNombreBiblioteca.text.toString()
            BBaseDeDatosMemoria.arregloBibliotecaXLibro.add(
                BBibliotecaXLibro(nextId,nombreBiblioteca,idBibliotecaOwner,idTipoLibroSeleccionado)
            )
            devolverRespuesta()
        }

        var btnCancelarlibro = findViewById<Button>(R.id.btn_Cancellibro)
        btnCancelarlibro.setOnClickListener {
            devolverRespuesta()
        }
    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionLibro",posicionLibro)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}