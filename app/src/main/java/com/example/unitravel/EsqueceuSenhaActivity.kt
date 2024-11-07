package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.Toast


class EsqueceuSenhaActivity : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var botaoEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esqueceu_senha)

        // Inicializa os componentes da interface
        editTextEmail = findViewById(R.id.email)
        botaoEnviar = findViewById(R.id.buttonEnviar)

        // Aperta botão de enviar
        botaoEnviar.setOnClickListener {

            // Obtém o texto dos campos de entrada
            val recuperar = editTextEmail.text.toString()

            // Verifica se os campos estão vazios
            if (recuperar.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            } else {
                Toast.makeText(this, "Email enviado com sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, NovaSenhaActivity::class.java)
                startActivity(intent) // Inicia a atividade de Login
            }
        }
    }
}