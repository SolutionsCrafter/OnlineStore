package com.example.e_valtstore.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.e_valtstore.DataClass.Category
import com.example.e_valtstore.R

class CategoryAdapter(private var categories: List<Category>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    inner class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageView: ImageView = view.findViewById(R.id.pic)
        val titleView: TextView = view.findViewById(R.id.pic_title)
        val categoryLayout: View = view.findViewById(R.id.categoryLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_brands, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.titleView.text = category.title

        // Toggle title visibility and background color
        if (position == selectedPosition) {
            holder.titleView.visibility = View.VISIBLE
            holder.categoryLayout.setBackgroundResource(R.drawable.category_highlight_bg)
            holder.imageView.setColorFilter(holder.itemView.context.getColor(R.color.white))
            holder.imageView.setBackgroundColor(holder.itemView.context.getColor(R.color.purple))
            holder.titleView.setTextColor(holder.itemView.context.getColor(R.color.white))
            holder.titleView.setBackgroundColor(holder.itemView.context.getColor(R.color.purple))
        } else {
            holder.titleView.visibility = View.GONE
            holder.imageView.visibility = View.VISIBLE
            holder.imageView.setColorFilter(holder.itemView.context.getColor(R.color.black))
            holder.categoryLayout.setBackgroundResource(R.drawable.gray_bg)
            holder.imageView.setBackgroundResource(R.drawable.gray_bg)
        }

        // Load image
        Glide.with(holder.imageView.context).load(category.picUrl).into(holder.imageView)

        // Set click listener
        holder.itemView.setOnClickListener {
            val previousPosition = selectedPosition
            selectedPosition = holder.adapterPosition

            // Refresh both old and new items
            notifyItemChanged(previousPosition)
            notifyItemChanged(selectedPosition)
        }
    }


    override fun getItemCount(): Int = categories.size

    // Method to update the adapter's data
    fun updateData(newCategories: List<Category>) {
        categories = newCategories
        notifyDataSetChanged()
    }
}
