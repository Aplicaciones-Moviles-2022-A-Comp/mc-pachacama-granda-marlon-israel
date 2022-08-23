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

class CrearBiblioteca : AppCompatActivity() {

    val db = Firebase.firestore
    val bibliotecas = db.collection("Bibliotecas")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_biblioteca)
    }

    override fun onStart() {
        super.onStart()

        var txtInNombreBiblioteca = findViewById<TextInputEditText>(R.id.txtIn_nombre_biblioteca_editar)
        var txtInYearFoundation = findViewById<TextInputEditText>(R.id.txtIn_year_fundacion_editar)
        var txtInCity = findViewById<TextInputEditText>(R.id.txtIn_ciudad_editar)
        var txtInDireccino = findViewById<TextInputEditText>(R.id.txtIn_direccion_editar)
        var txtInTelefono = findViewById<TextInputEditText>(R.id.txtIn_telefono_editar)

        var btnCrearBiblioteca = findViewById<Button>(R.id.btn_crear_biblioteca)
        btnCrearBiblioteca.setOnClickListener {
            var biblioteca= hashMapOf(
                "nombreBiblioteca" to txtInNombreBiblioteca.text.toString(),
                "añoFundacion" to txtInYearFoundation.text.toString(),
                "ciudad" to txtInCity.text.toString(),
                "direccion" to txtInDireccino.text.toString(),
                "telefono" to txtInTelefono.text.toString())
            bibliotecas.add(biblioteca).addOnSuccessListener {
                txtInNombreBiblioteca.text!!.clear()
                txtInYearFoundation.text!!.clear()
                txtInCity.text!!.clear()
                txtInDireccino.text!!.clear()
                txtInTelefono.text!!.clear()
                Toast.makeText(this,"Biblioteca registrada con exito", Toast.LENGTH_SHORT).show();
                Log.i("Crear-Biblioteca","Success")
            }.addOnSuccessListener {
                Log.i("Crear-Biblioteca","Failed")
            }


            val intentAddSucces = Intent(this, InicioBibliotecas::class.java)
            startActivity(intentAddSucces)
        }

        var btnCancelarBiblioteca = findViewById<Button>(R.id.btn_cancelar_editar)
        btnCancelarBiblioteca.setOnClickListener {
            val intentAddCancel = Intent(this, InicioBibliotecas::class.java)
            startActivity(intentAddCancel)
        }
    }

}