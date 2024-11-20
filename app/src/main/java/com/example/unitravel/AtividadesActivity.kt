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



class AtividadesActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: PlaceAdapter
    private lateinit var recyclerView: RecyclerView
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atividades)

        recyclerView = findViewById(R.id.recyclerView)

        // Configura o DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)
        val menuIcon: ImageView = findViewById(R.id.btnMenu)
        setupDrawer(drawerLayout, menuIcon, this)


        // Configura o RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PlaceAdapter(emptyList()) // Inicia com uma lista vazia
        recyclerView.adapter = adapter

        // Carrega os dados do Firestore
        carregarDestinos()
    }

    private fun carregarDestinos(){
        db.collection("destinos")
            .whereEqualTo("categoria", "Atividades")
            .get()
            .addOnSuccessListener { documents ->
                val placesList = mutableListOf<Place>()
                for (document in documents) {
                    val title  = document.getString("nome") ?: ""
                    val address  = document.getString("localizacao") ?: ""
                    val cost  = document.getString("custo") ?: ""
                    val hour = document.getString("horario") ?: ""
                    //val imageResource = document.getString("imagemUrl") ?: ""

                    // Cria um objeto Destino e adiciona à lista
                    val place = Place(title, address, cost, hour)
                    placesList.add(place)
                }

                // Atualiza a lista no adapter
                adapter.setPlaces(placesList)
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao carregar locais: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}