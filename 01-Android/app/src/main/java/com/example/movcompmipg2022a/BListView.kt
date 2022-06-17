package com.example.movcompmipg2022a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class BListView : AppCompatActivity() {
    val arreglo: ArrayList<Int> = arrayListOf(1,2,3,4,5)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blist_view)

        val listView = findViewById<ListView>(R.id.lv_list_view)
        val adaptador = ArrayAdapter(
            this, //contexto
            android.R.layout.simple_list_item_1, //como se va a ver
            arreglo
        )
        listView.adapter = adaptador
        adaptador.notifyDataSetChanged()

        val botonAnadirListView = findViewById<Button>(R.id.btn_anadir_list_view)
        botonAnadirListView
            .setOnClickListener {
            anadirNumero(adaptador)
        }
    }
    fun anadirNumero(
        adaptador: ArrayAdapter<Int>
    ){
        arreglo.add(1)
        adaptador.notifyDataSetChanged()
    }
}