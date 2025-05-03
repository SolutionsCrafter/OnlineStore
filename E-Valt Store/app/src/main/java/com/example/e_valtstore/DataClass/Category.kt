package com.example.e_valtstore.DataClass

data class Category(
    val id: Int = 0,
    val title: String = "",
    val picUrl: String = ""
) {
    // Empty constructor needed for Firebase
    constructor() : this(0, "", "")
}