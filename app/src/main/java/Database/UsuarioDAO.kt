package com.example.restauranteapp.database

import android.content.ContentValues
import android.content.Context

class UsuarioDAO(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun insertar(nombre: String, correo: String) {

        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {
            put("nombre", nombre)
            put("correo", correo)
        }

        db.insert("usuario", null, values)
        db.close()
    }
}