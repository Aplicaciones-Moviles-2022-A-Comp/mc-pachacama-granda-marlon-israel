package com.example.exameniibimestre

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*

class CrearLibro : AppCompatActivity() {

    var idBiblioGlobal:Long=0

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
        setContentView(R.layout.activity_crear_libros)
        val idBiblioteca = intent.getLongExtra("idBiblioteca",0)
        idBiblioGlobal=idBiblioteca
        val btnAniadirLibroFirestore = findViewById<Button>(R.id.btn_crear_libro)
        val edittextNombreLibro = findViewById<EditText>(R.id.et_nombre_libro)
        btnAniadirLibroFirestore.setOnClickListener {
            aniadirLibroFirestore(edittextNombreLibro.text.toString())
            edittextNombreLibro.setText("")
            abrirDialogo()
        }
        val btnSalirCrearLibro = findViewById<Button>(R.id.btn_salir_crear_libro)
        btnSalirCrearLibro.setOnClickListener {
            abrirActividadParametros(VerLibros::class.java)
        }
    }

    fun aniadirLibroFirestore(nombreLibro:String){
        val db = Firebase.firestore
        val progRefUnico = db.collection("exameniib")
            .document("${idBiblioGlobal}")
            .collection("Libros")
        val identificador = Date().time
        val datosLibro = hashMapOf(
            "idLibro" to identificador,
            "nombreLibro" to nombreLibro
        )
        progRefUnico.document(identificador.toString()).set(datosLibro)

    }

    fun abrirDialogo(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Agregado Exitosamente")
        builder.setPositiveButton(
            "Aceptar",
            DialogInterface.OnClickListener{
                    dialog, which ->
                abrirActividadParametros(VerLibros::class.java)
            }
        )
        val dialog = builder.create()
        dialog.show()
    }

    fun abrirActividadParametros(
        clase:Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("idBiblioteca",idBiblioGlobal)
        contenidoIntentExplicito.launch(intentExplicito)
        //startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)//Forma deprecada
    }
}