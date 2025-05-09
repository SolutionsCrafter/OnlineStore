package com.example.e_valtstore.DataClass

data class Recommendation(
    val id: Int = 0,
    val price: Int = 0,
    val rating: Double = 0.0,
    val title: String = ""
){
    // Empty constructor needed for Firebase
    constructor() : this(0, 0, 0.0, "")
}
