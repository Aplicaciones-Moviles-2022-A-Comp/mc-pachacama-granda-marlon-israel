package com.example.whatsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class activity_chats : AppCompatActivity() {

    val mensajes = arrayListOf<Mensaje>(
        Mensaje("Hola como estas, el sábado al futbol", "Hola yo muy bien, claro el sábado seguro"),
        Mensaje("No te olvides de llevar mis las canilleras", "Si no te preocupes, no se me olvida"),
        Mensaje("Tambien la cuota que de los $3, para el albitro", "Yo pense que ya no teniamos que pagar al albitro"),
        Mensaje("Es solo por este partido nada más", "Y si bajan todos los muchachos para el futbol?"),
        Mensaje("Si todos me confirmaron", "Hay que ganar este partido para llegar a la final"),
        Mensaje("Claro tenemos que asegurar el partido", "El Santiago juega en el otro equipo verdad?"),
        Mensaje("Si, les hiba a comentar si apostamos ese partido", "Claro de una que si le ganamos"),
        Mensaje("Ya le dije y apostamos $100", "Ahora si a ganar para festejar con la apuesta")

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chats)


        val contacto = intent.getParcelableExtra<Contacto>("contacto")

        val txtNombre = findViewById<TextView>(R.id.txt_chat_nombre)
        val img_chat = findViewById<ImageView>(R.id.img_chat)

        txtNombre.text = contacto!!.nombre
        Picasso.get().load(contacto!!.imagen).into(img_chat)

        //funcionalidad al textView mensaje

        val txtMensaje = findViewById<TextView>(R.id.txt_mensaje_chats)

        val btn_send = findViewById<ImageView>(R.id.btn_enviar)

        btn_send.setOnClickListener {

            mensajes.add(Mensaje(txtMensaje.text.toString(), "gracias por tu mensaje "))
            txtMensaje.text = ""
            irRecyclerView()
        }

        irRecyclerView()
    }

    fun irRecyclerView(){
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_chats)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MensajeAdapter(mensajes)
        recyclerView.adapter = adapter

    }

}
