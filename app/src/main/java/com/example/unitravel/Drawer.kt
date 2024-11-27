import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.view.GravityCompat
import com.example.unitravel.AlimentacaoActivity
import com.example.unitravel.AtividadesActivity
import com.example.unitravel.CadastroDestinoActivity
import com.example.unitravel.EscolhaUsuarioActivity
import com.example.unitravel.FaleConoscoActivity
import com.example.unitravel.GerenciarDestinosActivity
import com.example.unitravel.HoteisActivity
import com.example.unitravel.MainActivity
import com.example.unitravel.R
import com.example.unitravel.TransporteActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

fun setupDrawer(drawerLayout: DrawerLayout, menuIcon: ImageView, context: Context) {

    // Referências ao Firebase
    val auth = FirebaseAuth.getInstance()
    val db = FirebaseFirestore.getInstance()
    val currentUser = auth.currentUser

    // Configurações do ícone do menu
    menuIcon.setOnClickListener {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    // Referências aos itens do menu
    val cadastrar = drawerLayout.findViewById<TextView>(R.id.menu_cadastrar)
    val gerenciar_destino = drawerLayout.findViewById<TextView>(R.id.menu_gerenciar)
    val icon_cadastrar = drawerLayout.findViewById<ImageView>(R.id.icon_cadastrar)
    val icon_gerenciar = drawerLayout.findViewById<ImageView>(R.id.icon_gerenciar)

    // Verifica o tipo do usuário
    if (currentUser != null) {
        db.collection("usuarios").document(currentUser.uid).get()
            .addOnSuccessListener { document ->
                val tipoUsuario = document.getString("tipoUsuario")
                if (tipoUsuario == "Secretaria do Turismo") {
                    cadastrar.visibility = View.VISIBLE // Exibe o item
                    gerenciar_destino.visibility = View.VISIBLE
                    icon_cadastrar.visibility = View.VISIBLE
                    icon_gerenciar.visibility = View.VISIBLE
                } else {
                    cadastrar.visibility = View.GONE // Oculta o item
                    gerenciar_destino.visibility = View.GONE
                    icon_cadastrar.visibility = View.GONE
                    icon_gerenciar.visibility = View.GONE
                }
            }
            .addOnFailureListener { e ->
                cadastrar.visibility = View.GONE // Oculta em caso de erro
                gerenciar_destino.visibility = View.GONE
                icon_cadastrar.visibility = View.GONE
                icon_gerenciar.visibility = View.GONE
                e.printStackTrace()
            }
    } else {
        cadastrar.visibility = View.GONE // Oculta se o usuário não estiver logado
        gerenciar_destino.visibility = View.GONE
        icon_cadastrar.visibility = View.GONE
        icon_gerenciar.visibility = View.GONE
    }

    // Configuração dos outros itens do menu
    val inicio = drawerLayout.findViewById<TextView>(R.id.menu_inicio)
    inicio.setOnClickListener {
        drawerLayout.closeDrawer(GravityCompat.START)
        val intent = Intent(context, EscolhaUsuarioActivity::class.java)
        context.startActivity(intent)
    }

    cadastrar.setOnClickListener {
        drawerLayout.closeDrawer(GravityCompat.START)
        val intent = Intent(context, CadastroDestinoActivity::class.java)
        context.startActivity(intent)
    }

    gerenciar_destino.setOnClickListener {
        drawerLayout.closeDrawer(GravityCompat.START)
        val intent = Intent(context, GerenciarDestinosActivity::class.java)
        context.startActivity(intent)
    }

    val menuHoteis = drawerLayout.findViewById<TextView>(R.id.menu_hoteis)
    menuHoteis.setOnClickListener {
        drawerLayout.closeDrawer(GravityCompat.START)
        val intent = Intent(context, HoteisActivity::class.java)
        context.startActivity(intent)
    }

    val menuAtividades = drawerLayout.findViewById<TextView>(R.id.menu_atividades)
    menuAtividades.setOnClickListener {
        drawerLayout.closeDrawer(GravityCompat.START)
        val intent = Intent(context, AtividadesActivity::class.java)
        context.startActivity(intent)
    }

    val menuAlimentacao = drawerLayout.findViewById<TextView>(R.id.menu_alimentacao)
    menuAlimentacao.setOnClickListener {
        drawerLayout.closeDrawer(GravityCompat.START)
        val intent = Intent(context, AlimentacaoActivity::class.java)
        context.startActivity(intent)
    }

    val menuTransporte = drawerLayout.findViewById<TextView>(R.id.menu_transporte)
    menuTransporte.setOnClickListener {
        drawerLayout.closeDrawer(GravityCompat.START)
        val intent = Intent(context, TransporteActivity::class.java)
        context.startActivity(intent)
    }

    val menuFaleConosco = drawerLayout.findViewById<TextView>(R.id.menu_faleConosco)
    menuFaleConosco.setOnClickListener {
        drawerLayout.closeDrawer(GravityCompat.START)
        val intent = Intent(context, FaleConoscoActivity::class.java)
        context.startActivity(intent)
    }

    val sair = drawerLayout.findViewById<TextView>(R.id.sair)
    sair.setOnClickListener {
        drawerLayout.closeDrawer(GravityCompat.START)
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }

}
