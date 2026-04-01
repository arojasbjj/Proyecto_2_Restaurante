package com.example.restauranteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.content.Intent
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {

    lateinit var txtResultado: TextView
    lateinit var btnCargar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, RegistroActivity::class.java))

        setContentView(R.layout.activity_main)

        txtResultado = findViewById(R.id.txtResultado)
        btnCargar = findViewById(R.id.btnCargar)

        btnCargar.setOnClickListener {
            obtenerPlatillos()
        }
    }

    private fun obtenerPlatillos() {


        val url = "http://10.0.2.2:5288/api/PlatillosApi"

        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                txtResultado.text = response
            },
            { error ->
                txtResultado.text =
                    "Error API:\n${error.networkResponse?.statusCode}\n${error.message}"
            }
        )

        queue.add(request)
    }
}