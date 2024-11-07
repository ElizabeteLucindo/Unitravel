package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent


class EscolhaCidadeActivity : AppCompatActivity() {

    private lateinit var botaoIrMenu: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_escolha_cidade)

        botaoIrMenu = findViewById(R.id.buttonIrMenu)
        botaoIrMenu.setOnClickListener {
            // Cria uma nova intenção para a atividade de cadastro
            val intent = Intent(this, EscolhaUsuarioActivity::class.java) // Cria a intenção
            startActivity(intent)
        }
    }
}