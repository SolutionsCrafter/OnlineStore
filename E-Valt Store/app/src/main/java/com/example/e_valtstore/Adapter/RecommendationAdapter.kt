package com.example.e_valtstore.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_valtstore.Activity.DetailsActivity
import com.example.e_valtstore.DataClass.Items
import com.example.e_valtstore.R

class RecommendationAdapter(private var items: List<Items>) : RecyclerView.Adapter<RecommendationAdapter.ItemViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgPic: ImageView = view.findViewById(R.id.imgMainPic)
        val imgLove: ImageView = view.findViewById(R.id.imgLove)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvRating: TextView = view.findViewById(R.id.tvRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_recommendation, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.tvPrice.text = "$${item.price}"
        holder.tvTitle.text = item.title
        holder.tvRating.text = item.rating.toString().substring(0, 3)
        Glide.with(holder.imgPic.context).load(item.picUrl[0]).into(holder.imgPic)

        // Set click listener
        if (position == 0){
            holder.itemView.setOnClickListener {
                val previousPosition = selectedPosition
                selectedPosition = holder.adapterPosition

                // Refresh both old and new items
                notifyItemChanged(previousPosition)
                notifyItemChanged(selectedPosition)

                // Navigate to DetailsActivity with selected item
                val context = holder.itemView.context
                val intent = Intent(context, DetailsActivity::class.java).apply {
                    putExtra("item", item)
                }
                context.startActivity(intent)
            }
        }else{
            holder.itemView.setOnClickListener(null)
        }

    }


    override fun getItemCount(): Int = items.size

    // Method to update the adapter's data
    fun updateData(newItems: List<Items>) {
        items = newItems
        notifyDataSetChanged()
    }
}
