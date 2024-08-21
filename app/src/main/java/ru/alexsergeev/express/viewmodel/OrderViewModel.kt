package ru.alexsergeev.express.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexsergeev.express.dto.Order
import ru.alexsergeev.express.repository.OrderRepository
import ru.alexsergeev.express.repository.OrderRepositoryImpl

class OrderViewModel(private val repository: OrderRepository = OrderRepositoryImpl()) :
    ViewModel() {

    suspend fun makeOrder(order: Order) {
        viewModelScope.launch {
            try {
                repository.makeOrder(order)
            } catch (e: Exception) {
                throw e
            }
        }
    }
}