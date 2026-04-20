package com.example.restauranteapp.database

import android.content.ContentValues
import android.content.Context

class UsuarioDAO(context: Context) {

    private val dbHelper = DatabaseHelper(context)

    fun insertar(usuario: String, password: String, perfil: String) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("usuario", usuario)
            put("password", password)
            put("perfil", perfil)
        }
        db.insert("usuario", null, values)
        db.close()
    }

    fun validarAdmin(usuario: String, password: String): Boolean {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM usuario WHERE usuario = ? AND password = ? AND perfil = ?",
            arrayOf(usuario, password, "Administrador")
        )
        val existe = cursor.count > 0
        cursor.close()
        db.close()
        return existe
    }
}