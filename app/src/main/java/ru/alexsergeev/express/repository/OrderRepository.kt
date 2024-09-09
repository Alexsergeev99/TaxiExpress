package ru.alexsergeev.express.repository

import kotlinx.coroutines.flow.Flow
import ru.alexsergeev.express.dto.Order

interface OrderRepository {
    suspend fun makeOrder(order: Order)

    fun getOrder(): Flow<Order>

    suspend fun setOrder(order: Order)
}
