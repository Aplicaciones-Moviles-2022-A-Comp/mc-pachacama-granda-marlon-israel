package com.example.examenbimestre2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InicioLibros : AppCompatActivity() {
    var bibliotecaSeleccionado=Biblioteca("","",0,"","",0)
    val db = Firebase.firestore
    val bibliotecas = db.collection("Bibliotecas")
    var idItemSeleccionado = 0
    var adaptador: ArrayAdapter<Libro>?=null
    var libroSeleccionado:Libro? = Libro("","","", "", 0, "", 0.0)


    var resultAnadirlibro = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                bibliotecaSeleccionado = intent.getParcelableExtra<Biblioteca>("PosBiblioteca")!!
            }
        }

    }

    var resultEditarlibro = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                bibliotecaSeleccionado = intent.getParcelableExtra<Biblioteca>("PosBiblioteca")!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_libros)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")
        bibliotecaSeleccionado = intent.getParcelableExtra<Biblioteca>("PosBiblioteca")!!
        listViewlibroes()
        val txtNombreBiblioteca=findViewById<TextView>(R.id.tv_nombreLibro)
        txtNombreBiblioteca.setText("Biblioteca: "+bibliotecaSeleccionado.nombreBiblioteca)

        val btnCrearLibro = findViewById<Button>(R.id.btn_crear_libro)
        btnCrearLibro.setOnClickListener {
            abrirActividadAddlibro(CrearLibro::class.java)
        }

        val btnVolverLibro = findViewById<Button>(R.id.btn_volver_libro)
        btnVolverLibro.setOnClickListener {
            val intentAtraslibro = Intent(this, InicioBibliotecas::class.java)
            startActivity(intentAtraslibro)
        }
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
        idItemSeleccionado = info.position
        Log.i("context-menu", "ID libro ${idItemSeleccionado}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        libroSeleccionado = adaptador!!.getItem(idItemSeleccionado)
        return when (item.itemId) {
            R.id.mi_editarLibro -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarlibro(EditarLibro::class.java)
                return true
            }
            R.id.mi_eliminarLibro -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                val bibliotecasubColeccion= bibliotecas.document("${bibliotecaSeleccionado.idBiblioteca}")
                    .collection("Libros")
                    .document("${libroSeleccionado!!.idLibro}")
                    .delete()
                    .addOnSuccessListener {
                        Log.i("Eliminar-libro","Con exito")
                    }
                    .addOnFailureListener{
                        Log.i("Eliminar-libro","Fallido")
                    }
                listViewlibroes()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }
    fun listViewlibroes() {
        val bibliotecasubColeccion= bibliotecas.document("${bibliotecaSeleccionado.idBiblioteca}")
            .collection("Libros")
            .whereEqualTo("idBiblioteca","${bibliotecaSeleccionado.idBiblioteca}")

        val libros_lv = findViewById<ListView>(R.id.lv_libros_lista)
        bibliotecasubColeccion.get().addOnSuccessListener { result ->
            var listaLibros = arrayListOf<Libro>()
            for(document in result){
                listaLibros.add(
                    Libro(
                        document.id.toString(),
                        document.data.get("idBiblioteca").toString(),
                        document.data.get("nombreLibro").toString(),
                        document.data.get("nombreAutor").toString(),
                        document.data.get("yearEdicion").toString().toInt(),
                        document.data.get("categoria").toString(),
                        document.data.get("precio").toString().toDouble(),
                    )
                )
            }
            adaptador = ArrayAdapter(
                this,
                android.R.layout.simple_expandable_list_item_1,
                listaLibros
            )
            libros_lv.adapter=adaptador
            adaptador!!.notifyDataSetChanged()

            registerForContextMenu(libros_lv)
        }
    }
    fun abrirActividadEditarlibro(
        clase: Class<*>
    ) {
        val intentEditarlibro = Intent(this, clase)
        intentEditarlibro.putExtra("libro", libroSeleccionado)
        intentEditarlibro.putExtra("posicionBibliotecaeditar",bibliotecaSeleccionado)
        resultEditarlibro.launch(intentEditarlibro)
    }

    fun abrirActividadAddlibro(
        clase: Class<*>
    ) {
        val intentAddNewlibro = Intent(this, clase)
        intentAddNewlibro.putExtra("posicionBiblioteca",bibliotecaSeleccionado)
        resultAnadirlibro.launch(intentAddNewlibro)
    }


}