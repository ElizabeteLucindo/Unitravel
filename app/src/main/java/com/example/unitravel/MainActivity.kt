package com.example.unitravel

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    // Declaração das variáveis para os componentes da interface
    private lateinit var editTextUsuario: EditText
    private lateinit var editTextSenha: EditText
    private lateinit var botaoLogin: Button
    private lateinit var textViewEqueceuSenha: TextView
    private lateinit var textViewCadastrar: TextView


    // Instância do FirebaseAuth para autenticação
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextUsuario = findViewById(R.id.usuario)
        editTextSenha = findViewById(R.id.senha)
        botaoLogin = findViewById(R.id.buttonCadastro)
        textViewEqueceuSenha = findViewById(R.id.esqueceuSenha)
        textViewCadastrar = findViewById(R.id.cadastrar)

        auth = FirebaseAuth.getInstance()

        // Aperta botão de login
        botaoLogin.setOnClickListener {

            val usuario = editTextUsuario.text.toString()
            val senha = editTextSenha.text.toString()

            if (usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verifica se o usuário existe no Firebase Authentication
            auth.signInWithEmailAndPassword(usuario, senha)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val intent = Intent(this, EscolhaCidadeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "Erro no login: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                    }
                }
        }

        textViewCadastrar.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

        textViewEqueceuSenha.setOnClickListener {
            val intent = Intent(this, EsqueceuSenhaActivity::class.java)
            startActivity(intent)
        }
    }
}
