package com.example.restauranteapp.models

import com.example.restauranteapp.interfaces.IProductoDisplayable
import java.io.Serializable

// Clase abstracta: implementa el contrato común para todos los ítems del menú
// Los campos coinciden con los que devuelve el API de ASP.NET
// SOLID - (L) Liskov Substitution: cualquier subclase puede usarse donde se espera BaseItem
// SOLID - (O) Open/Closed: abierta para extensión (nuevos tipos de ítems), cerrada para modificación
abstract class BaseItem(
    val id: Int,
    private val nombre: String,
    private val descripcion: String,
    private val precio: Double
) : IProductoDisplayable, Serializable {

    override fun getNombre(): String = nombre
    override fun getDescripcion(): String = descripcion
    override fun getPrecio(): Double = precio

    fun getPrecioFormateado(): String = "₡ %.2f".format(precio)
}
