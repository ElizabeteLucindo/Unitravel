package com.example.unitravel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestaurantAdapter(private val restaurants: List<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.restaurantName)
        val address: TextView = itemView.findViewById(R.id.restaurantAddress)
        val type: TextView = itemView.findViewById(R.id.restaurantType)
        val phone: TextView = itemView.findViewById(R.id.restaurantPhone)
        val hours: TextView = itemView.findViewById(R.id.restaurantHours)
        val image: ImageView = itemView.findViewById(R.id.restaurantImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.name.text = restaurant.name
        holder.address.text = restaurant.address
        holder.type.text = restaurant.type
        holder.phone.text = restaurant.phone
        holder.hours.text = restaurant.hours
        holder.image.setImageResource(restaurant.imageResource)
    }

    override fun getItemCount() = restaurants.size
}
