package com.example.unitravel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HotelAdapter(private val hoteis: List<Hotel>) :
    RecyclerView.Adapter<HotelAdapter.HotelViewHolder>() {

    inner class HotelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.hoteisTitle)
        val address: TextView = itemView.findViewById(R.id.hoteisAddress)
        val cost: TextView = itemView.findViewById(R.id.hoteisCost)
        val topics: TextView = itemView.findViewById(R.id.hoteisTopics)
        val image: ImageView = itemView.findViewById(R.id.hoteisImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotelViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_hotel, parent, false)
        return HotelViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotelViewHolder, position: Int) {
        val hotel = hoteis[position]
        holder.title.text = hotel.title
        holder.address.text = hotel.address
        holder.cost.text = hotel.cost
        holder.topics.text = hotel.topics
        holder.image.setImageResource(hotel.imageResource)
    }

    override fun getItemCount() = hoteis.size
}