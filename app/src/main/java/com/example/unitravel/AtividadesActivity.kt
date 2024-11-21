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



class AtividadesActivity : AppCompatActivity() {

    private lateinit var textViewCabecalho: TextView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: PlaceAdapter
    private lateinit var recyclerView: RecyclerView
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atividades)

        textViewCabecalho = findViewById(R.id.textViewCabecalho)
        recyclerView = findViewById(R.id.recyclerView)

        // Configura o DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout)
        val menuIcon: ImageView = findViewById(R.id.btnMenu)
        setupDrawer(drawerLayout, menuIcon, this)


        // Configura o RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PlaceAdapter(emptyList()) // Inicia com uma lista vazia
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
            .whereEqualTo("categoria", "Atividades")
            .whereEqualTo("cidade", cidade)
            .whereEqualTo("estado", estado)
            .get()
            .addOnSuccessListener { documents ->
                val placesList = mutableListOf<Place>()

                if (documents.isEmpty) {
                    Toast.makeText(this, "Nenhum restaurante encontrado.", Toast.LENGTH_SHORT).show()
                } else {
                    for (document in documents) {
                        val title = document.getString("nome") ?: "Nome não disponível"
                        val address = document.getString("localizacao") ?: "Endereço não disponível"
                        val cost = document.getString("custo") ?: "Custo não disponível"
                        val hour = document.getString("horario") ?: "Horario de funcionamento não disponível"
                        //val imageResource = document.getString("imagemUrl") ?: ""

                        // Cria um objeto Destino e adiciona à lista
                        val place = Place(title, address, cost, hour)
                        placesList.add(place)
                    }

                    // Atualiza a lista no adapter
                    adapter.setPlaces(placesList)
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao carregar locais: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}