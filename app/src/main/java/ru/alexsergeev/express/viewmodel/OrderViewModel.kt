package ru.alexsergeev.express.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ru.alexsergeev.express.dto.Order
import ru.alexsergeev.express.repository.MakeOrderCallback
import ru.alexsergeev.express.repository.OrderRepository
import ru.alexsergeev.express.repository.OrderRepositoryImpl

class OrderViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: OrderRepository = OrderRepositoryImpl()
    suspend fun makeOrder(order: Order) {
        viewModelScope.launch {
            try {
                repository.makeOrder(order)
                Log.d("vm", "ok")
            } catch (e: Exception) {
                throw e
            }
        }
    }
}