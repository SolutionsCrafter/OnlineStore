package com.example.e_valtstore.DataClass

import java.io.Serializable

data class Items(
    val id: Int = 0,
    val description: String = "",
    val picUrl: List<String> = emptyList(),
    val price: Int = 0,
    val rating: Double = 0.0,
    val size: List<String> = emptyList(),
    val title: String = ""
) : Serializable {
    constructor() : this(0, "", emptyList(), 0, 0.0, emptyList(), "")
}
