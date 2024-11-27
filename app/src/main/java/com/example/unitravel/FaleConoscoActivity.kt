package com.example.unitravel

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class FaleConoscoActivity : AppCompatActivity() {

    private lateinit var nome: EditText
    private lateinit var email: EditText
    private lateinit var mensagem: EditText
    private lateinit var btnEnviar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fale_conosco)

        nome = findViewById(R.id.nome)
        email = findViewById(R.id.email)
        mensagem = findViewById(R.id.mensagem)
        btnEnviar = findViewById(R.id.buttonEnviar)

        btnEnviar.setOnClickListener {

            val nome = nome.text.toString()
            val email = email.text.toString()
            val mensagem = mensagem.text.toString()

            if (nome.isEmpty() || email.isEmpty() || mensagem.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            enviarEmail(nome, email, mensagem)
        }
    }

    private fun enviarEmail(nome: String, email: String, mensagem: String){
        // Configurar o Intent para abrir o cliente de e-mail
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = android.net.Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, arrayOf("elsilvahde@gmail.com")) // Endereço de e-mail para onde a mensagem será enviada
            putExtra(Intent.EXTRA_SUBJECT, "Mensagem de $nome")
            putExtra(Intent.EXTRA_TEXT, "Nome: $nome\nE-mail: $email\n\nMensagem:\n$mensagem")
        }

        // Verificar se existe um aplicativo de e-mail para tratar a Intent
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)  // Abrir o aplicativo de e-mail para o usuário enviar a mensagem
        } else {
            Toast.makeText(this, "Não foi possível encontrar um aplicativo de e-mail.", Toast.LENGTH_SHORT).show()
        }


    }
}