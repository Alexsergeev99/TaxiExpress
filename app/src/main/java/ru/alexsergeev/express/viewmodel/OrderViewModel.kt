package ru.alexsergeev.express.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import ru.alexsergeev.express.dto.Order
import ru.alexsergeev.express.repository.MakeOrderCallback
import ru.alexsergeev.express.repository.OrderRepository
import ru.alexsergeev.express.repository.OrderRepositoryImpl

class OrderViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: OrderRepository = OrderRepositoryImpl()
    fun makeOrder(order: Order) {
//        viewModelScope.launch {
//            try {
                repository.makeOrder(order, object : MakeOrderCallback {
                    override fun onSuccess(order: Unit?) {
                        Log.d("vm", "ok")
                    }
                    override fun onError(e: Exception) {
                        throw  e
                    }
                })
//            } catch (e: Exception) {
//                throw e
//            }
//        }
    }
}