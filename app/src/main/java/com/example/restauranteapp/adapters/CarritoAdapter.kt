package com.example.restauranteapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restauranteapp.R
import com.example.restauranteapp.models.CarritoItem

class CarritoAdapter(
    private val items: MutableList<CarritoItem>,
    private val onEliminar: (CarritoItem) -> Unit
) : RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    inner class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView      = itemView.findViewById(R.id.tvCarritoNombre)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvCarritoDescripcion)
        val tvPrecio: TextView      = itemView.findViewById(R.id.tvCarritoPrecio)
        val tvCantidad: TextView    = itemView.findViewById(R.id.tvCarritoCantidad)
        val btnEliminar: ImageButton= itemView.findViewById(R.id.btnEliminarItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carrito, parent, false)
        return CarritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val item = items[position]
        holder.tvNombre.text      = item.nombre
        holder.tvDescripcion.text = item.descripcion
        holder.tvPrecio.text      = item.getSubtotalFormateado()
        holder.tvCantidad.text    = "Cantidad: ${item.cantidad}"
        holder.btnEliminar.setOnClickListener { onEliminar(item) }
    }

    override fun getItemCount(): Int = items.size
}
