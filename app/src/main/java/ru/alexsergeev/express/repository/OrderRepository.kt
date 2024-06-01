package ru.alexsergeev.express.repository

import ru.alexsergeev.express.dto.Order

interface OrderRepository {
    suspend fun makeOrder(order: Order)
}

interface MakeOrderCallback {
    fun onSuccess(order: Unit?)
    fun onError(e: Exception)
}