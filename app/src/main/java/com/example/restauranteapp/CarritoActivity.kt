package com.example.restauranteapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restauranteapp.adapters.CarritoAdapter

class CarritoActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var tvTotal: TextView
    private lateinit var tvVacio: TextView
    private lateinit var btnCompletarPedido: Button
    private lateinit var adapter: CarritoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        supportActionBar?.title = "Mi Carrito"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recyclerView       = findViewById(R.id.rvCarrito)
        tvTotal            = findViewById(R.id.tvTotalCarrito)
        tvVacio            = findViewById(R.id.tvCarritoVacio)
        btnCompletarPedido = findViewById(R.id.btnCompletarPedido)

        recyclerView.layoutManager = LinearLayoutManager(this)

        cargarCarrito()

        btnCompletarPedido.setOnClickListener {
            if (CarritoManager.estaVacio()) {
                Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show()
            } else {
                CarritoManager.limpiar()
                Toast.makeText(this, "Pedido completado exitosamente", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }

    private fun cargarCarrito() {
        val items = CarritoManager.obtenerItems().toMutableList()

        if (items.isEmpty()) {
            tvVacio.visibility        = View.VISIBLE
            recyclerView.visibility   = View.GONE
            btnCompletarPedido.isEnabled = false
            tvTotal.text              = "Total: ₡ 0.00"
            return
        }

        tvVacio.visibility        = View.GONE
        recyclerView.visibility   = View.VISIBLE
        btnCompletarPedido.isEnabled = true
        tvTotal.text              = "Total: ${CarritoManager.getTotalFormateado()}"

        adapter = CarritoAdapter(items) { item ->
            CarritoManager.eliminar(item.platilloId)
            cargarCarrito()
        }
        recyclerView.adapter = adapter
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
