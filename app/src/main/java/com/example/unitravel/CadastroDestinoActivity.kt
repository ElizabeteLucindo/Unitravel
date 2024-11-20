package com.example.unitravel

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class CadastroDestinoActivity : AppCompatActivity() {

    // Inicializa os campos e o botão de cadastro
    private lateinit var nomeDestino: EditText
    private lateinit var categoria: Spinner
    private lateinit var localizacao: EditText
    private lateinit var horario: EditText
    private lateinit var custo: EditText
    private lateinit var contato: EditText
    private lateinit var tipoCozinha: Spinner
    private lateinit var avaliacao: Spinner
    private lateinit var label_tipoCozinha: TextView
    private lateinit var label_avaliacao: TextView
    private lateinit var btnCadastro: Button


    // Inicializa o Firestore
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_destino)

        // Conectando os elementos XML aos elementos da atividade
        nomeDestino = findViewById(R.id.nomeDestino)
        categoria = findViewById(R.id.Categoria)
        localizacao = findViewById(R.id.Localizacao)
        horario = findViewById(R.id.horario)
        custo = findViewById(R.id.custo)
        contato = findViewById(R.id.contato)
        tipoCozinha = findViewById(R.id.tipoCozinha)
        avaliacao = findViewById(R.id.avaliacao)
        label_tipoCozinha = findViewById(R.id.labelTipoCozinha)
        label_avaliacao = findViewById(R.id.labelAvaliacao)
        btnCadastro = findViewById(R.id.buttonCadastro)


        // Campos adicionais
        tipoCozinha.visibility = View.GONE
        avaliacao.visibility = View.GONE
        label_tipoCozinha.visibility = View.GONE
        label_avaliacao.visibility = View.GONE

        // Configuração do Spinner para selecionar a categoria
        categoria.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, view: View?, position: Int, id: Long) {
                // Exibe os campos extras quando "Alimentação" for selecionada
                if (categoria.selectedItem.toString() == "Alimentação") {
                    tipoCozinha.visibility = View.VISIBLE
                    avaliacao.visibility = View.VISIBLE
                    label_tipoCozinha.visibility = View.VISIBLE
                    label_avaliacao.visibility = View.VISIBLE
                } else {
                    tipoCozinha.visibility = View.GONE
                    avaliacao.visibility = View.GONE
                    label_tipoCozinha.visibility = View.GONE
                    label_avaliacao.visibility = View.GONE
                }
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                tipoCozinha.visibility = View.GONE
                avaliacao.visibility = View.GONE
                label_tipoCozinha.visibility = View.GONE
                label_avaliacao.visibility = View.GONE
            }
        }

        // Configura o botão para salvar o destino no Firestore
        btnCadastro.setOnClickListener {
            cadastrarDestino()
        }
    }

    private fun cadastrarDestino() {
        // Captura os valores dos campos
        val nome = nomeDestino.text.toString()
        val categoriaSelecionada = categoria.selectedItem.toString()
        val localizacaoText = localizacao.text.toString()
        val horarioText = horario.text.toString()
        val custoText = custo.text.toString()
        val contatoText = contato.text.toString()
        val tipoCozinhaText = tipoCozinha.selectedItem.toString()
        val avaliacaoText = avaliacao.selectedItem.toString()

        // Verifica se todos os campos foram preenchidos
        if (nome.isEmpty() || localizacaoText.isEmpty() || horarioText.isEmpty() || custoText.isEmpty() || contatoText.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        if  (categoriaSelecionada == "Alimentação" && tipoCozinhaText == "Tipo de cozinha") {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            return
        }

        // Cria o objeto para salvar no Firestore
        val destino = hashMapOf(
            "nome" to nome,
            "categoria" to categoriaSelecionada,
            "localizacao" to localizacaoText,
            "horario" to horarioText,
            "custo" to custoText,
            "contato" to contatoText
        )

        // Adiciona os campos adicionais caso a categoria seja "Alimentação"
        if (categoriaSelecionada == "Alimentação") {
            destino["tipoCozinha"] = tipoCozinhaText
            destino["avaliacao"] = avaliacaoText
        }

        // Salva no Firestore
        db.collection("destinos").add(destino)
            .addOnSuccessListener {
                Toast.makeText(this, "Destino cadastrado com sucesso!", Toast.LENGTH_SHORT).show()
                limparCampos()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao cadastrar destino: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun limparCampos() {
        // Limpa os campos após o cadastro
        nomeDestino.text.clear()
        localizacao.text.clear()
        horario.text.clear()
        custo.text.clear()
        contato.text.clear()
        tipoCozinha.setSelection(0)
        avaliacao.setSelection(0)
        categoria.setSelection(0)
    }
}
