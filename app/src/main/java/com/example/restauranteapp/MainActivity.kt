package com.example.restauranteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottomMenu)

        // Fragment inicial
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameContainer, InicioFragment())
            .commit()

        bottomMenu.setOnItemSelectedListener {

            when(it.itemId){

                R.id.menu_inicio -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameContainer, InicioFragment())
                        .commit()
                }

                R.id.menu_platillos -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameContainer, PlatillosFragment())
                        .commit()
                }

                R.id.menu_perfil -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameContainer, PerfilFragment())
                        .commit()
                }
            }

            true
        }
    }
}