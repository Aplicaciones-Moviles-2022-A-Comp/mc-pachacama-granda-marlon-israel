package com.example.examen_01

class BBaseDeDatosMemoria {

    companion object{
        var arregloEntrenador = arrayListOf<BEntrenador>()
        var arregloPokemon = arrayListOf<BPokemon>()
        var arregloEntrenadorXPokemon = arrayListOf<BEntrenadorXPokemon>()

        init {
            // cargar datos entrenador
            arregloEntrenador.add(
                BEntrenador(1,"Marlon","23")
            )
            arregloEntrenador.add(
                BEntrenador(2,"Diana","22")
            )
            arregloEntrenador.add(
                BEntrenador(3,"Carolina","21")
            )

            // cargar datos pokemon
            arregloPokemon.add(
                BPokemon(1,"Pikachu","Electrico")
            )
            arregloPokemon.add(
                BPokemon(2,"Squerel","Agua")
            )
            arregloPokemon.add(
                BPokemon(3,"Bolbasor","Hoja")
            )

            // cargar datos entrenador x pokemon
            arregloEntrenadorXPokemon.add(
                BEntrenadorXPokemon(1, "sque", 1,2)
            )
            arregloEntrenadorXPokemon.add(
                BEntrenadorXPokemon(2, "bol", 1, 3)
            )
            arregloEntrenadorXPokemon.add(
                BEntrenadorXPokemon(3, "bolba",2, 3)
            )
            arregloEntrenadorXPokemon.add(
                BEntrenadorXPokemon(4, "pika",2,1)
            )
            arregloEntrenadorXPokemon.add(
                BEntrenadorXPokemon(5, "squerel",2,2)
            )
            arregloEntrenadorXPokemon.add(
                BEntrenadorXPokemon(6, "querel",3,2)
            )

        }

    }

}