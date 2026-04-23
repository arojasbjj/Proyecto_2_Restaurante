package com.example.restauranteapp.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

// Clase abstracta: centraliza el ciclo de vida común de todos los fragments
// SOLID - (O) Open/Closed: abierta para extensión mediante subclases, cerrada para modificación
// SOLID - (S) Single Responsibility: solo gestiona el ciclo de vida base del fragment
abstract class BaseFragment : Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inicializarVistas(view)
        configurarObservadores()
    }

    abstract fun inicializarVistas(view: View)

    open fun configurarObservadores() {}
}
