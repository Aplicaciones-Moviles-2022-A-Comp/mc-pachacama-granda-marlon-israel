package com.example.exameniibimestre

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class EditarBiblioteca : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_biblioteca)
        val nombreBiblioteca = intent.getStringExtra("nombreBiblioteca")
        val idBiblioteca = intent.getLongExtra("idBiblioteca",0)
        val textViewNombre = findViewById<TextView>(R.id.tv_nombtr_biblioteca_actual)
        textViewNombre.setText(nombreBiblioteca)
        val btnSalirEditar = findViewById<Button>(R.id.btn_salir_editar_biblioteca)
        btnSalirEditar.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
        val etNuevonombreBiblioteca= findViewById<EditText>(R.id.et_nuevo_nombre_biblioteca)
        val btnEditarnombreBiblioteca = findViewById<Button>(R.id.btn_editar_nombre_biblioteca)
        btnEditarnombreBiblioteca.setOnClickListener {
            editarnombreBiblioteca(etNuevonombreBiblioteca.text.toString(),idBiblioteca)
            etNuevonombreBiblioteca.setText("")
            irActividad(MainActivity::class.java)
        }
    }

    fun editarnombreBiblioteca(nuevoNombre:String,idBiblioteca:Long){
        val db = Firebase.firestore
        val biblioteca = db.collection("exameniib")
        val datosBiblioteca = hashMapOf(
            "idBiblioteca" to idBiblioteca,
            "nombre" to nuevoNombre
        )
        biblioteca.document(idBiblioteca.toString()).set(datosBiblioteca)
    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}