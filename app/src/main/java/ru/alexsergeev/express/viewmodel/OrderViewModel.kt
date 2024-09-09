package ru.alexsergeev.express.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.play.integrity.internal.o
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.alexsergeev.express.dto.Options
import ru.alexsergeev.express.dto.Order
import ru.alexsergeev.express.dto.User
import ru.alexsergeev.express.repository.OrderRepository
import ru.alexsergeev.express.repository.OrderRepositoryImpl

class OrderViewModel(private val repository: OrderRepository = OrderRepositoryImpl()) :
    ViewModel() {

    private val orderMutable = MutableStateFlow(
        Order(
            from = "",
            to = "",
            time = "",
            tariff = "",
            user = User(name = "", number = ""),
            options = Options(babySeat = false, withAnimal = false),
        )
    )
    private val order: StateFlow<Order> = orderMutable

    suspend fun makeOrder(order: Order) {
        viewModelScope.launch {
            try {
                repository.makeOrder(order)
            } catch (e: Exception) {
                throw e
            }
        }
    }

    fun setOrder(order: Order) {
        try {
            viewModelScope.launch {
                repository.setOrder(order)
                orderMutable.update { order }
            }
        } catch (e: Exception) {
            throw e
        }
    }

    fun getOrder(): StateFlow<Order> {
        try {
            viewModelScope.launch {
                val order = repository.getOrder().first()
                orderMutable.update { order }
            }
            return order
        } catch (e: Exception) {
            throw e
        }
    }
}