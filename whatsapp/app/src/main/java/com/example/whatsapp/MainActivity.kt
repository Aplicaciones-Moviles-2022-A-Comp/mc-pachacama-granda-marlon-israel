package com.example.whatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    val contactos = listOf<Contacto>(
        Contacto("https://eststatic.com/2676/conversions/malas-personas-default.jpg",
            "Daniel",
            "Hola como estas, el sábado al futbol", "09 dic."),
        Contacto("https://scontent.fuio4-1.fna.fbcdn.net/v/t39.30808-6/248695773_4442076255881659_1302497224933349587_n.jpg?_nc_cat=105&ccb=1-5&_nc_sid=174925&_nc_eui2=AeGEk6soWoJIljE6vPHbxhEKkl1mwzLeB-GSXWbDMt4H4dErEzi7Nko9Vo3FLDMQftT9TXFIzlsQaZcQSGQmMck8&_nc_ohc=iNP0XWBs5hMAX_yBuRl&_nc_ht=scontent.fuio4-1.fna&oh=00_AT8fEZ-pDGzajbCjLxCHEVwu0vbVET1h8T3q4GndebxkQQ&oe=620A3BCF",
            "Brenda",
            "Hola como estas, el sábado al futbol", "09 dic."),
        Contacto("https://www.caritas.org.mx/wp-content/uploads/2019/02/cualidades-persona-humanitaria.jpg",
            "Caren",
            "Hola como estas, el sábado al futbol", "09 dic."),
        Contacto("https://images.unsplash.com/photo-1552058544-f2b08422138a?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=344&q=80",
            "Diego",
            "Hola como estas, el sábado al futbol", "09 dic."),
        Contacto("https://images.unsplash.com/photo-1504593811423-6dd665756598?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80",
            "Xavier",
            "Hola como estas, el sábado al futbol", "01 dic."),
        Contacto("https://scontent.fuio4-1.fna.fbcdn.net/v/t31.18172-8/26114400_1557407987683010_3886761956796531471_o.jpg?_nc_cat=108&ccb=1-5&_nc_sid=8bfeb9&_nc_eui2=AeEucBsl4Kd-fON5pwnRFVrhyj4ZjHrHrmnKPhmMeseuaQtznzDF0bIX0CVLDgn6ni8b_lwGSJwfyhMibbEwK-n7&_nc_ohc=RSEkAjFZsvsAX8_AF4k&tn=mZWQwc9jkhkWwciI&_nc_ht=scontent.fuio4-1.fna&oh=00_AT9lDLq6i2IhaI11sumz_APb9QJuvnlGQjq3GAOzA3drtg&oe=622ABEDD",
            "Nathy",
            "Hola como estas, el sábado al futbol", "01 dic."),
        Contacto("https://scontent.fuio4-1.fna.fbcdn.net/v/t39.30808-6/270179775_2194156930723736_8177676972026706337_n.jpg?_nc_cat=106&ccb=1-5&_nc_sid=174925&_nc_eui2=AeFqtAi9SgsdsP6Ct1wJ5tW8qslXz0cH8aKqyVfPRwfxooooCodF6SB9jK1dWes5IwCY-Fr1viVvGbhAHEAWLkmM&_nc_ohc=M9i49sXNLyUAX--UWZQ&_nc_ht=scontent.fuio4-1.fna&oh=00_AT9EMYjWTwx0f2I1uL2Lz7SKalO-mk24FDCFwGwgdljuOw&oe=6209E8AA",
            "Kathy",
            "Hola como estas, el sábado al futbol", "01 dic."),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        irRecyclerView()
    }

    fun irRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewLista)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ContactoAdapter(contactos)
        recyclerView.adapter = adapter
    }
}