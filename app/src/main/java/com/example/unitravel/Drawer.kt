import android.content.Context
import android.content.Intent
import android.widget.ImageView
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.core.view.GravityCompat
import com.example.unitravel.CadastroDestinoActivity
import com.example.unitravel.EscolhaUsuarioActivity
import com.example.unitravel.MainActivity
import com.example.unitravel.R

// Função para configurar o DrawerLayout
fun setupDrawer(drawerLayout: DrawerLayout, menuIcon: ImageView, context: Context) {
    menuIcon.setOnClickListener {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    // Configure o clique para a Opção 1 no menu
    val option1 = drawerLayout.findViewById<TextView>(R.id.menu_option1)
    option1.setOnClickListener {
        // Fechar o menu lateral
        drawerLayout.closeDrawer(GravityCompat.START)

        // Iniciar a nova Activity (Exemplo: CadastroDestinoActivity)
        val intent = Intent(context, EscolhaUsuarioActivity::class.java)
        context.startActivity(intent)
    }

    // Clique para a Cadastrar no menu
    val cadastrar = drawerLayout.findViewById<TextView>(R.id.menu_cadastrar)
    cadastrar.setOnClickListener {
        // Fechar o menu lateral
        drawerLayout.closeDrawer(GravityCompat.START)

        // Iniciar a nova Activity (Exemplo: CadastroDestinoActivity)
        val intent = Intent(context, CadastroDestinoActivity::class.java)
        context.startActivity(intent)
    }

    // Clique para a Cadastrar no menu
    val sair = drawerLayout.findViewById<TextView>(R.id.sair)
    sair.setOnClickListener {
        // Fechar o menu lateral
        drawerLayout.closeDrawer(GravityCompat.START)

        // Iniciar a nova Activity (Exemplo: CadastroDestinoActivity)
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }
}
