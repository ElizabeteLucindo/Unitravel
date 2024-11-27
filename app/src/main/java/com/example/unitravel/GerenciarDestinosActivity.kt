package com.example.unitravel

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class GerenciarDestinosActivity : AppCompatActivity() {

    private lateinit var spinnerCategoria: Spinner
    private lateinit var recyclerView: RecyclerView
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gerenciar_destinos)

        // Inicializa os elementos
        spinnerCategoria = findViewById(R.id.spinnerCategoria)
        recyclerView = findViewById(R.id.recyclerViewDestinos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        val cidade = sharedPreferences.getString("cidade", "Cidade") ?: ""
        val estado = sharedPreferences.getString("estado", "Estado") ?: ""

        // Configura o Spinner para carregar destinos com base na categoria selecionada
        spinnerCategoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val categoriaSelecionada = spinnerCategoria.selectedItem.toString()
                carregarDestinos(categoriaSelecionada, cidade, estado)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Não faz nada
            }
        }
    }

    private fun carregarDestinos(categoria: String, cidade: String, estado: String) {
        // Faz a consulta no Firebase com base na categoria
        db.collection("destinos")
            .whereEqualTo("categoria", categoria)
            .whereEqualTo("cidade", cidade)
            .whereEqualTo("estado", estado)
            .get()
            .addOnSuccessListener { documents ->
                val destinos = documents.map { document ->
                    val destino = document.toObject(Destino::class.java)
                    destino.id = document.id  // Adiciona o ID do Firestore ao destino
                    destino
                }.toMutableList()
                recyclerView.adapter = DestinosAdapter(destinos)

            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }
    override fun onResume() {
        super.onResume()
        // Captura a categoria selecionada do Spinner
        val categoriaSelecionada = spinnerCategoria.selectedItem.toString()

        // Recupera a cidade e o estado das preferências compartilhadas
        val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        val cidade = sharedPreferences.getString("cidade", "Cidade") ?: ""
        val estado = sharedPreferences.getString("estado", "Estado") ?: ""

        // Chama a função para carregar os destinos com base na categoria, cidade e estado
        carregarDestinos(categoriaSelecionada, cidade, estado)
    }

}
