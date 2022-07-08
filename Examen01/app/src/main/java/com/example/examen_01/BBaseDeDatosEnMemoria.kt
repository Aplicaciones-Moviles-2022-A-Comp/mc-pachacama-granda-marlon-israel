package com.example.examen_01

class BBaseDeDatosMemoria {

    companion object{
        var arregloBiblioteca = arrayListOf<BBiblioteca>()
        var arregloLibro = arrayListOf<BLibro>()
        var arregloBibliotecaXLibro = arrayListOf<BBibliotecaXLibro>()

        init {
            // cargar datos biblioteca
            arregloBiblioteca.add(
                BBiblioteca(1,"Biblioteca UCE","1980","Quito","Av. America","2345678")
            )
            arregloBiblioteca.add(
                BBiblioteca(2,"Biblioteca EPN","1981","Quito","Av. ladron de Guevara","2345690")

            )
            arregloBiblioteca.add(
                BBiblioteca(3,"Biblioteca ESPE","1945","Quito","Av. Militar","21347892")

            )

            // cargar datos libros
            arregloLibro.add(
                BLibro(1,"Geometria Plana","Biblioteca EPN","Calvache","2014","Ciencias Exactas")
            )
            arregloLibro.add(
                BLibro(2,"Fisica","Biblioteca UCE","Vallejo","2010","Ciencias Exactas")
            )
            arregloLibro.add(
                BLibro(3,"Algebra","Biblioteca ESPE","Rojas","2000","Ciencias Exactas")
            )

            // cargar datos biblioteca x libro
            arregloBibliotecaXLibro.add(
                BBibliotecaXLibro(1, "Geometia Plana", 2,1)
            )
            arregloBibliotecaXLibro.add(
                BBibliotecaXLibro(2, "Fisica", 1, 1)
            )
            arregloBibliotecaXLibro.add(
                BBibliotecaXLibro(4, "Algebra",3,1)
            )
            arregloBibliotecaXLibro.add(
                BBibliotecaXLibro(3, "Quimica",1, 2)
            )
            arregloBibliotecaXLibro.add(
                BBibliotecaXLibro(5, "Calculo Vectorial",2,2)
            )
            arregloBibliotecaXLibro.add(
                BBibliotecaXLibro(6, "Bioquimica",3,2)
            )

        }

    }

}