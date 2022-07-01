package com.example.examen_01

import android.content.Intent
import android.icu.number.IntegerWidth
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.core.view.get
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.w3c.dom.Text

class GUI_AnadirEntrenador : AppCompatActivity() {

    var nextId = 0
    var lastId = 0
    var nombre = ""
    var edad = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_anadir_entrenador)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

        var longitudListaEntreador = BBaseDeDatosMemoria.arregloEntrenador.lastIndex

        BBaseDeDatosMemoria.arregloEntrenador.forEachIndexed{ indice: Int, entrenador : BEntrenador ->
            Log.i("testExamen","${entrenador.idEntrenador} -> ${entrenador.nombre}")
            if (indice == longitudListaEntreador){
                lastId = entrenador.idEntrenador
            }
        }

        nextId = lastId+1

        var txtInNombre = findViewById<TextInputEditText>(R.id.txtIn_NombreEntrenador)
        var txtInEdad = findViewById<TextInputEditText>(R.id.txtIn_Edad)

        var btnAddEntrenador = findViewById<Button>(R.id.btn_AddEntrenador)
        btnAddEntrenador.setOnClickListener {
            nombre = txtInNombre.text.toString()
            edad = txtInEdad.text.toString()
            BBaseDeDatosMemoria.arregloEntrenador.add(
                BEntrenador(nextId,nombre,edad)
            )
            val intentAddSucces = Intent(this, GUI_Home::class.java)
            startActivity(intentAddSucces)
        }

        var btnCancelarEntrenador = findViewById<Button>(R.id.btn_CancelEntrenador)
        btnCancelarEntrenador.setOnClickListener {
            val intentAddCancel = Intent(this, GUI_Home::class.java)
            startActivity(intentAddCancel)
        }
    }

}