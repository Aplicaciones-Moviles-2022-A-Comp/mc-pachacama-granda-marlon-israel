package com.example.examenbimestre2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class InicioBibliotecas : AppCompatActivity() {

        val db = Firebase.firestore
        val bibliotecas = db.collection("Bibliotecas")
        var idItemSeleccionado = 0
        var adaptador: ArrayAdapter<Biblioteca>?=null

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_inicio_bibliotecas)
            Log.i("ciclo-vida", "onCreate")
        }

        override fun onStart() {
            super.onStart()
            Log.i("ciclo-vida", "onStart")
            listarBibliotecas()
            val btnAnadirBiblioteca = findViewById<Button>(R.id.btn_crear_nueva_biblioteca)
            btnAnadirBiblioteca.setOnClickListener {
                val intentAddBiblioteca = Intent(this, CrearBiblioteca::class.java)
                startActivity(intentAddBiblioteca)
            }

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
            var bibliotecaSeleccionado:Biblioteca = adaptador!!.getItem(idItemSeleccionado)!!

            return when (item.itemId) {
                R.id.mi_editar -> {
                    Log.i("context-menu", "Edit position: ${bibliotecaSeleccionado.idBiblioteca}")
                    val abrirEditarBiblioteca = Intent(this, EditarBiblioteca::class.java)
                    abrirEditarBiblioteca.putExtra("PosBiblioteca",bibliotecaSeleccionado)
                    startActivity(abrirEditarBiblioteca)
                    return true
                }
                R.id.mi_eliminar -> {
                    Log.i("context-menu", "Delte position: ${idItemSeleccionado}")
                    bibliotecas.document("${bibliotecaSeleccionado.idBiblioteca}").delete()
                        .addOnSuccessListener {
                            Log.i("Eliminar-Bibliotecas","Exito")
                        }
                        .addOnFailureListener{
                            Log.i("Eliminar-Bibliotecas","Fallido")
                        }

                    listarBibliotecas()
                    return true
                }
                R.id.mi_libros -> {
                    Log.i("context-menu", "Libros: ${idItemSeleccionado}")
                    val abrirInicioLibros = Intent(this, InicioLibros::class.java)
                    abrirInicioLibros.putExtra("PosBibliotecas",bibliotecaSeleccionado)
                    startActivity(abrirInicioLibros)
                    return true
                }
                else -> super.onContextItemSelected(item)
            }
        }
        fun listarBibliotecas(){
            val lv_bibliotecas = findViewById<ListView>(R.id.lv_bibliotecas_lista)
            bibliotecas.get().addOnSuccessListener{ result ->
                var bibliotecaLista = arrayListOf<Biblioteca>()
                for (document in result) {
                    bibliotecaLista.add(
                        Biblioteca(
                            document.id.toString(),
                            document.get("nombreBiblioteca").toString(),
                            document.get("yearFundacion").toString().toInt(),
                            document.get("city").toString(),
                            document.get("direccion").toString(),
                            document.get("telefono").toString().toInt()
                        )
                    )
                }
                adaptador = ArrayAdapter(
                    this,
                    android.R.layout.simple_expandable_list_item_1,
                    bibliotecaLista
                )
                lv_bibliotecas.adapter = adaptador
                adaptador!!.notifyDataSetChanged()
                registerForContextMenu(lv_bibliotecas)

            }.addOnFailureListener{
                Log.i("Error", "Creacion de lista de bibliotecas fallida")
            }
        }

        fun abrirActividadConParametros(
            clase: Class<*>
        ) {
            val intentEditarBiblioteca = Intent(this, clase)
            intentEditarBiblioteca.putExtra("posicionEditar", idItemSeleccionado)
            startActivity(intentEditarBiblioteca)
        }


    }