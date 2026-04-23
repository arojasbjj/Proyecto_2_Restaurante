package com.example.restauranteapp.interfaces

// SOLID - (I) Interface Segregation: contrato independiente solo para acceso a datos
// SOLID - (D) Dependency Inversion: los módulos de alto nivel dependen de esta abstracción
// Usa callbacks porque las llamadas al API son asíncronas (Volley)
interface IProductoRepository<T : IProductoDisplayable> {
    fun obtenerTodos(onSuccess: (List<T>) -> Unit, onError: (String) -> Unit)
    fun obtenerPorId(id: Int, onSuccess: (T?) -> Unit, onError: (String) -> Unit)
}
