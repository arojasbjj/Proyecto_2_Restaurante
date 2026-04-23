package com.example.restauranteapp

import com.example.restauranteapp.models.CarritoItem
import com.example.restauranteapp.models.Platillo

object CarritoManager {

    private val items = mutableListOf<CarritoItem>()

    fun agregar(platillo: Platillo) {
        val existente = items.find { it.platilloId == platillo.id }
        if (existente != null) {
            existente.cantidad++
        } else {
            items.add(CarritoItem(platillo))
        }
    }

    fun obtenerItems(): List<CarritoItem> = items.toList()

    fun obtenerTotal(): Double = items.sumOf { it.precio * it.cantidad }

    fun getTotalFormateado(): String = "₡ %.2f".format(obtenerTotal())

    fun eliminar(platilloId: Int) = items.removeAll { it.platilloId == platilloId }

    fun limpiar() = items.clear()

    fun estaVacio(): Boolean = items.isEmpty()
}
