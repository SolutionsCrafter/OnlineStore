package com.example.e_valtstore.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.e_valtstore.DataClass.Items
import com.example.e_valtstore.DataClass.Recommendation
import com.example.e_valtstore.Repository.ItemRepository

class ItemViewModel: ViewModel() {

    private val repository = ItemRepository()

    val items: LiveData<List<Items>> = repository.fetchItems()

}