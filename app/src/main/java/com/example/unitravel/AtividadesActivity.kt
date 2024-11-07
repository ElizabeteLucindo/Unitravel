package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class AtividadesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atividades)

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