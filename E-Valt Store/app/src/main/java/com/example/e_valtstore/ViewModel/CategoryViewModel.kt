package com.example.e_valtstore.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_valtstore.DataClass.Category
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CategoryViewModel : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>> = _categories

    private val database = FirebaseDatabase.getInstance()
    private val categoryRef = database.getReference("Category")

    init {
        Log.d("CategoryViewModel", "Initializing ViewModel and fetching categories")
        fetchCategories()
    }

    private fun fetchCategories() {
        Log.d("CategoryViewModel", "Fetching categories from Firebase")

        categoryRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val categoryList = mutableListOf<Category>()

                    for (categorySnapshot in snapshot.children) {
                        val category = categorySnapshot.getValue(Category::class.java)
                        category?.let {
                            Log.d("CategoryViewModel", "Category loaded: ${it.title}")
                            categoryList.add(it)
                        }
                    }

                    _categories.value = categoryList
                    Log.d("CategoryViewModel", "Categories loaded successfully: ${categoryList.size} items")
                } catch (e: Exception) {
                    Log.e("CategoryViewModel", "Error parsing categories: ${e.message}", e)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("CategoryViewModel", "Database error: ${error.message}", error.toException())
            }
        })
    }

    fun refreshData() {
        Log.d("CategoryViewModel", "Refreshing category data")
        fetchCategories()
    }
}