package com.example.e_valtstore.DataClass

data class Items(
    val id: Int = 0,
    val description: String = "",
    val picUrl: List<String> = emptyList(),
    val price: Int = 0,
    val rating: Double = 0.0,
    val size: List<String> = emptyList(),
    val title: String = ""
){
    // Empty constructor needed for Firebase
    constructor() : this(0, "", emptyList(), 0, 0.0, emptyList(), "")
}
