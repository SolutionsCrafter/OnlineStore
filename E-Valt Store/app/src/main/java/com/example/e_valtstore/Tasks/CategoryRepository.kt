package com.example.e_valtstore.Tasks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.e_valtstore.DataClass.Category
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

// CategoryRepository.kt
class CategoryRepository {
    private val databaseRef = FirebaseDatabase.getInstance().getReference("Category")

    fun fetchCategories(): LiveData<List<Category>> {
        val categoryLiveData = MutableLiveData<List<Category>>()

        // Inside CategoryRepository.kt
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val categoryList = mutableListOf<Category>()
                for (data in snapshot.children) {
                    val category = data.getValue(Category::class.java)
                    if (category != null) {
                        categoryList.add(category)
                        Log.d("FirebaseData", "Loaded category: ${category.title}")
                    }
                }
                categoryLiveData.value = categoryList
                Log.d("FirebaseData", "Total categories loaded: ${categoryList.size}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseData", "Error loading data: ${error.message}")
            }
        })
        return categoryLiveData
    }
}
