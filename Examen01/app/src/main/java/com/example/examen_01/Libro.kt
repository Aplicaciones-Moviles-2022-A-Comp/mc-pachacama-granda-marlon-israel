package com.example.examen_01

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

class Libro : AppCompatActivity() {
    var idItemSeleccionado = 0
    var nombreBiblioteca = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_libro)
        val titulo = findViewById<TextView>(R.id.textView_libro)
        val listView = findViewById<ListView>(R.id.lv_list_view)
        val botonRegresar = findViewById<Button>(R.id.btnRegresarLibro)
        botonRegresar.setOnClickListener {
            val i = Intent(this, Biblioteca::class.java)
            startActivity(i)
        }
        val bundle = intent.extras
        nombreBiblioteca = bundle?.getString("idBiblioteca").toString()
        if (nombreBiblioteca == null)
            nombreBiblioteca = ""
        titulo.setText("$nombreBiblioteca")
        val nuevoArreglo = BBaseDeDatosMemoria.arregloBLibro.filter { it.nombreBiblioteca == nombreBiblioteca }
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            nuevoArreglo
        )
        listView.adapter = adaptador
        val botonAnadirListView = findViewById<Button>(R.id.btnCrearLibro)
        botonAnadirListView
            .setOnClickListener {
                val i = Intent(this, CrearLibro::class.java)
                i.putExtra("idBibliotecaCrear", "$nombreBiblioteca")
                startActivity(i)
            }

        registerForContextMenu(listView)
        adaptador.notifyDataSetChanged()
    }
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_libro, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
    }
    override fun onContextItemSelected(
        item: MenuItem,
    ): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                val i = Intent(this, EditarLibro::class.java)
                val nuevoArreglo = BBaseDeDatosMemoria.arregloBLibro.filter { it.nombreBiblioteca == nombreBiblioteca }
                i.putExtra("nombreBiblioteca", "${nuevoArreglo[idItemSeleccionado].nombreBiblioteca}");
                startActivity(i);
                return true
            }
            R.id.mi_eliminar -> {
                elimarLibro()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun elimarLibro() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Desea eliminar")
            .setPositiveButton("Aceptar",
                DialogInterface.OnClickListener { dialog, id ->
                    BBaseDeDatosMemoria.arregloBLibro.removeAt(idItemSeleccionado)
                    Log.i("context-menu", "Editar Posicion: ${idItemSeleccionado}")
                    val listView = findViewById<ListView>(R.id.lv_list_view)
                    val adaptador = ArrayAdapter(
                        this, // Contexto
                        android.R.layout.simple_list_item_1, // como se va a ver (XML)
                        BBaseDeDatosMemoria.arregloBLibro
                    )
                    listView.adapter = adaptador
                })
            .setNegativeButton("Cancelar",
                DialogInterface.OnClickListener { dialog, id ->
                    // User cancelled the dialog
                })
        // Create the AlertDialog object and return it
        val dialogo = builder.create()
        dialogo.show()    }
}