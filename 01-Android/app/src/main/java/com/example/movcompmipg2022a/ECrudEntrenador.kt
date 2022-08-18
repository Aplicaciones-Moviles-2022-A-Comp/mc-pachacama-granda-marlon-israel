package com.example.movcompmipg2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ECrudEntrenador : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ecrud_entrenador)

        val botonCrearBDD = findViewById<Button>(R.id.btn_crear)
        botonCrearBDD
            .setOnClickListener {

                    val nombre = findViewById<EditText>(R.id.input_nombre)
                    val descripcion = findViewById<EditText>(R.id.input_descripcion)
                    EBaseDeDatos.TablaEntrenador!!.crearEntrenadorFormulario(
                    nombre.text.toString(),
                    descripcion.text.toString()
                    )
                }

        val botonBuscarBDD = findViewById<Button>(R.id.btn_consultar)
        botonBuscarBDD
            .setOnClickListener {
                val id = findViewById<EditText>(R.id.input_id)
                val nombre = findViewById<EditText>(R.id.input_nombre)
                val descripcion = findViewById<EditText>(R.id.input_descripcion)
                val entrenador = EBaseDeDatos.TablaEntrenador!!
                    .consultarEntrenadorPorId(
                    id.text.toString().toInt()
                )
                id.setText(entrenador.id.toString())
                nombre.setText(entrenador.nombre)
                descripcion.setText(entrenador.descripcion)
            }

        val botonActualizarBDD = findViewById<Button>(R.id.btn_actualizar)
        botonActualizarBDD
            .setOnClickListener {
                val id = findViewById<EditText>(R.id.input_id)
                val nombre = findViewById<EditText>(R.id.input_nombre)
                val descripcion = findViewById<EditText>(R.id.input_descripcion)
                EBaseDeDatos.TablaEntrenador!!.actualizarEntrenadorFormulario(
                    nombre.text.toString(),
                    descripcion.text.toString(),
                    id.text.toString().toInt()
                )
            }

        val botonEliminarBDD = findViewById<Button>(R.id.btn_fs_eliminar)
        botonEliminarBDD
            .setOnClickListener {
                val id = findViewById<EditText>(R.id.input_id)
                EBaseDeDatos.TablaEntrenador!!.eliminarEntrenadorFormulario(
                    id.text.toString().toInt()
                )
            }
    }
}