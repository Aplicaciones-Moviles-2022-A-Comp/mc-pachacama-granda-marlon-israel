package com.example.examenrefactorado

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarLibro : AppCompatActivity() {
    var bibliotecaSeleccionado = Biblioteca(0, "", 0, "", "", 0)
    var libroSeleccionado = Libro(0,"","","",0,"",0)
    val db = Firebase.firestore
    val bibliotecas = db.collection("Bibliotecas")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_libro)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()
        bibliotecaSeleccionado = intent.getParcelableExtra<Biblioteca>("posicionBibliotecaEditar")!!
        libroSeleccionado = intent.getParcelableExtra<Libro>("libro")!!

        var txtNombreLibros = findViewById<TextInputEditText>(R.id.txt_nombre_libro_editar)
        var txtNombreAutor = findViewById<TextInputEditText>(R.id.txt_nombre_autor_editar)
        var txtYearEdicion = findViewById<TextInputEditText>(R.id.txt_year_edicion_editar)
        var txtCategoria = findViewById<TextInputEditText>(R.id.txt_categoria_editar)
        var txtPrecio = findViewById<TextInputEditText>(R.id.txt_precio_editar)

        txtNombreLibros.setText(libroSeleccionado.nombreLibro)
        txtNombreAutor.setText(libroSeleccionado.nombreAutor)
        txtYearEdicion.setText(libroSeleccionado.yearEdicion.toString())
        txtCategoria.setText(libroSeleccionado.categoria)
        txtPrecio.setText(libroSeleccionado.precio.toString())

        val btnEditarLibro = findViewById<Button>(R.id.btn_editar_libro)
        btnEditarLibro.setOnClickListener {
            bibliotecas.document("${bibliotecaSeleccionado.idBiblioteca}")
                .collection("Librod")
                .document("${libroSeleccionado.idLibro}")
                .update(
                    "nombreLibro",txtNombreLibros.text.toString(),
                    "nombreAutor",txtNombreAutor.text.toString(),
                    "yearEdicion",txtYearEdicion.text.toString(),
                    "categoria",txtCategoria.text.toString(),
                    "precio",txtPrecio.text.toString()

                )
            Toast.makeText(this,"Jugador actualizado exitosamente", Toast.LENGTH_SHORT).show()
            val intentEditSucces = Intent(this, InicioLibros::class.java)
            startActivity(intentEditSucces)
        }

        val btnCancelar = findViewById<Button>(R.id.btn_cancelar_libro_editar)
        btnCancelar.setOnClickListener{
            respuesta()
        }

    }

    fun respuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionBibliotecaEditar",bibliotecaSeleccionado)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}