package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HoteisActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hoteis)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewHoteis)

        // Lista de Hoteis para exibir
        val hoteis = listOf(
            Hotel(
                "Ibis Budget",
                "Endereço: X",
                "Diária Mínima: 0 R$",
                "Tópicos : Segurança, acessibilidade, sustentabilidade.",
                R.drawable.hotel_ibis
            ),
            Hotel(
                "Itajaí Tur Hotel",
                "Endereço: X",
                "Diária Mínima: 0 R$",
                "Tópicos : X.",
                R.drawable.hotel_tur
            ),
            Hotel(
                "Hotel Marjaí",
                "Endereço: X",
                "Diária Mínima: 0 R$",
                "Tópicos : X",
                R.drawable.hotel_marjai
            )
        )
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = HotelAdapter(hoteis)
    }
}