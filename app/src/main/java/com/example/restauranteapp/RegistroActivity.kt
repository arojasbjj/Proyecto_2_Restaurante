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
import com.example.restauranteapp.database.UsuarioDAO

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

        val nombre = txtNombre.text.toString()
        val correo = txtCorreo.text.toString()


        if (nombre.isEmpty() || correo.isEmpty()) {
            txtResultado.text = "Complete todos los campos"
            return
        }

        val url = "http://10.0.2.2:5288/api/AuthApi/register"

        val json = JSONObject().apply {
            put("nombre", nombre)
            put("correo", correo)
            put("password", "12345")
            put("captcha", "5")
        }

        val request = object : JsonObjectRequest(
            Request.Method.POST,
            url,
            json,

            { response ->


                txtResultado.text = "Registro exitoso ✅"


                val dao = UsuarioDAO(this)
                dao.insertar(nombre, correo)
            },

            { error ->

                val status = error.networkResponse?.statusCode

                txtResultado.text = "Error API: $status"
            }

        ) {

            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-Type"] = "application/json"
                return headers
            }
        }

        Volley.newRequestQueue(this).add(request)
    }
}