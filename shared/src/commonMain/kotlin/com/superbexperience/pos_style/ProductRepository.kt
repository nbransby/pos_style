package com.superbexperience.pos_style

import kotlinx.coroutines.delay

internal class ProductRepository {
    suspend fun getProducts(): List<Product> {
        delay(500) // simulate network
        return listOf(
            Product("1", "Apple", 2.50),
            Product("2", "Banana", 0.99),
            Product("3", "Orange Juice", 1.29)
        )
    }
}
