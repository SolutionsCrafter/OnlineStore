package com.example.e_valtstore.Activity

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.e_valtstore.Adapter.CategoryAdapter
import com.example.e_valtstore.R
import com.example.e_valtstore.ViewModel.CategoryViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CategoryViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CategoryAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBar = findViewById(R.id.progressBarBrands)
        progressBar.visibility = ProgressBar.VISIBLE

        setupRecyclerView()
        setupImageSlider()
    }

    private fun setupRecyclerView() {
        // Initialize RecyclerView and LayoutManager
        recyclerView = findViewById(R.id.rvBrands)
        recyclerView.visibility = RecyclerView.GONE
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Attach an initially empty adapter to RecyclerView
        adapter = CategoryAdapter(emptyList())
        recyclerView.adapter = adapter
        Log.d("MainActivity", "Adapter attached to RecyclerView")

        // Set up ViewModel and observe LiveData
        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        viewModel.categories.observe(this) { categories ->
            Log.d("MainActivity", "Data fetched successfully, updating adapter")
            recyclerView.visibility = RecyclerView.VISIBLE
            progressBar.visibility = ProgressBar.GONE
            adapter.updateData(categories)
        }
    }

    private fun setupImageSlider() {
        val imageList = ArrayList<SlideModel>()
        val imageSlider = findViewById<ImageSlider>(R.id.image_slider)

        imageList.add(SlideModel(R.drawable.banner1))
        imageList.add(SlideModel(R.drawable.banner2))
        imageList.add(SlideModel(R.drawable.banner3))
        imageList.add(SlideModel(R.drawable.banner4))

        imageSlider.setImageList(imageList)
        imageSlider.setSlideAnimation(com.denzcoskun.imageslider.constants.AnimationTypes.DEPTH_SLIDE)
    }
}
