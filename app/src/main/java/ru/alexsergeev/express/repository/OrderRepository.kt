package ru.alexsergeev.express.repository

import ru.alexsergeev.express.dto.Order

interface OrderRepository {
    fun makeOrder(order: Order, callback: MakeOrderCallback)
}

interface MakeOrderCallback {
    fun onSuccess(order: Unit?)
    fun onError(e: Exception)
}