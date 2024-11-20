package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore


class CadastroActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextUsuario: EditText
    private lateinit var editTextSenha: EditText
    private lateinit var botaoCadastro: Button
    private lateinit var textViewContaExistente: TextView

    private lateinit var auth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Inicializa os componentes da interface
        editTextEmail = findViewById(R.id.email)
        editTextUsuario = findViewById(R.id.usuario)
        editTextSenha = findViewById(R.id.senha)
        botaoCadastro = findViewById(R.id.buttonCadastro)
        textViewContaExistente = findViewById(R.id.contaExistente)

        // Inicializa o Firebase Authentication e Firestore
        auth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        // Aperta botão de cadastrar
        botaoCadastro.setOnClickListener {

            val email = editTextEmail.text.toString()
            val usuario = editTextUsuario.text.toString()
            val senha = editTextSenha.text.toString()

            if (email.isEmpty() || usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Registra o usuário no Firebase Authentication
            auth.createUserWithEmailAndPassword(email, senha)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Se o cadastro for bem-sucedido, salva no Firestore
                        val user = auth.currentUser
                        val userData = hashMapOf(
                            "email" to email,
                            "usuario" to usuario,
                            "senha" to senha
                        )

                        db.collection("usuarios")
                            .document(user?.uid ?: "")  // Usa o UID do Firebase Auth
                            .set(userData)
                            .addOnSuccessListener {
                                Toast.makeText(this, "Cadastro feito com sucesso", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            }
                            .addOnFailureListener { e ->
                                Toast.makeText(this, "Erro ao salvar dados no Firestore: ${e.message}", Toast.LENGTH_SHORT).show()
                            }
                    } else {
                        Toast.makeText(this, "Erro no cadastro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        // Aperta o botão para voltar ao login
        textViewContaExistente.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}