package com.example.movcompmipg2022a

class BBaseDeDatosEnMemoria {
    companion object{
        val arregloEntrenador = arrayListOf<BEntrenador>()

        init{
            arregloEntrenador.add(
                BEntrenador(1,"Marlon","a@a.com")
            )
            arregloEntrenador.add(
                BEntrenador(2,"Israel","b@b.com")
            )
            arregloEntrenador.add(
                BEntrenador(3,"Carolina","c@c.com")
            )
        }
    }

}