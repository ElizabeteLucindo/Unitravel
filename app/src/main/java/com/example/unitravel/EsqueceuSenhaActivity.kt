package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class EsqueceuSenhaActivity : AppCompatActivity() {
    private lateinit var editTextEmail: EditText
    private lateinit var botaoEnviar: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_esqueceu_senha)

        // Inicializa o FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Inicializa os componentes da interface
        editTextEmail = findViewById(R.id.email)
        botaoEnviar = findViewById(R.id.buttonEnviar)

        // Aperta botão de enviar
        botaoEnviar.setOnClickListener {

            // Obtém o texto dos campos de entrada
            val email = editTextEmail.text.toString()

            // Verifica se os campos estão vazios
            if (email.isEmpty()) {
                Toast.makeText(this, "Por favor, insira seu e-mail.", Toast.LENGTH_SHORT).show()
            } else {
                enviarEmailRecuperacao(email)
            }
        }
    }

    private fun enviarEmailRecuperacao(email: String) {
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "E-mail de redefinição enviado. Verifique sua caixa de entrada.",
                        Toast.LENGTH_LONG).show()

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent) // Inicia a atividade de Login
                } else {
                    Toast.makeText(
                        this,
                        "Erro ao enviar o e-mail. Verifique o endereço e tente novamente.",
                        Toast.LENGTH_LONG).show()
                }
            }
    }
}