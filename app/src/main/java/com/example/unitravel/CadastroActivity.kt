package com.example.unitravel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import android.content.Intent

class CadastroActivity : AppCompatActivity() {
    // Declaração das variáveis para os componentes da interface
    private lateinit var editTextEmail: EditText
    private lateinit var editTextUsuario: EditText
    private lateinit var editTextSenha: EditText
    private lateinit var botaoCadastro: Button
    private lateinit var textViewContaExistente: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Inicializa os componentes da interface
        editTextEmail = findViewById(R.id.email)
        editTextUsuario = findViewById(R.id.usuario)
        editTextSenha = findViewById(R.id.senha)
        botaoCadastro = findViewById(R.id.buttonCadastro)
        textViewContaExistente = findViewById(R.id.contaExistente)

        // Aperta botão de cadastrar
        botaoCadastro.setOnClickListener {

            // Obtém o texto dos campos de entrada
            val usuario = editTextUsuario.text.toString()
            val senha = editTextSenha.text.toString()

            // Verifica se os campos estão vazios
            if(usuario.isEmpty() || senha.isEmpty() || usuario.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                Toast.makeText(this, "Cadastro feito com sucesso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this,EscolhaUsuarioActivity::class.java)
                startActivity(intent) // Inicia a atividade de Login
            }
        }

        // Aperta o botão para voltar ao login
        textViewContaExistente.setOnClickListener {
            // Cria uma nova intenção para a atividade de cadastro
            val intent = Intent(this, MainActivity::class.java) // Cria a intenção
            startActivity(intent) // Inicia a atividade de Login
        }
    }
}