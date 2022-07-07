package com.example.examen_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*

class GUI_Home : AppCompatActivity() {

    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_home)
        Log.i("ciclo-vida", "onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida", "onStart")

        val listViewEntrenador = findViewById<ListView>(R.id.lv_Bilioteca)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BBaseDeDatosMemoria.arregloBiblioteca
        )
        listViewEntrenador.adapter = adaptador
        adaptador.notifyDataSetChanged()

        this.registerForContextMenu(listViewEntrenador)

        val btnAnadirEntrenador = findViewById<Button>(R.id.btn_AnadirBiblioteca)
        btnAnadirEntrenador.setOnClickListener {
            val intentAddEntrenador = Intent(this, GUI_AnadirBiblioteca::class.java)
            startActivity(intentAddEntrenador)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            // Guardar las variables
            // primitivos
            putInt("idItemSeleccionado",idItemSeleccionado)
            putParcelableArrayList("arregloEntrenador",BBaseDeDatosMemoria.arregloBiblioteca)
            putParcelableArrayList("arregloEntrenadorXpokemon",BBaseDeDatosMemoria.arregloBibliotecaXLibro)
            putParcelableArrayList("arregloPokemon",BBaseDeDatosMemoria.arregloLibro)
        }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        idItemSeleccionado = savedInstanceState.getInt("idItemSeleccionado")
        BBaseDeDatosMemoria.arregloBiblioteca = savedInstanceState.getParcelableArrayList<BBiblioteca>("arregloBiblioteca")!!
        BBaseDeDatosMemoria.arregloBibliotecaXLibro = savedInstanceState.getParcelableArrayList<BBibliotecaXLibro>("arregloBibliotecaXLibro")!!
        BBaseDeDatosMemoria.arregloLibro = savedInstanceState.getParcelableArrayList<BLibro>("arregloLibro")!!
        if (idItemSeleccionado == null){
            idItemSeleccionado = 0
        }
        val listViewEntrenador = findViewById<ListView>(R.id.lv_Bilioteca)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BBaseDeDatosMemoria.arregloBiblioteca
        )
        listViewEntrenador.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado = id
        Log.i("context-menu", "ID ${id}")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editar -> {
                Log.i("context-menu", "Edit position: ${idItemSeleccionado}")
                abrirActividadConParametros(GUI_EditarBiblioteca::class.java)
                return true
            }
            R.id.mi_eliminar -> {
                Log.i("context-menu", "Delete position: ${idItemSeleccionado}")
                eliminarEntrenador(idItemSeleccionado)
                return true
            }
            R.id.mi_libros -> {
                Log.i("context-menu", "Libros: ${idItemSeleccionado}")
                abrirActividadConParametros(GUI_Libro::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirActividadConParametros(
        clase: Class<*>
    ) {
        val intentEditarEntrenador = Intent(this, clase)
        intentEditarEntrenador.putExtra("posicionEditar", idItemSeleccionado)
        startActivity(intentEditarEntrenador)
    }

    fun eliminarEntrenador(
        posicioEntrenadorEnliminar: Int
    ) {
        val listViewEntrenador = findViewById<ListView>(R.id.lv_Bilioteca)

        var entrenadorAeliminar = BBaseDeDatosMemoria.arregloBiblioteca.elementAt(posicioEntrenadorEnliminar)
        var idEntrenadorAeliminar = entrenadorAeliminar.idBiblioteca

        var auxListaEntrenadorXpokemon = arrayListOf<BBibliotecaXLibro>()

        BBaseDeDatosMemoria.arregloBibliotecaXLibro.forEachIndexed{ indice: Int, entrenadorXpokemon: BBibliotecaXLibro ->
            if(idEntrenadorAeliminar != entrenadorXpokemon.idBiblioteca){
                auxListaEntrenadorXpokemon.add(entrenadorXpokemon)
            }
        }

        BBaseDeDatosMemoria.arregloBiblioteca.removeAt(posicioEntrenadorEnliminar)
        BBaseDeDatosMemoria.arregloBibliotecaXLibro = auxListaEntrenadorXpokemon

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            BBaseDeDatosMemoria.arregloBiblioteca
        )
        listViewEntrenador.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }
}