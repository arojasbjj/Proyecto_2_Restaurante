package com.example.restauranteapp.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "RestauranteDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE usuario(
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                usuario TEXT,
                password TEXT,
                perfil TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)

        // Insertar usuario admin/admin/Administrador por defecto
        val insertAdmin = """
            INSERT INTO usuario (usuario, password, perfil)
            VALUES ('admin', 'admin', 'Administrador')
        """.trimIndent()
        db.execSQL(insertAdmin)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS usuario")
        onCreate(db)
    }
}