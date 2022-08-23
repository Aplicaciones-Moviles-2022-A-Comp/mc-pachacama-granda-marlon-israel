package com.example.examenbimestre2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CrearLibro : AppCompatActivity() {

        var bibliotecaSeleccionado = Biblioteca("", "", 0, "", "", 0)
        val db = Firebase.firestore
        val bibliotecas = db.collection("Bibliotecas")
        val libros = db.collection("Libros")
        var idLibroSeleccionado = 0


        override fun onCreate(savedInstanceState: Bundle?) {
            Log.i("ciclo-vida", "onCreate")
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_crear_libro)
        }

        override fun onStart() {
            super.onStart()
            Log.i("ciclo-vida", "onStart")

            bibliotecaSeleccionado = intent.getParcelableExtra<Biblioteca>("posicionBiblioteca")!!
            val bibliotecaSubColeccion = bibliotecas.document("${bibliotecaSeleccionado.idBiblioteca}")
                .collection("Libros")

            var txtNombreLibros = findViewById<TextInputEditText>(R.id.txt_nombreLibro_crear)
            var txtNombreAutor = findViewById<TextInputEditText>(R.id.txt_nombre_autor_crear)
            var txtYearEdicion = findViewById<TextInputEditText>(R.id.txt_yearEdicion_crear)
            var txtCategoria = findViewById<TextInputEditText>(R.id.txt_categoria_crear)
            var txtPrecio = findViewById<TextInputEditText>(R.id.txt_precio_editar)

            Log.i("posBiblioteca", "${bibliotecaSeleccionado.idBiblioteca}")

            var btnAddLibro = findViewById<Button>(R.id.btn_crear_libro)
            btnAddLibro.setOnClickListener {
                var libro = hashMapOf(
                    "idBiblioteca" to bibliotecaSeleccionado.idBiblioteca,
                    "nombreLibro" to txtNombreLibros.text.toString(),
                    "nombreAutor" to txtNombreAutor.text.toString(),
                    "yearEdicion" to txtYearEdicion.text.toString(),
                    "categoria" to txtCategoria.text.toString(),
                    "precio" to txtPrecio.text.toString()
                )
                bibliotecaSubColeccion.add(libro).addOnSuccessListener {
                    Toast.makeText(this, "Libro registrado exitosamente", Toast.LENGTH_SHORT).show();
                    Log.i("Crear-Libro", "Con exito")
                }.addOnFailureListener {
                    Log.i("Crear-Libro", "Fallido")
                }

                val intentAddSucces = Intent(this, InicioLibros::class.java)
                startActivity(intentAddSucces)
            }
            var btnCancelarLibro = findViewById<Button>(R.id.btn_cancelar_libro_crear)
            btnCancelarLibro.setOnClickListener {
                respuesta()

            }
        }

        fun respuesta() {
            val intentDevolverParametros = Intent()
            intentDevolverParametros.putExtra("PosBiblioteca", bibliotecaSeleccionado)
            setResult(
                RESULT_OK,
                intentDevolverParametros
            )
            finish()
        }
    }
