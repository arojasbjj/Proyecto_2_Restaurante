package com.example.restauranteapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restauranteapp.adapters.PlatillosAdapter
import com.example.restauranteapp.base.BaseFragment
import com.example.restauranteapp.interfaces.IProductoRepository
import com.example.restauranteapp.models.Platillo
import com.example.restauranteapp.repository.PlatilloRepository

// SOLID - (D) Dependency Inversion: depende de IProductoRepository (abstracción), no de PlatilloRepository (concreción)
class PlatillosFragment : BaseFragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvError: TextView

    private val repositorio: IProductoRepository<Platillo> by lazy {
        PlatilloRepository(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_platillos, container, false)

    override fun inicializarVistas(view: View) {
        recyclerView = view.findViewById(R.id.rvPlatillos)
        progressBar  = view.findViewById(R.id.progressBar)
        tvError      = view.findViewById(R.id.tvError)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        cargarPlatillos()
    }

    private fun cargarPlatillos() {
        progressBar.visibility  = View.VISIBLE
        tvError.visibility      = View.GONE
        recyclerView.visibility = View.GONE

        repositorio.obtenerTodos(
            onSuccess = { platillos ->
                progressBar.visibility  = View.GONE
                recyclerView.visibility = View.VISIBLE
                recyclerView.adapter = PlatillosAdapter(platillos) { platillo ->
                    val intent = Intent(requireContext(), DetallePlatilloActivity::class.java)
                    intent.putExtra(DetallePlatilloActivity.EXTRA_PLATILLO, platillo)
                    startActivity(intent)
                }
            },
            onError = { mensaje ->
                progressBar.visibility = View.GONE
                tvError.visibility     = View.VISIBLE
                tvError.text           = "No se pudo cargar el menú: $mensaje"
            }
        )
    }
}
