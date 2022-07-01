package com.example.examen_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.google.android.material.textfield.TextInputEditText
import java.util.*

class GUI_AnadirPokemon : AppCompatActivity() {

    var nextId = 0
    var lastId = 0
    var nombre = ""
    var idTipoPokemonSeleccionado = 0
    var posicionEntrenador = 0
    var idEntrenadorOwner = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ciclo-vida","onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_anadir_pokemon)
    }

    override fun onStart() {
        super.onStart()
        Log.i("ciclo-vida","onStart")

        posicionEntrenador = intent.getIntExtra("posicionEntrenador",-1)

        Log.i("posEntrendaor","${posicionEntrenador}")

        val availablrePokemons = arrayListOf<String>()

        BBaseDeDatosMemoria.arregloPokemon.forEachIndexed{ indice: Int, pokemon : BPokemon ->
            //Log.i("testExamen","${entrenador.idEntrenador} -> ${entrenador.nombre}")
            availablrePokemons.add(pokemon.nombre.toString())
        }

        val spinnerTipoPokemon = findViewById<Spinner>(R.id.id_spinner_tipo_pokemon)
        val adaptador = ArrayAdapter(this,android.R.layout.simple_spinner_item,availablrePokemons)
        spinnerTipoPokemon.adapter = adaptador

        spinnerTipoPokemon.onItemSelectedListener = object:
            AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, positionTipoPokemon: Int, p3: Long) {
                idTipoPokemonSeleccionado = positionTipoPokemon + 1
                Log.i("pokemon seleccionado","${idTipoPokemonSeleccionado}")
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        BBaseDeDatosMemoria.arregloEntrenador.forEachIndexed{ indice: Int, entrenador : BEntrenador ->
            //Log.i("testExamen","${entrenador.idEntrenador} -> ${entrenador.nombre}")
            if (indice == posicionEntrenador){
                idEntrenadorOwner = entrenador.idEntrenador
            }
        }

        var longitudListaPokemon = BBaseDeDatosMemoria.arregloEntrenadorXPokemon.lastIndex

        BBaseDeDatosMemoria.arregloEntrenadorXPokemon.forEachIndexed{ indice: Int, entrenadorXpokemon : BEntrenadorXPokemon ->
            Log.i("testExamen","${entrenadorXpokemon.nombreEntrenadorXPokemon} -> ${entrenadorXpokemon.idPokemon}")
            if (indice == longitudListaPokemon){
                lastId = entrenadorXpokemon.idBEntrenadorXPokemon
            }
        }

        nextId = lastId+1

        var txtInNombre = findViewById<TextInputEditText>(R.id.txtIn_NombrePokemon)

        var btnAddPokemon= findViewById<Button>(R.id.btn_AddPokemon)
        btnAddPokemon.setOnClickListener {
            var nombre = txtInNombre.text.toString()
            BBaseDeDatosMemoria.arregloEntrenadorXPokemon.add(
                BEntrenadorXPokemon(nextId,nombre,idEntrenadorOwner,idTipoPokemonSeleccionado)
            )
            devolverRespuesta()
        }

        var btnCancelarPokemon = findViewById<Button>(R.id.btn_CancelPokemon)
        btnCancelarPokemon.setOnClickListener {
            devolverRespuesta()
        }
    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionEntrenador",posicionEntrenador)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}