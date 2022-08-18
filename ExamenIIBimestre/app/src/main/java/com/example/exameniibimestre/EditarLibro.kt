package com.example.exameniibimestre

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class EditarLibro : AppCompatActivity() {
    var idLibroGlobal:Long = 0
    var idBibliotecaGlobal:Long = 0

    val contenidoIntentExplicito = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result->
        if(result.resultCode == Activity.RESULT_OK){
            if (result.data != null){
                val data = result.data
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_libro)
        val idBibliotecaEditar = intent.getLongExtra("idBiblioteca",0)
        val idLibroEditar = intent.getLongExtra("idLibro",0)
        idLibroGlobal = idLibroEditar
        idBibliotecaGlobal = idBibliotecaEditar
        val nombreLibroEditar = intent.getStringExtra("nombreLibro")
        val btnSalirEditarLibro = findViewById<Button>(R.id.btn_salir_editar_libro)
        val btnEditarLibro = findViewById<Button>(R.id.btn_editar_libro)
        val textViewLibro = findViewById<TextView>(R.id.btn_editar_libro)
        val editTextnombreLibro = findViewById<EditText>(R.id.et_nuevo_nombre_libro)
        btnSalirEditarLibro.setOnClickListener {
            abrirActividadParametros(VerLibros::class.java)
        }
        btnEditarLibro.setOnClickListener {
            editarnombreLibro(editTextnombreLibro.text.toString(),idLibroGlobal)
            abrirActividadParametros(VerLibros::class.java)
        }
        textViewLibro.setText(nombreLibroEditar)

    }

    fun editarnombreLibro(nuevoNombre:String,idLibrorama:Long){
        val db = Firebase.firestore
        val progRefUnico = db.collection("exameniib")
            .document("${idBibliotecaGlobal}")
            .collection("Libro")
        val datosLibro = hashMapOf(
            "idLibro" to idLibrorama,
            "nombreLibro" to nuevoNombre
        )
        progRefUnico.document(idLibrorama.toString()).set(datosLibro)
    }

    fun abrirActividadParametros(
        clase:Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("idLibro",idLibroGlobal)
        intentExplicito.putExtra("idBiblioteca",idBibliotecaGlobal)
        contenidoIntentExplicito.launch(intentExplicito)
        //startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)//Forma deprecada
    }
}