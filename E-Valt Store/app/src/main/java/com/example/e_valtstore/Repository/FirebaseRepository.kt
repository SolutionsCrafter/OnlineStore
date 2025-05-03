package com.example.e_valtstore.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseRepository {
    private val databaseReference = FirebaseDatabase.getInstance().getReference("Category")

    fun fetchCategories(): LiveData<List<com.example.e_valtstore.DataClass.Category>> {
        val categoriesLiveData = MutableLiveData<List<com.example.e_valtstore.DataClass.Category>>()

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val categories = snapshot.children.mapNotNull { it.getValue(com.example.e_valtstore.DataClass.Category::class.java) }
                categoriesLiveData.value = categories
                Log.d("Firebase","Category data fetched successful")
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error if needed
                Log.e("Firebase","Category data fetched unsuccessful")
            }
        })

        return categoriesLiveData
    }
}
