package com.example.examen_01

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperBiblioteca(
    contexto: Context?,
    ) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
    ) {
        override fun onCreate(db: SQLiteDatabase?) {
            val scriptSQLCrearTablaBiblioteca =
                """
               CREATE TABLE BIBLIOTECA(
               id INTEGER PRIMARY KEY AUTOINCREMENT,
               nombreBiblioteca VARCHAR(50),
               yearFoundation VARCHAR(50),
               ciudad VARCHAR(50),
               direccion VARCHAR(50),
               telefono VARCHAR(50),
               ) 
            """.trimIndent()
            db?.execSQL(scriptSQLCrearTablaBiblioteca)
        }

        override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

        fun crearBibliotecaFormulario(
            nombreBiblioteca: String,
            yearFoundation: String,
            ciudad: String,
            direccion: String,
            telefono: String,
        ): Boolean{
            val baseDatosLectura = readableDatabase
            val valoresAGuardar = ContentValues()
            valoresAGuardar.put("nombreBiblioteca", nombreBiblioteca)
            valoresAGuardar.put("yaerFoundation", yearFoundation)
            valoresAGuardar.put("ciudad", ciudad)
            valoresAGuardar.put("direccion", direccion)
            valoresAGuardar.put("telefono", telefono)
            var resultadoEscritura: Long = baseDatosLectura
                .insert(
                    "Biblioteca",
                    null,
                    valoresAGuardar
                )
            baseDatosLectura.close()
            return if(resultadoEscritura.toInt()==-1) false else true
        }


        fun consultarBibliotecaPorId(id: Int): BBiblioteca{
            val baseDatosLectura = readableDatabase
            val scriptConsultarEntrenador = "SELECT * FROM Entrenador WHERE ID=${id}"
            val resultadoConsultaLectura = baseDatosLectura.rawQuery(
                scriptConsultarEntrenador,
                null
            )
            val existeBiblioteca = resultadoConsultaLectura.moveToFirst()
            val BBibliotecaEncontrada = BBiblioteca(0,"","","","","")
            if (existeBiblioteca){
                do{
                    val id = resultadoConsultaLectura.getInt(0) // columna indice 0 -> ID
                    val nombreBiblioteca = resultadoConsultaLectura.getString(1) // columna indice 1 -> nombre
                    val yearFoundation = resultadoConsultaLectura.getString(2) // columna indice 2 - > descipcion
                    val ciudad = resultadoConsultaLectura.getString(3)
                    val direccion = resultadoConsultaLectura.getString(4)
                    val telefono = resultadoConsultaLectura.getString(5)
                    if(id!=null){
                        BBibliotecaEncontrada.id = id
                        BBibliotecaEncontrada.nombreBiblioteca = nombreBiblioteca
                        BBibliotecaEncontrada.yearFundacion = yearFoundation
                        BBibliotecaEncontrada.ciudad = ciudad
                        BBibliotecaEncontrada.direccion = direccion
                        BBibliotecaEncontrada.telefono = telefono
                    }
                }while (resultadoConsultaLectura.moveToNext())
            }
            resultadoConsultaLectura.close()
            baseDatosLectura.close()
            return BBibliotecaEncontrada
        }

        fun eliminarBibliotecaFormulario(id: Int): Boolean{
            val conexionEscritura = writableDatabase
            val resultadoEliminacion = conexionEscritura
                .delete(
                    "BIBLIOTECA",
                    "id=?",
                    arrayOf(
                        id.toString()
                    )
                )
            conexionEscritura.close()
            return if (resultadoEliminacion.toInt() == -1) false else true
        }

        fun actualizarBibliotecaFormulario(
            nombreBiblioteca: String,
            yearFoundation: String,
            ciudad: String,
            direccion: String,
            telefono: String,
            idActualizar: Int
        ): Boolean{
            val conexionEscritura = writableDatabase
            val valoresAActualizar = ContentValues()
            valoresAActualizar.put("nombreBiblioteca", nombreBiblioteca)
            valoresAActualizar.put("yearFoundation", yearFoundation)
            valoresAActualizar.put("ciudad", ciudad)
            valoresAActualizar.put("direccion", direccion)
            valoresAActualizar.put("telefono", telefono)
            val resultadoAtualizacion = conexionEscritura
                .update(
                    "BIBLIOTECA",
                    valoresAActualizar,
                    "id=?",
                    arrayOf(
                        idActualizar.toString()
                    )
                )
            conexionEscritura.close()
            return if(resultadoAtualizacion == -1) false else true
        }


    }