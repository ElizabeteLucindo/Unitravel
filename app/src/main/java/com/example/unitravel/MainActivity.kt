package com.example.unitravel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import android.content.Intent


class MainActivity : AppCompatActivity() {
    // Declaração das variáveis para os componentes da interface
    private lateinit var editTextUsuario: EditText
    private lateinit var editTextSenha: EditText
    private lateinit var botaoLogin: Button
    private lateinit var textViewEqueceuSenha: TextView
    private lateinit var textViewCadastrar: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializa os componentes da interface
        editTextUsuario = findViewById(R.id.usuario)
        editTextSenha = findViewById(R.id.senha)
        botaoLogin = findViewById(R.id.buttonCadastro)
        textViewEqueceuSenha = findViewById(R.id.esqueceuSenha)
        textViewCadastrar = findViewById(R.id.cadastrar)

        // Aperta botão de login
        botaoLogin.setOnClickListener {

            // Obtém o texto dos campos de entrada
            val usuario = editTextUsuario.text.toString()
            val senha = editTextSenha.text.toString()

            // Verifica se os campos estão vazios
            if(usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }else{
                val intent = Intent(this, EscolhaCidadeActivity::class.java) // Cria a intenção
                startActivity(intent) // Inicia a atividade de cadastro
            }
        }

        // Aperta o botão para cadastrar
        textViewCadastrar.setOnClickListener {
            // Cria uma nova intenção para a atividade de cadastro
            val intent = Intent(this, CadastroDestinoActivity::class.java) // Cria a intenção
            startActivity(intent) // Inicia a atividade de cadastro
        }

        textViewEqueceuSenha.setOnClickListener {
            // Cria uma nova intenção para a atividade de esqueceu senha
            val intent = Intent(this, EsqueceuSenhaActivity::class.java) // Cria a intenção
            startActivity(intent)
        }

    }
}