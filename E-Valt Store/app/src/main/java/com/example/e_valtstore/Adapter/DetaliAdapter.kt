package com.example.e_valtstore.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_valtstore.R

class DetaliAdapter(private val imageUrls: List<String>) :
    RecyclerView.Adapter<DetaliAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgDetail: ImageView = itemView.findViewById(R.id.imgDetailSlider)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.detail_slider, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Glide.with(holder.imgDetail.context)
            .load(imageUrls[position])
            .into(holder.imgDetail)
    }

    override fun getItemCount(): Int = imageUrls.size
}
