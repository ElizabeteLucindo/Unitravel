package com.example.unitravel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlaceAdapter(private var places: List<Place>) : RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.placeTitle)
        val address: TextView = itemView.findViewById(R.id.placeAddress)
        val cost: TextView = itemView.findViewById(R.id.placeCost)
        val hour: TextView = itemView.findViewById(R.id.placeHours)
        //val image: ImageView = itemView.findViewById(R.id.placeImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_place, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = places[position]
        holder.title.text = place.title
        holder.address.text = "Endereço: ${place.address}"
        holder.cost.text = "Custo: ${place.cost}"
        holder.hour.text = "Horário de funcionamento: ${place.hour}"

    }

    override fun getItemCount(): Int {
        return places.size
    }

    // Método para atualizar a lista de places
    fun setPlaces(newPlaces: List<Place>) {
        places = newPlaces
        notifyDataSetChanged()
    }
}
