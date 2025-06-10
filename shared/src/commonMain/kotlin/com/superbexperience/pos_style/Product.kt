package com.superbexperience.pos_style

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val category: String? = null,
    val description: String? = null
)
