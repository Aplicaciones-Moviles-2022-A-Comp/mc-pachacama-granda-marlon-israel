package com.example.exameniibimestre

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class VerLibros : AppCompatActivity() {

    var idBibliotecaGlobal:Long = 0
    var idItemSeleccionado = 0
    var idLibroGlobal:Long = 0
    var nombreLibroGlobal = ""
    var arregloLibro:ArrayList<Libro> = ArrayList<Libro>()

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
        setContentView(R.layout.activity_ver_libros)
        val idBiblioteca = intent.getLongExtra("idBiblioteca",0)
        idBibliotecaGlobal=idBiblioteca
        val listLibro = findViewById<ListView>(R.id.lv_libro)
        cargarLibroInicio()
        val btnSalirVerLibros = findViewById<Button>(R.id.btn_salir_de_ver_libros)
        btnSalirVerLibros.setOnClickListener {
            irActividad(MainActivity::class.java)
        }
        val btnIrCrearLibro = findViewById<Button>(R.id.btn_ir_a_crear_libros)
        btnIrCrearLibro.setOnClickListener {
            abrirActividadParametros(CrearLibro::class.java)
        }
        registerForContextMenu(listLibro)
    }

    fun cargarLibroInicio(){
        val db = Firebase.firestore
        val libroRefUnico = db.collection("exameniib")
            .document("${idBibliotecaGlobal}")
            .collection("Libro")
        libroRefUnico.get()
            .addOnSuccessListener {
                arregloLibro = ArrayList<Libro>()
                for (libro in it) {
                    aniadirLibro(arregloLibro, libro)
                }
                actualizarListView()
            }

    }


    fun actualizarListView(){
        val db = Firebase.firestore
        val libroRefUnico = db.collection("exameniib")
            .document("${idBibliotecaGlobal}")
            .collection("Libro")
        libroRefUnico
            .get()
            .addOnSuccessListener {
                arregloLibro = ArrayList<Libro>()
                for (librorama in it) {
                    aniadirLibro(arregloLibro, librorama)
                }
                actualizarVistaListView()
            }

    }

    fun actualizarVistaListView(){
        val listLibro = findViewById<ListView>(R.id.lv_libro)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arregloLibro
        )
        listLibro.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

    fun aniadirLibro(
        arregloNuevo: ArrayList<Libro>,
        so: QueryDocumentSnapshot
    ) {
        arregloNuevo.add(
            Libro(
                so.get("idlibro") as Long?,
                so.get("nombrelibro") as String?
            )
        )
    }

    fun irActividad(
        clase:Class<*>
    ){
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    fun abrirActividadParametros(
        clase:Class<*>
    ){
        val intentExplicito = Intent(this, clase)
        intentExplicito.putExtra("idlibro",idLibroGlobal)
        intentExplicito.putExtra("idBiblioteca",idBibliotecaGlobal)
        intentExplicito.putExtra("nombrelibrorama",nombreLibroGlobal)
        contenidoIntentExplicito.launch(intentExplicito)
        //startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)//Forma deprecada
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_libro,menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado=id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.it_editar_libro->{
                "${idItemSeleccionado}"
                val listView = findViewById<ListView>(R.id.lv_libro)
                val itemSelecionado = listView.getItemAtPosition(idItemSeleccionado)
                nombreLibroGlobal = getNombrelibrorama(itemSelecionado as Libro)
                idLibroGlobal = getIdlibrorama(itemSelecionado as Libro).toLong()
                abrirActividadParametros(EditarLibro::class.java)
                return true
            }
            R.id.it_eliminar_libro->{
                "${idItemSeleccionado}"
                val listView = findViewById<ListView>(R.id.lv_libro)
                val itemSelecionado = listView.getItemAtPosition(idItemSeleccionado)
                val idlibroactual = getIdlibrorama(itemSelecionado as Libro)
                eliminarlibrorama(idlibroactual)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun getNombrelibrorama(librorama:Libro):String{
        return librorama.nombreLibro.toString()
    }

    fun getIdlibrorama(librorama: Libro):String{
        return librorama.idLibros.toString()
    }

    fun eliminarlibrorama(nombreso:String){
        val db = Firebase.firestore
        val libroRefUnico = db.collection("exameniib")
            .document("${idBibliotecaGlobal}")
            .collection("Libro")
        libroRefUnico.document(nombreso).delete()
        actualizarListView()
    }

}