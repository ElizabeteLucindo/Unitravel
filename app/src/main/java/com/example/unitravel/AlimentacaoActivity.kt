package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.drawerlayout.widget.DrawerLayout
import android.widget.ImageView
import setupDrawer
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class AlimentacaoActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: RestaurantAdapter
    private lateinit var recyclerView: RecyclerView
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alimentacao)

        recyclerView = findViewById(R.id.recyclerViewRestaurants)

        // Configura o DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout_alimentacao)
        val menuIcon: ImageView = findViewById(R.id.btnMenu)
        setupDrawer(drawerLayout, menuIcon, this)

        // Configura o RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RestaurantAdapter(mutableListOf())
        recyclerView.adapter = adapter

        // Carrega os dados do Firestore
        carregarDestinos()
    }

    private fun carregarDestinos() {
        db.collection("destinos")
            .whereEqualTo("categoria", "Alimentação")
            .get()
            .addOnSuccessListener { documents ->
                val restaurantList = mutableListOf<Restaurant>()

                if (documents.isEmpty) {
                    Toast.makeText(this, "Nenhum restaurante encontrado.", Toast.LENGTH_SHORT).show()
                } else {
                    for (document in documents) {
                        val title = document.getString("nome") ?: "Nome não disponível"
                        val address = document.getString("localizacao") ?: "Endereço não disponível"
                        val type = document.getString("tipoCozinha") ?: "Tipo não informado"
                        val hours = document.getString("horario") ?: "Horário não informado"

                        val restaurante = Restaurant(title, address, type, hours)
                        restaurantList.add(restaurante)
                    }

                    adapter.setRestaurant(restaurantList)
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao carregar locais: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
