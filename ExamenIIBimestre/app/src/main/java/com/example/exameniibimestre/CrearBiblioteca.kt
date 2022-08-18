package com.example.exameniibimestre

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class CrearBiblioteca : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_biblioteca)
        val btnAniadirBiblioFirestore = findViewById<Button>(R.id.btn_aniadir_biblioteca_firestore)
        val edittextNombreBiblio = findViewById<EditText>(R.id.et_NombreBiblioteca)
        btnAniadirBiblioFirestore.setOnClickListener {
            aniadirBiblioFirestore(edittextNombreBiblio.text.toString())
            edittextNombreBiblio.setText("")
            abrirDialogo()
        }
        val btnSalirBiblioFirestore = findViewById<Button>(R.id.btn_salir_aniadir_biblioteca)
        btnSalirBiblioFirestore.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
    }

    fun aniadirBiblioFirestore(nombreBiblioteca:String){
        val db = Firebase.firestore
        val biblio = db.collection("exameniib")
        val identificador = Date().time
        val datosBiblio = hashMapOf(
            "idBiblioteca" to identificador,
            "nombre" to nombreBiblioteca
        )
        biblio.document(identificador.toString()).set(datosBiblio)

    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Agregado Exitosamente")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which ->
                irActividad(MainActivity::class.java)
            }
        )
        val dialog = builder.create()
        dialog.show()
    }
}