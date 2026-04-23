package com.example.restauranteapp

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class InicioFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_inicio, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferencias = requireContext().getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val usuario = preferencias.getString("usuario", "Usuario") ?: "Usuario"

        view.findViewById<TextView>(R.id.tvBienvenida).text = "Bienvenido,"
        view.findViewById<TextView>(R.id.tvNombreUsuario).text = usuario
    }
}
