package com.example.restauranteapp

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {

    lateinit var txtCorreo: EditText
    lateinit var txtPassword: EditText
    lateinit var txtResultado: TextView
    lateinit var btnIngresar: Button

    lateinit var btnIrRegistro: Button

    lateinit var preferencias: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        preferencias = getSharedPreferences("sesion", MODE_PRIVATE)

        txtCorreo = findViewById(R.id.txtCorreo)
        txtPassword = findViewById(R.id.txtPassword)
        txtResultado = findViewById(R.id.txtResultado)
        btnIngresar = findViewById(R.id.btnIngresar)

        btnIngresar.setOnClickListener {
            loginUsuario()
        }

        btnIrRegistro = findViewById(R.id.btnIrRegistro)

        btnIrRegistro.setOnClickListener {
            irARegistro()
        }
    }

    private fun loginUsuario() {
        val usuario = txtCorreo.text.toString()
        val password = txtPassword.text.toString()

        if (usuario == "admin" && password == "admin") {
            val editor = preferencias.edit()
            editor.putString("usuario", usuario)
            editor.putBoolean("logueado", true)
            editor.apply()

            txtResultado.text = "Acceso permitido"

            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            txtResultado.text = "Credenciales inválidas (admin/admin)"
        }
    }

    private fun irARegistro() {
        startActivity(Intent(this, RegistroActivity::class.java))
    }
}