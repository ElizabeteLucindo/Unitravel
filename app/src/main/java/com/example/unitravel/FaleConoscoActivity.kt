package com.example.unitravel

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class FaleConoscoActivity : AppCompatActivity() {

    private lateinit var nome: EditText
    private lateinit var email: EditText
    private lateinit var mensagem: EditText
    private lateinit var btnEnviar: Button
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fale_conosco)

        db = FirebaseFirestore.getInstance()

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
        val mensagemData = hashMapOf(
            "nome" to nome,
            "email" to email,
            "mensagem" to mensagem,
            "timestamp" to System.currentTimeMillis()
        )

        // Adiciona a mensagem ao Firestore
        db.collection("fale_conosco")
            .add(mensagemData)
            .addOnSuccessListener {
                Toast.makeText(this, "Mensagem enviada com sucesso!", Toast.LENGTH_SHORT).show()
                limparCampos()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Erro ao enviar mensagem: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun limparCampos() {
        nome.text.clear()
        email.text.clear()
        mensagem.text.clear()
    }
}