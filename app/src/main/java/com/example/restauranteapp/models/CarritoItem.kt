package com.example.restauranteapp.models

data class CarritoItem(
    val platilloId: Int,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    var cantidad: Int = 1
) {
    constructor(platillo: Platillo) : this(
        platilloId  = platillo.id,
        nombre      = platillo.getNombre(),
        descripcion = platillo.getDescripcion(),
        precio      = platillo.getPrecio()
    )

    fun getSubtotalFormateado(): String = "₡ %.2f".format(precio * cantidad)
}
