package com.example.examen_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class GUI_EditarLibro : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("ciclo-vida", "onCreate")
        setContentView(R.layout.activity_gui_editar_libro)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val posicionlibro = intent.getIntExtra("posicionEditar",1)

        var txtInNombreLibro = findViewById<TextInputEditText>(R.id.txtIn_NombreLibro)
        var txtInNombreBiblioteca = findViewById<TextInputEditText>(R.id.txtIn_NombreBiblioteca)
        var txtInAutor = findViewById<TextInputEditText>(R.id.txtIn_Autor)
        var txtInYearEdicion = findViewById<TextInputEditText>(R.id.txtln_YearEdicion)
        var txtInCategoria = findViewById<TextInputEditText>(R.id.txtln_Categoria)

        BBaseDeDatosMemoria.arregloLibro.forEachIndexed{ indice: Int, libro : BLibro ->
            Log.i("testExamen","${libro.idLibro} -> ${libro.nombreLibro}")
            if (indice == posicionlibro){
                txtInNombreLibro.setText(libro.nombreLibro)
                txtInNombreBiblioteca.setText(libro.nombreBiblioteca)
                txtInAutor.setText(libro.autor)
                txtInYearEdicion.setText(libro.yearEdicion)
                txtInCategoria.setText(libro.categoria)

            }
        }

        val btnActualizarEditar = findViewById<Button>(R.id.btn_ActualizarEditar)
        btnActualizarEditar.setOnClickListener {
            BBaseDeDatosMemoria.arregloLibro.forEachIndexed{ indice: Int, libro: BLibro ->
                if (indice == posicionlibro){
                    Log.i("editar","${txtInNombreLibro.text.toString()}")
                    libro.nombreLibro = (txtInNombreLibro.text.toString())
                    libro.nombreBiblioteca = (txtInNombreBiblioteca.text.toString())
                    libro.autor = (txtInAutor.text.toString())
                    libro.yearEdicion = (txtInYearEdicion.text.toString())
                    libro.categoria = (txtInCategoria.text.toString())
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