package com.example.restauranteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class RegistroActivity : AppCompatActivity() {

    lateinit var txtNombre: EditText
    lateinit var txtCorreo: EditText
    lateinit var btnRegistrar: Button
    lateinit var txtRespuesta: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_registro)

        txtNombre = findViewById(R.id.txtNombre)
        txtCorreo = findViewById(R.id.txtCorreo)
        btnRegistrar = findViewById(R.id.btnRegistrar)
        txtRespuesta = findViewById(R.id.txtRespuesta)

        btnRegistrar.setOnClickListener {
            txtRespuesta.text = "Registro funcionando ✅"
        }
    }
}