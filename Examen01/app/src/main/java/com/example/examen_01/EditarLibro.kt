package com.example.examen_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class EditarLibro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_libro)
        val titulo = findViewById<TextView>(R.id.txtTituloBiblioteca)
        val bundle = intent.extras
        var indice = bundle?.getString("nombreBiblioteca")
        if (indice == null)
            indice = ""
        titulo.setText("$indice")

        val nombreLibro = findViewById<EditText>(R.id.txtUpdateIng1)
        val nombreBiblioteca = findViewById<EditText>(R.id.txtUpdateIng2)
        val autor = findViewById<EditText>(R.id.txtUpdateIng3)
        val yearEdicion = findViewById<EditText>(R.id.txtUpdateIng4)
        val categoria = findViewById<EditText>(R.id.txtUpdateIng5)
        val boton = findViewById<Button>(R.id.btnUpdateRaza)

        BBaseDeDatosMemoria.arregloBLibro.filter { it.nombreLibro == indice }
            .map {
                nombreLibro.setText(it.nombreLibro)
                nombreBiblioteca.setText(it.nombreBiblioteca)
                autor.setText(it.autor)
                yearEdicion.setText(it.yearEdicion)
                categoria.setText(it.categoria)
            }

        boton.setOnClickListener {

            if(nombreLibro.text.isEmpty() || nombreBiblioteca.text.isEmpty() || autor.text.isEmpty()
                || yearEdicion.text.isEmpty() || categoria.text.isEmpty()){
                val toast = Toast.makeText(this, "Campos son obligatorios", Toast.LENGTH_SHORT)
                toast.show()
            }else{
                editarIngrediente(indice, nombreLibro, nombreBiblioteca, autor, yearEdicion, categoria)
            }
        }
    }
    fun editarIngrediente(
        nombre: String,
        nombreLibro: EditText,
        nombreBiblioteca: EditText,
        autor: EditText,
        yearEdicion: EditText,
        categoria: EditText
    ) {
        var aux = ""
        BBaseDeDatosMemoria.arregloBLibro.filter { it.nombreLibro == nombre }
            .map {
                it.nombreLibro = nombreLibro.text.toString()
                it.nombreBiblioteca = nombreBiblioteca.text.toString()
                it.autor = autor.text.toString()
                it.yearEdicion = yearEdicion.text.toString()
                it.categoria = categoria.text.toString()
                it.nombreLibro = it.nombreLibro
                aux = it.nombreLibro.toString()
            }
        abrirActividad(Libro::class.java,aux )
    }
    private fun abrirActividad(
        clase: Class<*>,
        indice: String,
    ) {
        val i = Intent(this, clase)
        i.putExtra("idPlato",indice )
        startActivity(i);
    }
}