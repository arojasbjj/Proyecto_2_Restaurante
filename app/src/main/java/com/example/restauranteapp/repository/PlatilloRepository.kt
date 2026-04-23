package com.example.restauranteapp.repository

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.restauranteapp.interfaces.IProductoRepository
import com.example.restauranteapp.models.Platillo

// SOLID - (S) Single Responsibility: única responsabilidad es obtener datos de platillos desde el API
// SOLID - (D) Dependency Inversion: implementa IProductoRepository (abstracción)
// Usa Volley igual que RegistroActivity para mantener consistencia con el resto del proyecto
class PlatilloRepository(private val context: Context) : IProductoRepository<Platillo> {

    private val BASE_URL = "http://10.0.2.2:5288/api/PlatillosApi"

    override fun obtenerTodos(onSuccess: (List<Platillo>) -> Unit, onError: (String) -> Unit) {
        val request = JsonArrayRequest(
            Request.Method.GET, BASE_URL, null,
            { jsonArray ->
                val lista = mutableListOf<Platillo>()
                for (i in 0 until jsonArray.length()) {
                    val obj = jsonArray.getJSONObject(i)
                    lista.add(
                        Platillo(
                            id           = obj.getInt("platilloId"),
                            nombre       = obj.getString("nombre"),
                            descripcion  = obj.getString("descripcion"),
                            precio       = obj.getDouble("precio"),
                            imagenUrl    = obj.optString("imagenUrl", null),
                            restauranteId= obj.getInt("restauranteId")
                        )
                    )
                }
                onSuccess(lista)
            },
            { error -> onError(error.message ?: "Error al conectar con el servidor") }
        )
        Volley.newRequestQueue(context).add(request)
    }

    override fun obtenerPorId(id: Int, onSuccess: (Platillo?) -> Unit, onError: (String) -> Unit) {
        val url = "$BASE_URL/$id"
        val request = JsonArrayRequest(
            Request.Method.GET, url, null,
            { jsonArray ->
                if (jsonArray.length() > 0) {
                    val obj = jsonArray.getJSONObject(0)
                    onSuccess(
                        Platillo(
                            id           = obj.getInt("platilloId"),
                            nombre       = obj.getString("nombre"),
                            descripcion  = obj.getString("descripcion"),
                            precio       = obj.getDouble("precio"),
                            imagenUrl    = obj.optString("imagenUrl", null),
                            restauranteId= obj.getInt("restauranteId")
                        )
                    )
                } else {
                    onSuccess(null)
                }
            },
            { error -> onError(error.message ?: "Error al conectar con el servidor") }
        )
        Volley.newRequestQueue(context).add(request)
    }
}
