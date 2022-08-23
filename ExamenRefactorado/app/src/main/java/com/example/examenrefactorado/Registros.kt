package com.example.examenrefactorado

class Registros{
    companion object{

        var arregloBibliotecas_Libros = arrayListOf<Biblioteca_Libro>()

        init {
            // Bibliotecas

            BibliotecaBaseDeDatos.TablaBiblioteca!!.crearBiblioteca(1,"Biblioteca UCE","Quito","Av America","2345678")

            BibliotecaBaseDeDatos.TablaBiblioteca!!.crearBiblioteca(2,"Biblioteca EPN","Quito","Av Ladron de Guevara","2345677")

            BibliotecaBaseDeDatos.TablaBiblioteca!!.crearBiblioteca(3,"Biblioteca ESPE","Quito","Av Militar","2345670")


            // Libros
            BibliotecaBaseDeDatos.TablaBiblioteca!!.crearLibro(1,"Matematica","Calvache","Editorrial Norma","Ciencias Exactas","20")

            BibliotecaBaseDeDatos.TablaBiblioteca!!.crearLibro(2,"Fisica","Rojas","Editorrial Neptuno","Ciencias Exactas","15")

            BibliotecaBaseDeDatos.TablaBiblioteca!!.crearLibro(3,"Ingles","Richard","Editorial Cambridge","Idioma Extrangero","30")


            // Biliotecas y Libros
            arregloBibliotecas_Libros.add(
                Biblioteca_Libro(1, 1,2)
            )
            arregloBibliotecas_Libros.add(
                Biblioteca_Libro(2, 1, 3)
            )
            arregloBibliotecas_Libros.add(
                Biblioteca_Libro(3,2, 3)
            )
            arregloBibliotecas_Libros.add(
                Biblioteca_Libro(4,2,1)
            )
            arregloBibliotecas_Libros.add(
                Biblioteca_Libro(5,2,2)
            )
            arregloBibliotecas_Libros.add(
                Biblioteca_Libro(6,3,2)
            )

        }

    }

}