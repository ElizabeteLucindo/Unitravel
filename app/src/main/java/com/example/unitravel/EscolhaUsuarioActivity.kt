package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent


class EscolhaUsuarioActivity : AppCompatActivity() {

    private lateinit var botaoMudarLocal: Button
    private lateinit var botaoHoteis: Button
    private lateinit var botaoAlimentacao: Button
    private lateinit var botaoAtividades: Button
    private lateinit var botaoTransporte: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escolha_usuario)

        // Inicializa os componentes da interface
        botaoMudarLocal = findViewById(R.id.btnMudarLocal)
        botaoHoteis = findViewById(R.id.btnHoteis)
        botaoAlimentacao = findViewById(R.id.btnAlimentacao)
        botaoAtividades = findViewById(R.id.btnAtividades)
        botaoTransporte = findViewById(R.id.btnTransporte)

        // Aperta botão de MudarLocal
        botaoMudarLocal.setOnClickListener {
            val intent = Intent(this, EscolhaCidadeActivity::class.java) // Cria a intenção
            startActivity(intent)
        }

        // Aperta botão de Hotéis
        botaoHoteis.setOnClickListener {
            val intent = Intent(this, HoteisActivity::class.java) // Cria a intenção
            startActivity(intent)
        }

        // Aperta botão de Alimentacao
        botaoAlimentacao.setOnClickListener {
            val intent = Intent(this, AlimentacaoActivity::class.java)
            startActivity(intent)
        }

        // Aperta botão de Atividades
        botaoAtividades.setOnClickListener {
            val intent = Intent(this, AtividadesActivity::class.java) // Cria a intenção
            startActivity(intent)
        }

        // Aperta botão de Transporte
        botaoTransporte.setOnClickListener {
            val intent = Intent(this, TransporteActivity::class.java) // Cria a intenção
            startActivity(intent)
        }
    }
}