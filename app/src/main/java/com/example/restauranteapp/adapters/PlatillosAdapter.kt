package com.example.restauranteapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.restauranteapp.R
import com.example.restauranteapp.models.Platillo

// SOLID - (S) Single Responsibility: única responsabilidad es mostrar la lista de platillos en RecyclerView
class PlatillosAdapter(
    private val platillos: List<Platillo>,
    private val onItemClick: (Platillo) -> Unit
) : RecyclerView.Adapter<PlatillosAdapter.PlatilloViewHolder>() {

    inner class PlatilloViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView    = itemView.findViewById(R.id.tvNombrePlatillo)
        val tvDescripcion: TextView = itemView.findViewById(R.id.tvDescripcionPlatillo)
        val tvPrecio: TextView    = itemView.findViewById(R.id.tvPrecioPlatillo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatilloViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_platillo, parent, false)
        return PlatilloViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlatilloViewHolder, position: Int) {
        val platillo = platillos[position]
        holder.tvNombre.text      = platillo.getNombre()
        holder.tvDescripcion.text = platillo.getDescripcion()
        holder.tvPrecio.text      = platillo.getPrecioFormateado()
        holder.itemView.setOnClickListener { onItemClick(platillo) }
    }

    override fun getItemCount(): Int = platillos.size
}
