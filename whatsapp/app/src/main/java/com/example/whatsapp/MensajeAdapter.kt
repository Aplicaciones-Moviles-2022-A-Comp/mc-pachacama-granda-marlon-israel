package com.example.whatsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class MensajeAdapter (val mensajes:List<Mensaje>): RecyclerView.Adapter<MensajeAdapter.MensajeHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MensajeHolder{
        val layout = LayoutInflater.from(parent.context)
        return MensajeHolder(layout.inflate(R.layout.item_mensaje, parent, false))
    }

    override fun onBindViewHolder(holder: MensajeAdapter.MensajeHolder, position: Int) {
        holder.render(mensajes[position])
    }

    override fun getItemCount(): Int {
        return mensajes.size
    }

    inner class MensajeHolder(val view: View):RecyclerView.ViewHolder(view){

        val mensajeEnviado: TextView
        val mensajeRecibido: TextView

        init {
            mensajeEnviado = view.findViewById(R.id.txt_mensaje_enviado)
            mensajeRecibido = view.findViewById(R.id.txt_mensaje_recibido)
        }

        fun render(mensaje: Mensaje){
            mensajeEnviado.text = mensaje.mensaje_enviado
            mensajeRecibido.text = mensaje.mensaje_recibido
        }

    }

}