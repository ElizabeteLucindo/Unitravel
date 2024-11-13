package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.drawerlayout.widget.DrawerLayout
import android.widget.ImageView


class AlimentacaoActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alimentacao)

        // Configura o DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout_alimentacao)
        val menuIcon: ImageView = findViewById(R.id.btnMenu)
        setupDrawer(drawerLayout, menuIcon)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewRestaurants)

        // Lista de restaurantes para exibir
        val restaurants = listOf(
            Restaurant(
                "Brava Sushi - Praia Brava",
                "Av. José Medeiros Vieira 2400",
                "japonesa",
                "(47) 3366-1851",
                "19:00 - 00:00",
                R.drawable.sushi
            ),
            Restaurant(
                "Santo Grill",
                "Av. Min. Victor Konder, 1212 - Fazenda",
                "churrasco",
                "(47) 3246-1076",
                "11:30 - 16:00",
                R.drawable.churrasco
            ),
            Restaurant(
            "Pizzaria e Restaurante Vovó Zena",
            "R. Estefano José Vanolli, 1427 - São Vicente",
            "Pizzaria",
            "(47) 3248-4394",
            "11:00 - 14:00 e 18:00 - 00:00",
            R.drawable.pizza
        )
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RestaurantAdapter(restaurants)
    }
}