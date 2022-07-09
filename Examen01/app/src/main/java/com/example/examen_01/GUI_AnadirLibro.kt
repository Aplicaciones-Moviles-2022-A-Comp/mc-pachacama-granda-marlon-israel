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
    var nombreLibro = ""
    var nombreBiblioteca = ""
    var autor = ""
    var yearEdicion = "0"
    var categoria = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_anadir_libro)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

        var longitudListaLibro = BBaseDeDatosMemoria.arregloLibro.lastIndex

        BBaseDeDatosMemoria.arregloLibro.forEachIndexed{ indice: Int, libro : BLibro ->
            Log.i("testExamen","${libro.idLibro} -> ${libro.nombreLibro}")
            if (indice == longitudListaLibro){
                lastId = libro.idLibro
            }
        }

        nextId = lastId+1

        var txtInNombreLibro = findViewById<TextInputEditText>(R.id.txtIn_NombreLibro)
        var txtInNombreBiblioteca = findViewById<TextInputEditText>(R.id.txtIn_NameBiblioteca)
        var txtInAutor = findViewById<TextInputEditText>(R.id.txtIn_Autor)
        var txtInYearEdicion = findViewById<TextInputEditText>(R.id.txtIn_YearEdicion)
        var txtInCategoria = findViewById<TextInputEditText>(R.id.txtln_Categoria)

        var btnAddlibro = findViewById<Button>(R.id.btn_AddLibro)
        btnAddlibro.setOnClickListener {
            nombreLibro = txtInNombreLibro.text.toString()
            nombreBiblioteca = txtInNombreBiblioteca.text.toString()
            autor = txtInAutor.text.toString()
            yearEdicion = txtInYearEdicion.text.toString()
            categoria = txtInCategoria.text.toString()
            BBaseDeDatosMemoria.arregloBiblioteca.add(
                BBiblioteca(nextId,nombreLibro,nombreBiblioteca,autor,yearEdicion,categoria)
            )
            val intentAddSucces = Intent(this, GUI_Home::class.java)
            startActivity(intentAddSucces)
        }

        var btnCancelarlibro = findViewById<Button>(R.id.btn_CancelLibro)
        btnCancelarlibro.setOnClickListener {
            val intentAddCancel = Intent(this, GUI_Home::class.java)
            startActivity(intentAddCancel)
        }
    }

}