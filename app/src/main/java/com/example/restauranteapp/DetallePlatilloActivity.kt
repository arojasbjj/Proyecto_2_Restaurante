package com.example.restauranteapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.restauranteapp.models.Platillo

class DetallePlatilloActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_PLATILLO = "platillo"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_platillo)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        @Suppress("DEPRECATION")
        val platillo = intent.getSerializableExtra(EXTRA_PLATILLO) as? Platillo
        platillo?.let { mostrarDetalle(it) }
    }

    private fun mostrarDetalle(platillo: Platillo) {
        supportActionBar?.title = platillo.getNombre()
        findViewById<TextView>(R.id.tvDetalleNombre).text        = platillo.getNombre()
        findViewById<TextView>(R.id.tvDetalleRestaurante).text  = "Restaurante #${platillo.restauranteId}"
        findViewById<TextView>(R.id.tvDetalleDescripcion).text  = platillo.getDescripcion()
        findViewById<TextView>(R.id.tvDetallePrecio).text       = platillo.getPrecioFormateado()

        findViewById<Button>(R.id.btnAgregarCarrito).setOnClickListener {
            CarritoManager.agregar(platillo)
            Toast.makeText(this, "${platillo.getNombre()} agregado al carrito", Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btnVerCarrito).setOnClickListener {
            startActivity(Intent(this, CarritoActivity::class.java))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
