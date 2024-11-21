package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.drawerlayout.widget.DrawerLayout
import android.widget.ImageView
import android.widget.TextView
import setupDrawer
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore


class HoteisActivity : AppCompatActivity() {

    private lateinit var textViewCabecalho: TextView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: HotelAdapter
    private lateinit var recyclerView: RecyclerView
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hoteis)

        recyclerView = findViewById(R.id.recyclerViewHoteis)
        textViewCabecalho = findViewById(R.id.textViewCabecalho)

        // Configura o DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout_hoteis)
        val menuIcon: ImageView = findViewById(R.id.btnMenu)
        setupDrawer(drawerLayout, menuIcon, this)

        // Configura o RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = HotelAdapter(mutableListOf()) // Inicia com uma lista vazia
        recyclerView.adapter = adapter

        val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        val cidade = sharedPreferences.getString("cidade", "Cidade") ?: ""
        val estado = sharedPreferences.getString("estado", "Estado") ?: ""

        // Atualiza o texto do TextView
        textViewCabecalho.text = "$cidade-$estado"

        // Carrega os dados do Firestore
        carregarDestinos(cidade, estado)
    }

    private fun carregarDestinos(cidade: String, estado: String){

        db.collection("destinos")
            .whereEqualTo("categoria", "Hotéis")
            .whereEqualTo("cidade", cidade)
            .whereEqualTo("estado", estado)
            .get()
            .addOnSuccessListener { documents ->
                val hoteisList = mutableListOf<Hotel>()

                if (documents.isEmpty) {
                    Toast.makeText(this, "Nenhum hotel encontrado.", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    for (document in documents) {
                        val title = document.getString("nome") ?: "Nome não disponível"
                        val address = document.getString("localizacao") ?: "Endereço não disponível"
                        val cost = document.getString("custo") ?: "Custo não informado"
                        val avaliacao = document.getString("avaliacao") ?: "Avaliação não informado"
                        val servico = document.getString("servicos") ?: "Serviço não informado"
                        val phone = document.getString("contato") ?: "Contato não informado"

                        val hotel = Hotel(title, address, cost, avaliacao,servico, phone)
                        hoteisList.add(hotel)
                    }

                    // Atualiza a lista no adapter
                    adapter.setHoteis(hoteisList)
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao carregar locais: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}