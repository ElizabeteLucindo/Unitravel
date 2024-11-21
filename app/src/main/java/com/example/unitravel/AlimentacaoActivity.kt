package com.example.unitravel

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.drawerlayout.widget.DrawerLayout
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import setupDrawer
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class AlimentacaoActivity : AppCompatActivity() {

    private lateinit var textViewCabecalho: TextView
    private lateinit var tipoCozinhaSpinner: Spinner
    //private lateinit var precoSpinner: Spinner
    private lateinit var avaliacaoSpinner: Spinner
    //private lateinit var distanciaSpinner: Spinner
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var adapter: RestaurantAdapter
    private lateinit var recyclerView: RecyclerView
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alimentacao)

        recyclerView = findViewById(R.id.recyclerViewRestaurants)
        textViewCabecalho = findViewById(R.id.textViewCabecalho)
        tipoCozinhaSpinner = findViewById(R.id.tipoCozinhaSpinner)
        avaliacaoSpinner = findViewById(R.id.avaliacaoSpinner)


        // Configura o DrawerLayout
        drawerLayout = findViewById(R.id.drawer_layout_alimentacao)
        val menuIcon: ImageView = findViewById(R.id.btnMenu)
        setupDrawer(drawerLayout, menuIcon, this)

        // Configura o RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = RestaurantAdapter(mutableListOf())
        recyclerView.adapter = adapter

        // Recuperar a cidade e o estado do SharedPreferences
        val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
        val cidade = sharedPreferences.getString("cidade", "Cidade") ?: ""
        val estado = sharedPreferences.getString("estado", "Estado") ?: ""

        // Atualiza o texto do TextView
        textViewCabecalho.text = "$cidade-$estado"

        tipoCozinhaSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                // Atualiza o filtro com o valor selecionado
                carregarDestinos(cidade, estado)
            }
            override fun onNothingSelected(parentView: AdapterView<*>) {}
        }

        avaliacaoSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                carregarDestinos(cidade, estado)
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {}
        }
    }

    private fun carregarDestinos(cidade: String, estado: String) {

        // Obtém os valores selecionados nos Spinners
        val tipoCozinhaSelecionado = tipoCozinhaSpinner.selectedItem.toString()
        val avaliacaoSelecionada = avaliacaoSpinner.selectedItem.toString()

        // Limpa a lista de restaurantes no adaptador
        adapter.setRestaurant(mutableListOf())

        // Começa a consulta com os filtros obrigatórios
        var query = db.collection("destinos")
            .whereEqualTo("categoria", "Alimentação")
            .whereEqualTo("cidade", cidade)
            .whereEqualTo("estado", estado)

        // Aplica o filtro de tipo de cozinha, se selecionado
        if (tipoCozinhaSelecionado != "Tipo de cozinha") {
            query = query.whereEqualTo("tipoCozinha", tipoCozinhaSelecionado)
        }

        // Aplica o filtro de avaliação, se selecionado
        if (avaliacaoSelecionada != "Avaliação") {
            query = query.whereEqualTo("avaliacao", avaliacaoSelecionada)
        }

        // Executa a consulta
        query.get()
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
                        val phone = document.getString("contato") ?: "Telefone não informado"
                        val rate = document.getString("avaliacao")?.let {
                            if (it == "Avaliação") "" else it
                        } ?: "Avaliação não informada"

                        val restaurante = Restaurant(title, address, type, hours, phone, rate)
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
