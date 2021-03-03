package com.example.bike.riders.feature.main.view

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.bike.riders.R
import com.example.bike.riders.feature.main.api.Bike

class NetworkAdapter(val context: Context, val bike: List<Bike>) : RecyclerView.Adapter<NetworkAdapter.NetworkViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NetworkAdapter.NetworkViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view, parent, false)
        return NetworkViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bike.size
    }

    override fun onBindViewHolder(holder: NetworkAdapter.NetworkViewHolder, position: Int) {
        val bikes = bike[position]
        holder.bikeId.text = bikes.id
        holder.bikeName.text = bikes.name
        holder.bikeCity.text = bikes.location.city
        holder.bikeCountry.text = bikes.location.country
        if (position % 2 == 0) {
            holder.container.setBackgroundColor(ContextCompat.getColor(context, R.color.cardcolor1))
        } else {
            holder.container.setBackgroundColor(ContextCompat.getColor(context, R.color.cardcolor2))
        }
    }

    class NetworkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var bikeId = itemView.findViewById<TextView>(R.id.bikeIdtv)
        var bikeName = itemView.findViewById<TextView>(R.id.bikeNametv)
        var bikeCountry = itemView.findViewById<TextView>(R.id.bikeCountrytv)
        var bikeCity = itemView.findViewById<TextView>(R.id.bikeCitytv)
        var container = itemView.findViewById<LinearLayout>(R.id.card_view)

    }
}