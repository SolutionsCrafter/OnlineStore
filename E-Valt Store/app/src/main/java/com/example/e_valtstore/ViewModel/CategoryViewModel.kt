// CategoryViewModel.kt
package com.example.e_valtstore.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.e_valtstore.DataClass.Category
import com.example.e_valtstore.Repository.CategoryRepository

class CategoryViewModel : ViewModel() {

    private val repository = CategoryRepository()

    // Expose LiveData from repository
    val categories: LiveData<List<Category>> = repository.fetchCategories()
}
