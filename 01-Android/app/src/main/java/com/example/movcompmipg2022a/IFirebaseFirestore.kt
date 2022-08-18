package com.example.movcompmipg2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class IFirebaseFirestore : AppCompatActivity() {

    var query: Query? = null

    val arreglo: ArrayList<ICitiesDto> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ifirebase_firestore)
        val listView = findViewById<ListView>(R.id.lv_firestore)
        val adaptador = ArrayAdapter(
            this, // Contexto
            android.R.layout.simple_list_item_1, // como se va a ver (XML)
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonDatosPrueba = findViewById<Button>(R.id.btn_fs_datos_prueba)
        botonDatosPrueba.setOnClickListener { crearDatosPrueba() }
        val db = Firebase.firestore
        val citiesRefUnico = db
            .collection("cities")
        val botonOrderBy = findViewById<Button>(R.id.btn_fs_order_by)
        botonOrderBy.setOnClickListener {
            limpiarArreglo()
            adaptador.notifyDataSetChanged()
            citiesRefUnico
                .orderBy("population") // NO USAMOS CON DOCUMENT xq en DOCUMENT nos devuelve 1
                .get()
                .addOnSuccessListener {
                    for (ciudad in it) {
                        anadirAArregloCiudad(arreglo, ciudad, adaptador)
                    }
                }
        }
        val botonObtenerDocumento = findViewById<Button>(R.id.btn_fs_odoc)
        botonObtenerDocumento.setOnClickListener {
            limpiarArreglo()
            adaptador.notifyDataSetChanged()
            citiesRefUnico
                .document("BJ")
                .get()
                .addOnSuccessListener {
                    arreglo.add(
                        ICitiesDto(
                            it.data?.get("name") as String?,
                            it.data?.get("state") as String?,
                            it.data?.get("country") as String?,
                            it.data?.get("capital") as Boolean?,
                            it.data?.get("population") as Long?,
                            it.data?.get("regions") as ArrayList<String>
                        )
                    )
                    adaptador.notifyDataSetChanged()
                }
        }

        val botonFirebaseIndiceCompuesto = findViewById<Button>(R.id.btn_fs_ind_comp)
        botonFirebaseIndiceCompuesto.setOnClickListener {
            limpiarArreglo()
            adaptador.notifyDataSetChanged()
            citiesRefUnico
                .whereEqualTo("capital", false)
                .whereLessThanOrEqualTo("population", 4000000)
                .orderBy("population", Query.Direction.DESCENDING)
                .get()
                .addOnSuccessListener {
                    for (ciudad in it) {
                        anadirAArregloCiudad(arreglo, ciudad, adaptador)
                    }
                }
        }

        val botonFirebaseCrear = findViewById<Button>(R.id.btn_fs_crear)
        botonFirebaseCrear.setOnClickListener {
            val db = Firebase.firestore
            val referenciaEstudianteEjemplo = db
                .collection("ejemplo")
                .document("123456789")
                .collection("estudiante")
            val identificador = Date().time
            val datosEstudiante = hashMapOf(
                "nombrwe" to "Marlon",
                "graduado" to false,
                "promedio" to 14.00,
                "direccion" to hashMapOf(
                    "direccion" to "Mitad del Mundo",
                    "numeorCalle" to 1234
                ),
                "materias" to listOf("web", "moviles")
            )
            //con identificador quemado
            referenciaEstudianteEjemplo
                .document("123456789")
                .set(datosEstudiante)
                .addOnCompleteListener {/*Si todo salio bien*/ }
                .addOnFailureListener {/*Si algo salio mal*/ }

            //sin identificador generado en  Date.time
            referenciaEstudianteEjemplo
                .document(identificador.toString())
                .set(datosEstudiante)
                .addOnCompleteListener {/*Si todo salio bien*/ }
                .addOnFailureListener {/*Si algo salio mal*/ }

            //sin identificador
            referenciaEstudianteEjemplo
                .add(datosEstudiante)
                .addOnCompleteListener {/*Si todo salio bien*/ }
                .addOnFailureListener {/*Si algo salio mal*/ }
        }

        val botonFirebaseEliminar = findViewById<Button>(R.id.btn_fs_eliminar)
        botonFirebaseEliminar.setOnClickListener {
            val db = Firebase.firestore
            val referenciaEstudianteEjemplo = db
                .collection("ejemplo")
                .document("123456789")
                .collection("estudiante")

            //con identificador quemado
            referenciaEstudianteEjemplo
                .document("123456789")
                .delete()
                .addOnCompleteListener {/*Si todo salio bien*/ }
                .addOnFailureListener {/*Si algo salio mal*/ }
        }

        val botonFirebaseEmpezarPaginar = findViewById<Button>(R.id.btn_fs_epaginar)
        botonFirebaseEmpezarPaginar.setOnClickListener {
            query = null
            consultarCiudades(adaptador)
        }

        val botonFirebasePaginar = findViewById<Button>(R.id.btn_fs_paginar)
        botonFirebasePaginar.setOnClickListener {
            query = null
            consultarCiudades(adaptador)
        }

    }

    fun consultarCiudades(
        adaptador: ArrayAdapter<ICitiesDto>
    ) {
        val db = Firebase.firestore

        val citiesRef = db
            .collection("cities")
            .orderBy("population")
            .limit(1)

        var tarea: Task<QuerySnapshot>? = null
        if (query == null) {
            tarea = citiesRef.get()//1era vez
            limpiarArreglo()
            adaptador.notifyDataSetChanged()
        } else {
            tarea = query!!.get()//consulta de la consulta anterior empezando en el nuevo documento
        }
        if (tarea != null) {
            tarea
                .addOnSuccessListener { documentSnapshots ->
                    guardarQuery(documentSnapshots, citiesRef)
                    for (ciudad in documentSnapshots) {
                        anadirAArregloCiudad(arreglo, ciudad, adaptador)
                    }
                    adaptador.notifyDataSetChanged()
                }
                .addOnFailureListener {
                    //si hay fallos
                }
        }
    }

    fun guardarQuery(documentSnapshot: QuerySnapshot, refCities: Query){
        if(documentSnapshot.size() > 0){
            val ultimoDocumento = documentSnapshot.documents[documentSnapshot.size() - 1]
            query = refCities
                .startAfter(ultimoDocumento)
        }else{

        }
    }

    fun anadirAArregloCiudad(
        arregloNuevo: ArrayList<ICitiesDto>,
        ciudad: QueryDocumentSnapshot,
        adaptador: ArrayAdapter<ICitiesDto>
    ) {
        val nuevaCiudad = ICitiesDto(
            ciudad.data.get("name") as String?, ciudad.data.get("state") as String?,
            ciudad.data.get("country") as String?, ciudad.data.get("capital") as Boolean?,
            ciudad.data.get("population") as Long?, ciudad.data.get("regions") as ArrayList<String>
        )
        arregloNuevo.add(
            nuevaCiudad
        )
        adaptador.notifyDataSetChanged()
    }

    fun limpiarArreglo() {
        arreglo.clear()
    }

    fun crearDatosPrueba() {
        val db = Firebase.firestore

        val cities = db.collection("cities")

        val data1 = hashMapOf(
            "name" to "San Francisco",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 860000,
            "regions" to listOf("west_coast", "norcal")
        )
        cities.document("SF").set(data1)

        val data2 = hashMapOf(
            "name" to "Los Angeles",
            "state" to "CA",
            "country" to "USA",
            "capital" to false,
            "population" to 3900000,
            "regions" to listOf("west_coast", "socal")
        )
        cities.document("LA").set(data2)

        val data3 = hashMapOf(
            "name" to "Washington D.C.",
            "state" to null,
            "country" to "USA",
            "capital" to true,
            "population" to 680000,
            "regions" to listOf("east_coast")
        )
        cities.document("DC").set(data3)

        val data4 = hashMapOf(
            "name" to "Tokyo",
            "state" to null,
            "country" to "Japan",
            "capital" to true,
            "population" to 9000000,
            "regions" to listOf("kanto", "honshu")
        )
        cities.document("TOK").set(data4)

        val data5 = hashMapOf(
            "name" to "Beijing",
            "state" to null,
            "country" to "China",
            "capital" to true,
            "population" to 21500000,
            "regions" to listOf("jingjinji", "hebei")
        )
        cities.document("BJ").set(data5)
    }

    /*
       < less than
       <= less than or equal to
       == equal to
       > greater than
       >= greater than or equal to
       != not equal to
       array-contains
       array-contains-any
       in
       not in
       // Obtener solo un limite de registros
       val db = Firebase.firestore
            val citiesRef = db
               .collection("cities")
               .limit(2) // solo tomamos 2 registros
       // Buscar por un solo campo '=='
            val citiesRef = db
               .collection("cities")
               .whereEqualTo("country", "China")
               // .whereEqualTo("propiedad.otraPropiedad", "valor")
               .get()
      // Buscar por un solo campo 'array-contains'
            val citiesRef = db
               .collection("cities")
               .whereArrayContainsAny("regions", "west_cost")
               // .whereEqualTo("propiedad.otraPropiedad", "valor")
               .get()
       // Buscar por dos o mas elementos campo '==' 'array-contains'
        citiesRef
            .whereEqualTo("capital", false)
            .whereArrayContainsAny("regions", arrayListOf("socal", "norcal"))
      // Buscar por un solo campo '>='
            val citiesRef = db
               .collection("cities")
               .whereGreaterThanOrEqualTo("population", 1000000)
               .get()
       // Buscar por dos o mas elementos campo '==' '>='
        citiesRef
            .whereEqualTo("capital", true)
            .whereGreaterThanOrEqualTo("population", 1000000)
      // Buscar por un solo campo '<='
            val citiesRef = db
               .collection("cities")
               .whereLessThanOrEqualTo("population", 1000000)
               .get()
       // Buscar por dos o mas elementos campo '==' '<='
        citiesRef
            .whereEqualTo("capital", true)
            .whereLessThanOrEqualTo("population", 1000000)
       // Buscar por un solo campo 'array-contains-any'
            val citiesRef = db
               .collection("cities")
               .whereArrayContainsAny("regions", listOf("west_coast", "east_coast"))
               .get()
       // Buscar por un solo campo 'in'
            val citiesRef = db
               .collection("cities")
               .whereIn("country", listOf("USA", "Japan"))
               .get()
    }*/
}