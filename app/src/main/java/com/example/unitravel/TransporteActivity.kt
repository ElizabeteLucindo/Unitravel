package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.drawerlayout.widget.DrawerLayout
import android.widget.ImageView


class TransporteActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transporte)

        // Configura o DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout_transporte)
        val menuIcon: ImageView = findViewById(R.id.btnMenu)
        setupDrawer(drawerLayout, menuIcon)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewTransportes)

        // Lista de locais para exibir
        val transportes = listOf(
            Transporte(
                "Ônibus",
                "Pontos: ",
                "Preço: 0 R$",
                "Telefone: ",
                "Horário de Funcionamento: ",
                R.drawable.onibus
            ),
            Transporte(
                "Avião",
                "Endereço: X",
                "Preço: 123 R$",
                "Telefone: 123",
                "Horário de Funcionamento: ",
                R.drawable.aviao2
            ),
            Transporte(
                "Ferry Boat",
                "Endereço: XX",
                "Preço: 0 R$",
                "Telefone: ",
                "Horário de Funcionamento: ",
                R.drawable.morro_da_cruz
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TransporteAdapter(transportes)
    }
}