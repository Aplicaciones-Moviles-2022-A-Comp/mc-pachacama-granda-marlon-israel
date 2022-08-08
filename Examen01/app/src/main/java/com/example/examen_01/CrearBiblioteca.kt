package com.example.examen_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class CrearBiblioteca : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_biblioteca)
        val nombreBiblioteca = findViewById<EditText>(R.id.txtnewNombreBiblioteca)
        val yearfoundation= findViewById<EditText>(R.id.txtYearFoundation)
        val ciudad = findViewById<EditText>(R.id.txtNewCity)
        val direccion = findViewById<EditText>(R.id.txtNewAddress)
        val telefono = findViewById<EditText>(R.id.txtNewTelephone)
        val boton = findViewById<Button>(R.id.btnCrearBiblioteca)
        boton
            .setOnClickListener {


                if(nombreBiblioteca.text.isEmpty() ||
                    yearfoundation.text.isEmpty() ||
                    ciudad.text.isEmpty() ||
                    direccion.text.isEmpty() ||
                    telefono.text.isEmpty()){
                    val toast = Toast.makeText(this, "Campos son obligatorios", Toast.LENGTH_SHORT)
                    toast.show()
                }else{
                    devolverRespuesta(nombreBiblioteca.text.toString(),
                        yearfoundation.text.toString().toDouble(),
                        ciudad.text.toString(),
                        direccion.text.toString(),
                        telefono.text.toString())
                }
            }
    }
    fun devolverRespuesta(nombre:String, precio:Double, region:String, provincia:String, descripcion:String){

        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("nuevoNombreBiblioteca", nombre )
        intentDevolverParametros.putExtra("nuevoAñoDeFudacion", precio )
        intentDevolverParametros.putExtra("nuevaCiudad", region )
        intentDevolverParametros.putExtra("nuevaDireccion", provincia )
        intentDevolverParametros.putExtra("nuevoTelefono", descripcion )
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }


}