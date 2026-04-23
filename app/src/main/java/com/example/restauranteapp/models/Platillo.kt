package com.example.restauranteapp.models

import java.io.Serializable

// SOLID - (S) Single Responsibility: solo representa los datos de un platillo
// SOLID - (L) Liskov Substitution: sustituye a BaseItem sin romper el comportamiento esperado
// Campos mapeados al JSON del API ASP.NET: platilloId, nombre, descripcion, precio, imagenUrl, restauranteId
class Platillo(
    id: Int,
    nombre: String,
    descripcion: String,
    precio: Double,
    val imagenUrl: String?,
    val restauranteId: Int
) : BaseItem(id, nombre, descripcion, precio), Serializable
