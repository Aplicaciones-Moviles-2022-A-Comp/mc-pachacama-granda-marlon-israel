package com.example.examenrefactorado

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class ESqliteHelperBiblioteca_Libro(
    contexto: Context?,
    ) : SQLiteOpenHelper(contexto, "moviles", null, 1) {

        override fun onCreate(db: SQLiteDatabase?) {
            val scriptSQLCrearTablaEntrenador:ArrayList<String> = arrayListOf(
                """
               CREATE TABLE LIBRO(
               id INTEGER PRIMARY KEY,
               nombreLibro VARCHAR(50),
               nombreAutor VARCHAR(50),
               yearEdicion VARCHAR(50),
               categoria VARCHAR(50),
               precio VARCHAR(50)
               );
             ""","""
               CREATE TABLE BIBLIOTECA(
               id INTEGER PRIMARY KEY,
               nombreBiblioteca VARCHAR(50),
               yearFundacion VARCHAR(50),
               ciudad VARCHAR(50),
               direccion VARCHAR(50),
               telefono VARCHAR(50)
               );
            """)
            for (i in scriptSQLCrearTablaEntrenador){
                db!!.execSQL(i)
            }
            Log.i("crear", "Bibliotecas")
        }

        override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

        }
        fun crearBiblioteca(id:Int,nombreBiblioteca:String, yearFundacion:String, ciudad:String,
                        direccion:String, telefono:String ): Boolean{
            val basedatosEscritura = writableDatabase
            val valoresAGuardar = ContentValues()
            valoresAGuardar.put("id",id)
            valoresAGuardar.put("nombreBiblioteca", nombreBiblioteca)
            valoresAGuardar.put("yearFundacion", yearFundacion)
            valoresAGuardar.put("ciudadn", ciudad)
            valoresAGuardar.put("direccion", direccion)
            valoresAGuardar.put("telefono", telefono)
            val resultadoGuardar = basedatosEscritura
                .insert(
                    "BIBLIOTECA",
                    null,
                    valoresAGuardar
                )
            basedatosEscritura.close()
            return if(resultadoGuardar.toInt() == -1) false else true

        }

        fun listarEquipos(): ArrayList<Biblioteca>{
            var lista = arrayListOf<Biblioteca>()
            var biblioteca: Biblioteca?
            val baseDatosLectura = readableDatabase
            val scriptConsultarUsuario = "SELECT * FROM BIBLIOTECA"
            val resultadoConsultaLectura = baseDatosLectura.rawQuery(
                scriptConsultarUsuario,
                null
            )
            if(resultadoConsultaLectura.moveToFirst()){
                do {
                    biblioteca=Biblioteca(0,"",0,"","",0)
                    biblioteca!!.idBiblioteca= resultadoConsultaLectura.getInt(0)
                    biblioteca.nombreBiblioteca= resultadoConsultaLectura.getString(1)
                    biblioteca.yearFundacion= resultadoConsultaLectura.getString(2).toInt()
                    biblioteca.city= resultadoConsultaLectura.getString(3)
                    biblioteca.direccion= resultadoConsultaLectura.getString(4)
                    biblioteca.telefono= resultadoConsultaLectura.getString(5).toInt()
                    lista.add(biblioteca)
                }while (resultadoConsultaLectura.moveToNext())
            }
            resultadoConsultaLectura.close()
            baseDatosLectura.close()
            return lista
        }
        fun actualizarBiblioteca(idA:Int,nombreBiblioteca:String, yearFundacion:String, ciudad:String,
    direccion:String, telefono:String ): Boolean{
            var lista= BibliotecaBaseDeDatos.TablaBiblioteca!!.listarEquipos()
            val id=lista[idA].idBiblioteca.toString()
            val conexionEscritura = writableDatabase
            val valoresAActualizar = ContentValues()
            valoresAActualizar.put("nombreBiblioteca", nombreBiblioteca)
            valoresAActualizar.put("yearFundacion", yearFundacion)
            valoresAActualizar.put("ciudad", ciudad)
            valoresAActualizar.put("direccion", direccion)
            valoresAActualizar.put("telefono", telefono)
            val resultadoActualizacion = conexionEscritura
                .update(
                    "BIBLIOTECA", // Nombre tabla
                    valoresAActualizar,  // Valores a actualizar
                    "id=?", // Clausula Where
                    arrayOf(
                        id.toString()
                    ) // Parametros clausula Where
                )
            conexionEscritura.close()
            return if (resultadoActualizacion == -1) false else true
        }
        fun eliminarBiblioteca(id:Int):Boolean{
            var lista= BibliotecaBaseDeDatos.TablaBiblioteca!!.listarEquipos()
            val idR=lista[id].idBiblioteca.toString()
            val conexion= writableDatabase
            val resultadoEliminacion=conexion
                .delete("BIBLIOTECA","id=?", arrayOf(idR))
            conexion.close()
            return if (resultadoEliminacion.toInt() == -1) false else true
        }
        fun crearLibro(id:Int, nombreLibro:String,nombreAutor:String, yearEdicion: String,
                         categoria: String, precio: String):Boolean{
            val basedatosEscritura = writableDatabase
            val valoresAGuardar = ContentValues()
            valoresAGuardar.put("id",id)
            valoresAGuardar.put("nombreLibro", nombreLibro)
            valoresAGuardar.put("nombreAutor", nombreAutor)
            valoresAGuardar.put("yearEdicion", yearEdicion)
            valoresAGuardar.put("categoria", categoria)
            valoresAGuardar.put("precio", precio)
            val resultadoGuardar = basedatosEscritura
                .insert(
                    "LIBRO",
                    null,
                    valoresAGuardar
                )
            basedatosEscritura.close()
            return if(resultadoGuardar.toInt() == -1) false else true

        }
        fun listarlIBRO(): ArrayList<Libro>{
            var lista = arrayListOf<Libro>()
            var libro: Libro?
            val baseDatosLectura = readableDatabase
            val scriptConsultarUsuario = "SELECT * FROM LIBRO"
            val resultadoConsultaLectura = baseDatosLectura.rawQuery(
                scriptConsultarUsuario,
                null
            )
            if(resultadoConsultaLectura.moveToFirst()){
                do {
                    libro= Libro(0,"","","",0,"",0)
                    libro!!.idLibro= resultadoConsultaLectura.getInt(0)
                    libro.nombreLibro= resultadoConsultaLectura.getString(1)
                    libro.nombreAutor= resultadoConsultaLectura.getString(2)
                    libro.yearEdicion= resultadoConsultaLectura.getString(3).toInt()
                    libro.categoria= resultadoConsultaLectura.getString(4)
                    libro.precio= resultadoConsultaLectura.getString(5).toInt()
                    lista.add(libro)
                }while (resultadoConsultaLectura.moveToNext())
            }
            resultadoConsultaLectura.close()
            baseDatosLectura.close()
            return lista
        }

        fun actualizarLibro(id:Int, nombreLibro:String, nombreAutor:String, yearEdicion: String,
                              categoria: String, precio: String ):Boolean{

            val conexionEscritura = writableDatabase
            val valoresAActualizar = ContentValues()
            valoresAActualizar.put("nombreLibro", nombreLibro)
            valoresAActualizar.put("nombreAutor", nombreAutor)
            valoresAActualizar.put("yearEdicion", yearEdicion)
            valoresAActualizar.put("categoria", categoria)
            valoresAActualizar.put("precio", precio)
            val resultadoActualizacion = conexionEscritura
                .update(
                    "LIBRO", // Nombre tabla
                    valoresAActualizar,  // Valores a actualizar
                    "id=?", // Clausula Where
                    arrayOf(
                        id.toString()
                    ) // Parametros clausula Where
                )
            conexionEscritura.close()
            return if (resultadoActualizacion == -1) false else true
        }
        fun eliminarLibro(id:Int):Boolean{
            val conexion= writableDatabase
            val resultadoEliminacion=conexion
                .delete("LIBRO","id=?", arrayOf(id.toString()))
            conexion.close()
            return if (resultadoEliminacion.toInt() == -1) false else true
        }



    }