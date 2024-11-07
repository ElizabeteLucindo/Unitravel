package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.Toast

class NovaSenhaActivity : AppCompatActivity() {
    private lateinit var editTextNovaSenha: EditText
    private lateinit var editTextConfirmarSenha: EditText
    private lateinit var botaoEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nova_senha)

        // Inicializa os componentes da interface
        editTextNovaSenha = findViewById(R.id.novaSenha)
        editTextConfirmarSenha = findViewById(R.id.ConfirmarSenha)
        botaoEnviar = findViewById(R.id.buttonConfirmar)

        // Aperta botão de enviar
        botaoEnviar.setOnClickListener {

            // Obtém o texto dos campos de entrada
            val NovaSenha = editTextNovaSenha.text.toString()
            val ConfirmarSenha = editTextConfirmarSenha.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent) // Inicia a atividade de Login
        }
    }
}