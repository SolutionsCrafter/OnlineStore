// CategoryRepository.kt
package com.example.e_valtstore.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.e_valtstore.DataClass.Category
import com.google.firebase.database.*

class CategoryRepository {

    private val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Category")

    fun fetchCategories(): LiveData<List<Category>> {
        val categoryLiveData = MutableLiveData<List<Category>>()

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val categoryList = mutableListOf<Category>()
                for (data in snapshot.children) {
                    val category = data.getValue(Category::class.java)
                    category?.let {
                        categoryList.add(it)
                        Log.d("FirebaseData", "Loaded category: ${it.title}")
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
