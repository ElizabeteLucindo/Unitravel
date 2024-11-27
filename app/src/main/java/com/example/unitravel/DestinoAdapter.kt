package com.example.unitravel

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class DestinosAdapter(private val destinos: MutableList<Destino>) : RecyclerView.Adapter<DestinosAdapter.DestinoViewHolder>() {

    private val db = FirebaseFirestore.getInstance()

    class DestinoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titulo = itemView.findViewById<TextView>(R.id.txtTituloDestino)
        val categoria = itemView.findViewById<TextView>(R.id.txtCategoriaDestino)
        val delete = itemView.findViewById<ImageView>(R.id.deleteImageView)
        val edit = itemView.findViewById<ImageView>(R.id.editImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DestinoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_destino, parent, false)
        return DestinoViewHolder(view)
    }

    override fun onBindViewHolder(holder: DestinoViewHolder, position: Int) {
        val destino = destinos[position]
        holder.titulo.text = destino.nome
        holder.categoria.text = destino.categoria

        // Lógica para o botão de deletar
        holder.delete.setOnClickListener {
            val destinoId = destino.id // Certifique-se de que 'id' está sendo atribuído corretamente
            db.collection("destinos")
                .document(destinoId)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(holder.itemView.context, "Destino excluído com sucesso", Toast.LENGTH_SHORT).show()
                    destinos.removeAt(position)  // Remove da lista local
                    notifyItemRemoved(position)  // Atualiza o RecyclerView
                }
                .addOnFailureListener { e ->
                    Toast.makeText(holder.itemView.context, "Falha ao excluir: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }

        holder.edit.setOnClickListener {
            val intent = Intent(holder.itemView.context, CadastroDestinoActivity::class.java)
            intent.putExtra("destinoId", destino.id) // Passa o ID do destino
            intent.putExtra("nome", destino.nome)
            intent.putExtra("categoria", destino.categoria)
            intent.putExtra("localizacao", destino.localizacao)
            intent.putExtra("horario", destino.horario)
            intent.putExtra("custo", destino.custo)
            intent.putExtra("contato", destino.contato)
            intent.putExtra("cidade", destino.cidade)
            intent.putExtra("estado", destino.estado)
            intent.putExtra("tipoCozinha", destino.tipoCozinha)
            intent.putExtra("avaliacao", destino.avaliacao)
            intent.putExtra("servicos", destino.servicos)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = destinos.size

    fun atualizarLista(novaLista: MutableList<Destino>) {
        this.destinos.clear()
        this.destinos.addAll(novaLista)
        notifyDataSetChanged()
    }
}

