package com.example.restauranteapp.interfaces

// SOLID - (I) Interface Segregation: contrato mínimo para mostrar un producto
interface IProductoDisplayable {
    fun getNombre(): String
    fun getDescripcion(): String
    fun getPrecio(): Double
}
