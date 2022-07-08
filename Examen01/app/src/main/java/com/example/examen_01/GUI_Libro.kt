package com.example.examen_01

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

class GUI_Libro : AppCompatActivity() {

    var idItemSeleccionado = 0
    var idBibliotecaOwner = 0
    var posicionBiblioteca = 0
    var selectedItem = 0

    var libroLista = arrayListOf<String>()
    var idBibliotecaXLibro = arrayListOf<Int>()

    var resultAddNewLibro = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                posicionBiblioteca = data?.getIntExtra("posicionBiblioteca",0)!!
            }
        }

    }

    var resultEditarLibro = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if (result.resultCode == Activity.RESULT_OK){
            if(result.data != null) {
                val data = result.data
                posicionBiblioteca = data?.getIntExtra("posicionBibliotecaEditar",0)!!
            }
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_libro)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        libroLista = arrayListOf()
        idBibliotecaXLibro = arrayListOf()


        posicionBiblioteca = intent.getIntExtra("posicionEditar",1)

        Log.i("posBiblioteca","${posicionBiblioteca}")

        val tvNombreBbibliotecaXlibro = findViewById<TextView>(R.id.tv_NombreBibliotecaXLibro)

        BBaseDeDatosMemoria.arregloBiblioteca.forEachIndexed{ indice: Int, biblioteca : BBiblioteca ->
            Log.i("testExamen","${biblioteca.idBiblioteca} -> ${biblioteca.nombreBiblioteca}")
            if (indice == posicionBiblioteca){
                idBibliotecaOwner = biblioteca.idBiblioteca
                var label = "biblioteca: ${biblioteca.nombreBiblioteca}"
                tvNombreBbibliotecaXlibro.setText(label)
            }
        }

        BBaseDeDatosMemoria.arregloBibliotecaXLibro.forEachIndexed{ indice: Int, bibliotecaXlibro : BBibliotecaXLibro ->
            if (idBibliotecaOwner == bibliotecaXlibro.idBiblioteca){
                libroLista.add(bibliotecaXlibro.nombreBilbiotecaXLibro.toString())
                idBibliotecaXLibro.add(bibliotecaXlibro.idBibliotecarXLibro)
            }
        }

        val listViewLibro = findViewById<ListView>(R.id.lv_Libro)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            libroLista
        )
        listViewLibro.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val btnAddLibro = findViewById<Button>(R.id.btn_NuevoLibro)
        btnAddLibro.setOnClickListener {
            abrirActividadAddLibro(GUI_AnadirLibro::class.java)
        }

        val btnAtrasLibro = findViewById<Button>(R.id.btn_AtrasLibro)
        btnAtrasLibro.setOnClickListener {
            val intentAtrasLibro = Intent(this, GUI_Home::class.java)
            startActivity(intentAtrasLibro)
        }

        val btnVerBD = findViewById<Button>(R.id.btn_verBD)
        btnVerBD.setOnClickListener {
            BBaseDeDatosMemoria.arregloBibliotecaXLibro.forEach{ bibliotecaXlibro:BBibliotecaXLibro ->
                Log.i("BD","${bibliotecaXlibro.idBibliotecarXLibro} -> ${bibliotecaXlibro.nombreBilbiotecaXLibro} -> ${bibliotecaXlibro.idBiblioteca} -> ${bibliotecaXlibro.idLibro}")
            }
        }

        this.registerForContextMenu(listViewLibro)

    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_pokemon, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        selectedItem = id
        idItemSeleccionado = idBibliotecaXLibro.elementAt(id)
        Log.i("id bibliotecaXlibro", "ID ${idItemSeleccionado}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar-> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadEditarLibro(GUI_EditarLibro::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                eliminarLibro(idItemSeleccionado)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadEditarLibro(
        clase: Class<*>
    ) {
        val intentEditarLibro = Intent(this, clase)
        intentEditarLibro.putExtra("Libro", idItemSeleccionado)
        intentEditarLibro.putExtra("posicionbibliotecaeditar",posicionBiblioteca)
        resultEditarLibro.launch(intentEditarLibro)
    }

    fun abrirActividadAddLibro(
        clase: Class<*>
    ) {
        val intentAddNewLibro = Intent(this, clase)
        intentAddNewLibro.putExtra("posicionbiblioteca",posicionBiblioteca)
        Log.i("positionSend","${posicionBiblioteca}")
        resultAddNewLibro.launch(intentAddNewLibro)
    }

    fun eliminarLibro(
        idLibroAeliminar: Int
    ){
        val listViewLibro = findViewById<ListView>(R.id.lv_Libro)

        var auxListabibliotecaXlibro = arrayListOf<BBibliotecaXLibro>()

        BBaseDeDatosMemoria.arregloBibliotecaXLibro.forEach{ bibliotecaXlibro:BBibliotecaXLibro ->
            if(idLibroAeliminar != bibliotecaXlibro.idBibliotecarXLibro){
                auxListabibliotecaXlibro.add(bibliotecaXlibro)
            }
        }

        BBaseDeDatosMemoria.arregloBibliotecaXLibro = auxListabibliotecaXlibro

        libroLista.removeAt(selectedItem)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            libroLista
        )
        listViewLibro.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

}