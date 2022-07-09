package com.example.movcompmipg2022a

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ESqliteHelperEntrenador(
    contexto: Context?,
) : SQLiteOpenHelper(
    contexto,
    "moviles",
    null,
    1
) {
    override fun onCreate(db: SQLiteDatabase?) {
        val scriptSQLCrearTablaEntrenador =
            """
               CREATE TABLE ENTRENADOR(
               id INTEGER PRIMARY KEY AUTOINCREMENT,
               nombre VARCHAR(50),
               descripcion VARCHAR(50)
               ) 
            """.trimIndent()
        db?.execSQL(scriptSQLCrearTablaEntrenador)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    fun crearEntrenadorFormulario(
        nombre: String,
        descripcion: String
    ): Boolean{
        val baseDatosLectura = readableDatabase
        val valoresAGuardar = ContentValues()
        valoresAGuardar.put("nombre", nombre)
        valoresAGuardar.put("descipcion", descripcion)
        var resultadoEscritura: Long = baseDatosLectura
            .insert(
                "Entrenador",
                null,
                valoresAGuardar
            )
        baseDatosLectura.close()
        return if(resultadoEscritura.toInt()==-1) false else true
    }


    fun consultarEntrenadorPorId(id: Int): BEntrenador{
        val baseDatosLectura = readableDatabase
        val scriptConsultarEntrenador = "SELECT * FROM Entrenador WHERE ID=${id}"
        val resultadoConsultaLectura = baseDatosLectura.rawQuery(
            scriptConsultarEntrenador,
            null
        )
        val existeEntrenador = resultadoConsultaLectura.moveToFirst()
        val EntrenadorEncontrado = BEntrenador(0,"","")
        if (existeEntrenador){
            do{
                val id = resultadoConsultaLectura.getInt(0) // columna indice 0 -> ID
                val nombre = resultadoConsultaLectura.getString(1) // columna indice 1 -> nombre
                val desripcion = resultadoConsultaLectura.getString(2) // columna indice 2 - > descipcion

                if(id!=null){
                    EntrenadorEncontrado.id = id
                    EntrenadorEncontrado.nombre = nombre
                    EntrenadorEncontrado.descripcion = desripcion
                }
            }while (resultadoConsultaLectura.moveToNext())
        }
        resultadoConsultaLectura.close()
        baseDatosLectura.close()
        return EntrenadorEncontrado
    }

    fun eliminarEntrenadorFormulario(id: Int): Boolean{
        val conexionEscritura = writableDatabase
        val resultadoEliminacion = conexionEscritura
            .delete(
                "ENTRENADOR",
                "id=?",
                arrayOf(
                    id.toString()
                )
            )
        conexionEscritura.close()
        return if (resultadoEliminacion.toInt() == -1) false else true
    }

    fun actualizarEntrenadorFormulario(
        nombre: String,
        descripcion: String,
        idActualizar: Int
    ): Boolean{
        val conexionEscritura = writableDatabase
        val valoresAActualizar = ContentValues()
        valoresAActualizar.put("nombre", nombre)
        valoresAActualizar.put("descipcion", descripcion)
        val resultadoAtualizacion = conexionEscritura
            .update(
                "ENTRENADOR",
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