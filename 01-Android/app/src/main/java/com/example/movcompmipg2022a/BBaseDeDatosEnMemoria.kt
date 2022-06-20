package com.example.movcompmipg2022a

class BBaseDeDatosEnMemoria {
    companion object{
        val arregloEntrenador = arrayListOf<BEntrenador>()

        init{
            arregloEntrenador.add(
                BEntrenador("Marlon","a@a.com")
            )
            arregloEntrenador.add(
                BEntrenador("Israel","b@b.com")
            )
            arregloEntrenador.add(
                BEntrenador("Carolina","c@c.com")
            )
        }
    }

}