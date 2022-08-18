package com.example.exameniibimestre

class Biblioteca(
    var idBiblioteca:Long?,
    var nombreBiblioteca:String?
) {
    override fun toString(): String {
        return "${nombreBiblioteca}"
    }
}