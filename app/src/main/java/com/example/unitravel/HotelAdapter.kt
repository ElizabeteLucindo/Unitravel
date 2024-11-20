package com.example.unitravel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HotelAdapter(private val hoteis: MutableList<Hotel>) :
    RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    inner class HotelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.hoteisTitle)
        val address: TextView = itemView.findViewById(R.id.hoteisAddress)
        val cost: TextView = itemView.findViewById(R.id.hoteisCost)
        val avaliacaoText: TextView = itemView.findViewById(R.id.hoteisAvaliacaoText)
        val servicos: TextView = itemView.findViewById(R.id.hoteisServiço)
        val phone: TextView = itemView.findViewById(R.id.hoteisPhone)
        //val image: ImageView = itemView.findViewById(R.id.hoteisImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hotel, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hoteis[position]
        holder.title.text = hotel.title
        holder.address.text = "Endereço: ${hotel.address}"
        holder.cost.text = "Diária Mínima: ${hotel.cost}"
        holder.servicos.text = "Serviços: ${hotel.servicos}"
        holder.avaliacaoText.text = "Avaliação: ${hotel.avaliacao}"
        holder.phone.text = "Contato: ${hotel.phone}"
        //holder.image.setImageResource(hotel.imageResource)



    }

    override fun getItemCount(): Int {
        return hoteis.size
    }

    // Método para atualizar a lista de places
    fun setHoteis(newHoteis: List<Hotel>) {
        this.hoteis.clear()
        this.hoteis.addAll(newHoteis)
        notifyDataSetChanged()
    }
}