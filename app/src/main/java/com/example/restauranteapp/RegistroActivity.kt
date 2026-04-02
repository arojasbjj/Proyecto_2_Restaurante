package com.example.restauranteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class RegistroActivity : AppCompatActivity() {

    lateinit var txtNombre: EditText
    lateinit var txtCorreo: EditText
    lateinit var txtResultado: TextView
    lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        txtNombre = findViewById(R.id.txtNombre)
        txtCorreo = findViewById(R.id.txtCorreo)
        txtResultado = findViewById(R.id.txtResultado)
        btnRegistrar = findViewById(R.id.btnRegistrar)

        btnRegistrar.setOnClickListener {
            registrarUsuario()
        }
    }

    private fun registrarUsuario() {

        val url = "http://10.0.2.2:5288/api/AuthApi/register"

        val json = JSONObject()
        json.put("nombre", txtNombre.text.toString())
        json.put("correo", txtCorreo.text.toString())
        json.put("password", "12345")
        json.put("captcha", "5")

        val request = JsonObjectRequest(
            Request.Method.POST,
            url,
            json,

            { response ->
                txtResultado.text = response.toString()
            },

            { error ->
                txtResultado.text =
                    "Error API: ${error.networkResponse?.statusCode}"
            }
        )

        Volley.newRequestQueue(this).add(request)
    }
}