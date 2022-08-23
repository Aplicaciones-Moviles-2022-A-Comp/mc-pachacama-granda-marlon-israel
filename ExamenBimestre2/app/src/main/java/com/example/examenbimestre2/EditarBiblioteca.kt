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

class EditarBiblioteca : AppCompatActivity() {

        var bibliotecaSeleccionado = Biblioteca("", "", 0, "", "", 0)
        val db = Firebase.firestore
        val bibliotecas = db.collection("Bibliotecas")

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            Log.i("ciclo-vida", "onCreate")
            setContentView(R.layout.activity_editar_biblioteca)
        }

        override fun onStart() {
            Log.i("ciclo-vida", "onStart")
            super.onStart()

            bibliotecaSeleccionado = intent.getParcelableExtra<Biblioteca>("PosBiblioteca")!!

            val editarNombreBiblioteca = findViewById<TextInputEditText>(R.id.txtIn_nomre_biblioteca_editar)
            val editarYearFundacion = findViewById<TextInputEditText>(R.id.txtIn_year_fundacion_editar)
            val editarCiudad = findViewById<TextInputEditText>(R.id.txtIn_ciudad_editar)
            val editarDireccion = findViewById<TextInputEditText>(R.id.txtIn_direccion_editar)
            val editarTelefomo = findViewById<TextInputEditText>(R.id.txtIn_telefono_editar)

            editarNombreBiblioteca.setText(bibliotecaSeleccionado.nombreBiblioteca)
            editarYearFundacion.setText(bibliotecaSeleccionado.yearFundacion.toString())
            editarCiudad.setText(bibliotecaSeleccionado.city.toString())
            editarDireccion.setText(bibliotecaSeleccionado.direccion.toString())
            editarTelefomo.setText(bibliotecaSeleccionado.telefono.toString())

            val btnGuardarCambios = findViewById<Button>(R.id.btn_guardar_cambio)
            btnGuardarCambios.setOnClickListener {
                bibliotecas.document("${bibliotecaSeleccionado.idBiblioteca}").update(
                    "nombreBiblioteca", editarNombreBiblioteca.text.toString(),
                    "yearEdicion",editarYearFundacion.text.toString(),
                    "ciudad",editarCiudad.text.toString(),
                    "direccion",editarDireccion.text.toString(),
                    "telefono",editarTelefomo.text.toString()
                )
                Toast.makeText(this,"Biblioteca actualizado exitosamente", Toast.LENGTH_SHORT).show()

                val intentEditSucces = Intent(this, InicioBibliotecas::class.java)
                startActivity(intentEditSucces)
            }

            val btnCancelarEditar = findViewById<Button>(R.id.btn_cancelar_editar)
            btnCancelarEditar.setOnClickListener{
                val intentEditCancel = Intent(this, InicioBibliotecas::class.java)
                startActivity(intentEditCancel)
            }

        }
    }
