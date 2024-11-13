package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.drawerlayout.widget.DrawerLayout
import android.widget.ImageView


class AtividadesActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atividades)

        // Configura o DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)
        val menuIcon: ImageView = findViewById(R.id.btnMenu)
        setupDrawer(drawerLayout, menuIcon)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)

        // Lista de locais para exibir
        val places = listOf(
            Place(
                "Bico do Papagaio",
                "Endereço: 1733, R. Dep. Francisco Evaristo Canziani, 1531 - Cabeçudas",
                "Custo: 0 R$",
                "Tópicos : Fotos, mirante, família, passeio.",
                R.drawable.bico_do_papagaio
            ),
            Place(
                "Praia do Atalaia",
                "Endereço: R. Dep. Francisco Evaristo Canziani, 1878 - Cabeçudas",
                "Custo: 0 R$",
                "Tópicos : Fotos, praia, alimentação, surfe, passeio.",
                R.drawable.praia_atalaia
            ),
            Place(
                "Morro da Cruz",
                "Endereço: R. Antônio Menezes Vasconcelos Drumond, 271-355 - Fazenda",
                "Custo: 0 R$",
                "Tópicos : Fotos, mirante, família, passeio.",
                R.drawable.morro_da_cruz
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PlaceAdapter(places)

    }
}