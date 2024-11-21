package com.example.unitravel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RestaurantAdapter(private var restaurants: MutableList<Restaurant>) :
    RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.restaurantName)
        val address: TextView = itemView.findViewById(R.id.restaurantAddress)
        val type: TextView = itemView.findViewById(R.id.restaurantType)
        val hours: TextView = itemView.findViewById(R.id.restaurantHours)
        val phone: TextView = itemView.findViewById(R.id.restaurantPhone)
        val rate: TextView = itemView.findViewById(R.id.restaurantRate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurant = restaurants[position]
        holder.name.text = restaurant.name
        holder.address.text = "Endereço: ${restaurant.address}"
        holder.type.text = "Tipo de cozinha: ${restaurant.type}"
        holder.hours.text = "Horário de funcionamento: ${restaurant.hours}"
        holder.phone.text = "Telefone: ${restaurant.phone}"
        holder.rate.text = "Avalição: ${restaurant.rate}"
    }

    override fun getItemCount(): Int {
        return restaurants.size
    }

    fun setRestaurant(newRestaurants: List<Restaurant>) {
        this.restaurants.clear()
        this.restaurants.addAll(newRestaurants)
        notifyDataSetChanged()
    }

}
