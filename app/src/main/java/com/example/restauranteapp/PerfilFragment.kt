package com.example.restauranteapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_perfil, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferencias = requireContext().getSharedPreferences("sesion", Context.MODE_PRIVATE)
        val usuario = preferencias.getString("usuario", "Usuario") ?: "Usuario"

        // Mostrar inicial del usuario en el avatar
        view.findViewById<TextView>(R.id.tvAvatar).text = usuario.first().uppercaseChar().toString()
        view.findViewById<TextView>(R.id.tvNombrePerfil).text = usuario
        view.findViewById<TextView>(R.id.tvCorreoPerfil).text = usuario

        // Cerrar sesión
        view.findViewById<Button>(R.id.btnCerrarSesion).setOnClickListener {
            preferencias.edit().clear().apply()
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
    }
}
