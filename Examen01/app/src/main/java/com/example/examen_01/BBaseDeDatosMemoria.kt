package com.example.examen_01

import java.time.LocalDate
import java.util.*

class BBaseDeDatosMemoria {
    companion object{
        val arregloBBiblioteca = arrayListOf<BBiblioteca>()
        val arregloBLibro = arrayListOf<BLibro>()
        init {
            arregloBBiblioteca
                .add(
                    BBiblioteca("Biblioteca Central", "1918", "Quito", "Av. America", "2934568")
                )
            arregloBBiblioteca
                .add(
                    BBiblioteca("Biblioteca ESPE", "1920", "Quito", "Av. Sangolqui", "2768908")
                )
            arregloBBiblioteca
                .add(
                    BBiblioteca("Biblioteca UDLA", "1922", "Quito", "Av. Cumbaya", "2912345")
                )

        }
    }
}