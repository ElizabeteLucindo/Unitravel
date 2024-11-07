package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class TransporteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transporte)

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