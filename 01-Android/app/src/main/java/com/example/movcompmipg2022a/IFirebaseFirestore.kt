package com.example.movcompmipg2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class IFirebaseFirestore : AppCompatActivity() {

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
        val botonOrderBy = findViewById<Button>(R.id.btn_fs_order_by)
        val db = Firebase.firestore
        val citiesRefUnico = db
            .collection("cities")
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
                            it.data?.get("name") as String?, it.data?.get("state") as String?,
                            it.data?.get("country") as String?, it.data?.get("capital") as Boolean?,
                            it.data?.get("population") as Long?, it.data?.get("regions") as ArrayList<String>
                        )
                    )
                    adaptador.notifyDataSetChanged()
                }
        }
    }

    fun anadirAArregloCiudad(
        arregloNuevo: ArrayList<ICitiesDto>,
        ciudad: QueryDocumentSnapshot,
        adaptador: ArrayAdapter<ICitiesDto>
    ) {
        Log.i("ejemplo","data name ${ciudad.data.get("name")}")

        Log.i("ejemplo","ciudad ${ciudad}")
        var a = ICitiesDto(
            ciudad.data.get("name") as String?, ciudad.data.get("state") as String?,
            ciudad.data.get("country")  as String?,ciudad.data.get("capital")  as Boolean?,
            ciudad.data.get("population")  as Long?, ciudad.data.get("regions") as ArrayList<String>
        )
        Log.i("ejemplo", "Ciudad ya rica ${a}")
        Log.i("ejemplo", "arregloNuevo ${arregloNuevo}")
        arregloNuevo.add(
            a
        )

        adaptador.notifyDataSetChanged()
    }

    fun limpiarArreglo(){
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
}