package com.example.exameniibimestre

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

class MainActivity : AppCompatActivity() {

    var idItemSeleccionado=0
    var nombreBibliotecaGlobal=""
    var idBibliotecaGlobal:Long=0
    var arreglo:ArrayList<Biblioteca> = ArrayList<Biblioteca>()

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
        setContentView(R.layout.activity_main)
        val listBibliotecas = findViewById<ListView>(R.id.lv_bibliotecas)
        cargarBibliotecaInicio()
        val btnAniadirBiblioteca = findViewById<Button>(R.id.btn_aniadir_biblioteca)

        btnAniadirBiblioteca.setOnClickListener {
            irActividad(CrearBiblioteca::class.java)
        }
        registerForContextMenu(listBibliotecas)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_biblioteca,menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val id = info.position
        idItemSeleccionado=id
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.it_ver_libros->{
                val listView = findViewById<ListView>(R.id.lv_bibliotecas)
                val itemSelecionado = listView.getItemAtPosition(idItemSeleccionado)
                idBibliotecaGlobal = getIdBiblioteca(itemSelecionado as Biblioteca).toLong()
                abrirActividadParametros(VerLibros::class.java)
                "${idItemSeleccionado}"
                return true
            }
            R.id.it_editar_biblioteca->{
                "${idItemSeleccionado}"
                val listView = findViewById<ListView>(R.id.lv_bibliotecas)
                val itemSelecionado = listView.getItemAtPosition(idItemSeleccionado)
                nombreBibliotecaGlobal = getNombreBiblioteca(itemSelecionado as Biblioteca)
                idBibliotecaGlobal = getIdBiblioteca(itemSelecionado as Biblioteca).toLong()
                abrirActividadParametros(EditarBiblioteca::class.java)
                return true
            }
            R.id.it_eliminar_biblioteca->{
                "${idItemSeleccionado}"
                val listView = findViewById<ListView>(R.id.lv_bibliotecas)
                val itemSelecionado = listView.getItemAtPosition(idItemSeleccionado)
                val idBibliotecaactual = getIdBiblioteca(itemSelecionado as Biblioteca)
                eliminarBiblioteca(idBibliotecaactual)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun cargarBibliotecaInicio(){
        val db = Firebase.firestore
        val bibliotecaRefUnico = db
            .collection("exameniib")
        bibliotecaRefUnico
            .get()
            .addOnSuccessListener {
                arreglo = ArrayList<Biblioteca>()
                for (biblioteca in it) {
                    aniadirbiblioteca(arreglo, biblioteca)
                }
                actualizarListView()
            }

    }

    fun actualizarListView(){
        val db = Firebase.firestore
        val bibliotecaRefUnico = db
            .collection("exameniib")
        bibliotecaRefUnico
            .get()
            .addOnSuccessListener {
                arreglo = ArrayList<Biblioteca>()
                for (biblioteca in it) {
                    aniadirbiblioteca(arreglo, biblioteca)
                }
                actualizarVistaListView()
            }

    }

    fun actualizarVistaListView(){
        val listBibliotecas = findViewById<ListView>(R.id.lv_bibliotecas)
        //  cargarbiblioteca()
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            arreglo
        )
        listBibliotecas.adapter = adaptador
        adaptador.notifyDataSetChanged()
    }

    fun aniadirbiblioteca(
        arregloNuevo: ArrayList<Biblioteca>,
        biblioteca: QueryDocumentSnapshot
    ) {
        arregloNuevo.add(
            Biblioteca(
                biblioteca.get("idbiblioteca") as Long?,
                biblioteca.get("nombre") as String?
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
        intentExplicito.putExtra("nombrebiblioteca",nombreBibliotecaGlobal)
        intentExplicito.putExtra("idbiblioteca",idBibliotecaGlobal)
        contenidoIntentExplicito.launch(intentExplicito)
        //startActivityForResult(intentExplicito, CODIGO_RESPUESTA_INTENT_EXPLICITO)//Forma deprecada
    }

    fun eliminarBiblioteca(nombrebiblioteca:String){
        val db = Firebase.firestore
        val biblioteca = db.collection("exameniib")
        biblioteca.document(nombrebiblioteca).delete()
        actualizarListView()
    }

    fun getNombreBiblioteca(biblioteca:Biblioteca):String{
        return biblioteca.nombreBiblioteca.toString()
    }
    fun getIdBiblioteca(biblioteca:Biblioteca):String{
        return biblioteca.idBiblioteca.toString()
    }

    override fun onResume() {
        super.onResume()
        actualizarListView()
    }

}