package com.example.e_valtstore.Activity

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.models.SlideModel
import com.example.e_valtstore.Adapter.CategoryAdapter
import com.example.e_valtstore.Adapter.RecommendationAdapter
import com.example.e_valtstore.R
import com.example.e_valtstore.ViewModel.CategoryViewModel
import com.example.e_valtstore.ViewModel.ItemViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var itemViewModel: ItemViewModel
    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var recyclerViewRecommendation: RecyclerView
    private lateinit var adapterCategory: CategoryAdapter
    private lateinit var adapterRecommendation: RecommendationAdapter
    private lateinit var progressBarBrand: ProgressBar
    private lateinit var progressBarRecommend: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progressBarBrand = findViewById(R.id.progressBarBrands)
        progressBarRecommend = findViewById(R.id.progressBarRecommend)
        progressBarBrand.visibility = ProgressBar.VISIBLE
        progressBarRecommend.visibility = ProgressBar.VISIBLE


        setupCategoryRecyclerView()
        setupRecommendationRecyclerView()
        setupImageSlider()

    }

    private fun setupCategoryRecyclerView() {
        // Initialize RecyclerView and LayoutManager
        recyclerViewCategory = findViewById(R.id.rvBrands)
        recyclerViewCategory.visibility = RecyclerView.GONE
        recyclerViewCategory.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        // Attach an initially empty adapter to RecyclerView
        adapterCategory = CategoryAdapter(emptyList())
        recyclerViewCategory.adapter = adapterCategory
        Log.d("MainActivity", "Adapter attached to RecyclerView")

        // Set up ViewModel and observe LiveData
        categoryViewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        categoryViewModel.categories.observe(this) { categories ->
            Log.d("MainActivity", "Category data fetched successfully, updating adapter: ${categories.size}")
            recyclerViewCategory.visibility = RecyclerView.VISIBLE
            progressBarBrand.visibility = ProgressBar.GONE
            adapterCategory.updateData(categories)
        }
    }

    private fun setupRecommendationRecyclerView() {
        // Initialize RecyclerView and LayoutManager
        recyclerViewRecommendation = findViewById(R.id.viewPopuler)
        recyclerViewRecommendation.layoutManager = GridLayoutManager(this, 2, RecyclerView.VERTICAL, false)
        recyclerViewRecommendation.visibility = RecyclerView.GONE

        // Attach an initially empty adapter to RecyclerView
        adapterRecommendation = RecommendationAdapter(emptyList())
        recyclerViewRecommendation.adapter = adapterRecommendation
        Log.d("MainActivity", "Adapter attached to RecyclerView")

        // Set up ViewModel and observe LiveData
        itemViewModel = ViewModelProvider(this)[ItemViewModel::class.java]
        itemViewModel.items.observe(this) { items ->
            Log.d("MainActivity", "Item data fetched successfully, updating adapter: ${items.size}")
            recyclerViewRecommendation.visibility = RecyclerView.VISIBLE
            progressBarRecommend.visibility = ProgressBar.GONE
            adapterRecommendation.updateData(items)

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
