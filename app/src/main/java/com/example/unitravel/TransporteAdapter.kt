package com.example.unitravel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TransporteAdapter(private val transportes: List<Transporte>) :
    RecyclerView.Adapter<TransporteAdapter.TransporteViewHolder>() {

    inner class TransporteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.transpName)
        val info: TextView = itemView.findViewById(R.id.transpInfo)
        val phone: TextView = itemView.findViewById(R.id.transpPhone)
        val hours: TextView = itemView.findViewById(R.id.transpHours)
        val cost: TextView = itemView.findViewById(R.id.transpCost)
        val image: ImageView = itemView.findViewById(R.id.transpImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransporteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_transp, parent, false)
        return TransporteViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransporteViewHolder, position: Int) {
        val transp = transportes[position]
        holder.title.text = transp.title
        holder.info.text = transp.info
        holder.cost.text = transp.cost
        holder.phone.text = transp.phone
        holder.hours.text = transp.hours
        holder.image.setImageResource(transp.imageResource)
    }

    override fun getItemCount() = transportes.size
}
