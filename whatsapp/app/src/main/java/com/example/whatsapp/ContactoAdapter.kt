package com.example.whatsapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ContactoAdapter (val contacto:List<Contacto>): RecyclerView.Adapter<ContactoAdapter.ContactoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactoHolder {
        val layout = LayoutInflater.from(parent.context)
        return ContactoHolder(layout.inflate(R.layout.item_contacto, parent, false))
    }

    override fun onBindViewHolder(holder: ContactoHolder, position: Int) {
        holder.render(contacto[position])
    }

    override fun getItemCount(): Int {
        return contacto.size
    }

    inner class ContactoHolder(val view: View): RecyclerView.ViewHolder(view){

        val nombreTextView: TextView
        val contenidoMensaje: TextView
        val imagenContacto: ImageView
        val fecha: TextView
        val contexto = view.context

        init {
            nombreTextView = view.findViewById(R.id.txt_nombre_contacto)
            contenidoMensaje = view.findViewById(R.id.txt_mensaje)
            imagenContacto = view.findViewById(R.id.img_contacto)
            fecha = view.findViewById(R.id.txt_fecha)

        }

        fun render(contacto: Contacto){
            nombreTextView.text = contacto.nombre
            contenidoMensaje.text = contacto.mensaje
            Picasso.get().load(contacto.imagen).into(imagenContacto)
            fecha.text = contacto.fecha
            view.setOnClickListener {
                Toast.makeText(contexto, "Abriendo el chat de ${contacto.nombre}", Toast.LENGTH_SHORT).show()
                val itemExplicito = Intent(contexto, activity_chats::class.java)
                itemExplicito.putExtra("contacto", contacto)
                contexto.startActivity(itemExplicito)
            }
        }

    }

}