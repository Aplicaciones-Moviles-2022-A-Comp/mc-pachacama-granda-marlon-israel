package com.example.exameniibimestre

class Libro(
    var idLibros:Long?,
    var nombreLibro:String?
    ){
    override fun toString(): String {
        return "${nombreLibro}"
    }
}