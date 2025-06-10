package com.superbexperience.pos_style

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn

class ProductListViewModel() : CoroutineScope {

    override val coroutineContext = Job()

    enum class SortBy(internal val comparator: Comparator<Product>) {
        Name(compareBy { it.name }),
        Price(compareBy { it.price }),
    }

    private val repo = ProductRepository()

    val sortBy = MutableStateFlow(SortBy.Name)

    val products: StateFlow<List<Product>> = flow { emit(repo.getProducts()) }
        .combine(sortBy) { products, sortBy -> products.sortedWith(sortBy.comparator) }
        .stateIn(this, SharingStarted.Lazily, emptyList())

}