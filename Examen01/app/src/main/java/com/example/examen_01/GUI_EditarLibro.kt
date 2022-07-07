package com.example.examen_01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.material.textfield.TextInputEditText

class GUI_EditarLibro : AppCompatActivity() {

    var posicionEntrenador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gui_editar_libro)
    }

    override fun onStart() {
        Log.i("ciclo-vida", "onStart")
        super.onStart()

        val idEntrenadoXPokemon = intent.getIntExtra("pokemon",1)
        posicionEntrenador = intent.getIntExtra("posicionEntrenadoreditar",1)

        val txtInNombreEditarPokemon = findViewById<TextInputEditText>(R.id.txtIn_NombrePokemonEdit)
        val txtInEspecieEditarPokemon = findViewById<TextInputEditText>(R.id.txtIn_EspeciePokemonEdit)
        val txtInTipoEditarPokemon = findViewById<TextInputEditText>(R.id.txtIn_TipoPokemonEdit)

        var idPokemon: Int = 0

        BBaseDeDatosMemoria.arregloEntrenadorXPokemon.forEachIndexed{ indice: Int, entrenadorXpokemon : BBibliotecaXLibro ->
            if (idEntrenadoXPokemon == entrenadorXpokemon.idBEntrenadorXPokemon){
                txtInNombreEditarPokemon.setText(entrenadorXpokemon.nombreEntrenadorXPokemon)
                idPokemon = entrenadorXpokemon.idPokemon
            }
        }

        BBaseDeDatosMemoria.arregloPokemon.forEachIndexed{ indice: Int, pokemon : BLibro ->
            if (idPokemon == pokemon.idPokemon){
                txtInEspecieEditarPokemon.setText(pokemon.nombre)
                txtInTipoEditarPokemon.setText(pokemon.tipo)
            }
        }

        val btnActualizarEditarPokemon = findViewById<Button>(R.id.btn_ActualizarEditarPokemon)
        btnActualizarEditarPokemon.setOnClickListener {
            BBaseDeDatosMemoria.arregloEntrenadorXPokemon.forEachIndexed{ indice: Int, entrenadorXpokemon: BBibliotecaXLibro ->
                if (idEntrenadoXPokemon == entrenadorXpokemon.idBEntrenadorXPokemon){
                    Log.i("editar","${txtInNombreEditarPokemon.text.toString()}")
                    entrenadorXpokemon.nombreEntrenadorXPokemon = (txtInNombreEditarPokemon.text.toString())
                }
            }
            devolverRespuesta()
        }

        val btnCancelarEditarPokemon = findViewById<Button>(R.id.btn_CancelarEditarPokemon)
        btnCancelarEditarPokemon.setOnClickListener{
            devolverRespuesta()
        }

    }

    fun devolverRespuesta(){
        val intentDevolverParametros = Intent()
        intentDevolverParametros.putExtra("posicionEntrenadoreditar",posicionEntrenador)
        setResult(
            RESULT_OK,
            intentDevolverParametros
        )
        finish()
    }

}