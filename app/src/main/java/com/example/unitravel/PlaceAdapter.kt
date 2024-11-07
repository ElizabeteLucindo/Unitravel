package com.example.unitravel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlaceAdapter(private val places: List<Place>) :
    RecyclerView.Adapter<PlaceAdapter.PlaceViewHolder>() {

    inner class PlaceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.placeTitle)
        val address: TextView = itemView.findViewById(R.id.placeAddress)
        val cost: TextView = itemView.findViewById(R.id.placeCost)
        val topics: TextView = itemView.findViewById(R.id.placeTopics)
        val image: ImageView = itemView.findViewById(R.id.placeImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_place, parent, false)
        return PlaceViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val place = places[position]
        holder.title.text = place.title
        holder.address.text = place.address
        holder.cost.text = place.cost
        holder.topics.text = place.topics
        holder.image.setImageResource(place.imageResource)
    }

    override fun getItemCount() = places.size
}