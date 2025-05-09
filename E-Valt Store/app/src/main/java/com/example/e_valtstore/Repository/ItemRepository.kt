package com.example.e_valtstore.Repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.e_valtstore.DataClass.Items
import com.google.firebase.database.*

class ItemRepository {

    private val databaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("Items")

    fun fetchItems(): LiveData<List<Items>> {
        val itemLiveData = MutableLiveData<List<Items>>()

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val itemList = mutableListOf<Items>()
                for (data in snapshot.children) {
                    val item = data.getValue(Items::class.java)
                    item?.let {
                        itemList.add(it)
                        Log.d("FirebaseData", "Loaded item: ${it.title}")
                    }
                }
                itemLiveData.value = itemList
                Log.d("FirebaseData", "Total items loaded: ${itemList.size}")
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseData", "Error loading data: ${error.message}")
            }
        })

        return itemLiveData
    }
}
