package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore


class EscolhaCidadeActivity : AppCompatActivity() {

    private val firestore = FirebaseFirestore.getInstance()

    private lateinit var botaoIrMenu: Button
    private lateinit var spinnerEstado: Spinner
    private lateinit var spinnerCidade: Spinner


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escolha_cidade)

        botaoIrMenu = findViewById(R.id.buttonIrMenu)
        spinnerEstado = findViewById(R.id.spinner_estado)
        spinnerCidade = findViewById(R.id.spinner_cidade)

        // Busca os estados do Firestore
        buscarEstados()

        // Listener do SpinnerEstado para carregar cidades
        spinnerEstado.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                val estadoSelecionado = spinnerEstado.selectedItem.toString()
                carregarCidades(estadoSelecionado)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        botaoIrMenu.setOnClickListener {

            val estadoSelecionado = spinnerEstado.selectedItem.toString()
            val cidadeSelecionada = spinnerCidade.selectedItem.toString()

            // Salva os dados de cidade e estado no SharedPreferences
            val sharedPreferences = getSharedPreferences("user_preferences", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("cidade", cidadeSelecionada)
            editor.putString("estado", estadoSelecionado)
            editor.apply()  // Salva as informações

            // Cria uma nova intenção para a atividade de cadastro
            val intent = Intent(this, EscolhaUsuarioActivity::class.java) // Cria a intenção
            intent.putExtra("cidade", cidadeSelecionada)
            intent.putExtra("estado", estadoSelecionado)

            startActivity(intent)
        }
    }

    // Função para buscar e carregar os estados no Spinner
    private fun buscarEstados(){


        firestore.collection("destinos")
            .get()
            .addOnSuccessListener { documents ->
                val estados = mutableSetOf<String>() // Evita duplicados
                for (document in documents) {
                    val estado = document.getString("estado")
                    estado?.let { estados.add(it) }
                }

                if (estados.isEmpty()) {
                    estados.add("Nenhum estado encontrado")
                }

                val adapterEstados = ArrayAdapter(this, android.R.layout.simple_spinner_item, estados.toList())
                adapterEstados.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerEstado.adapter = adapterEstados
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }

    // Função para carregar as cidades de acordo com o estado selecionado
    private fun carregarCidades(estado: String){
        firestore.collection("destinos")
            .whereEqualTo("estado", estado)
            .get()
            .addOnSuccessListener { documents ->
                val cidades = mutableSetOf<String>() // Usando Set para evitar duplicação de cidades
                for (document in documents) {
                    val cidade = document.getString("cidade")
                    cidade?.let { cidades.add(it) }
                }

                if (cidades.isEmpty()) {
                    cidades.add("Nenhuma cidade encontrada")
                }

                val adapterCidades = ArrayAdapter(this, android.R.layout.simple_spinner_item, cidades.toList())
                adapterCidades.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCidade.adapter = adapterCidades
            }
            .addOnFailureListener { e ->
                e.printStackTrace()
            }
    }
}
