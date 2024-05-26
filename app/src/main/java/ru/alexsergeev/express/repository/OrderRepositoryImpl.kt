package ru.alexsergeev.express.repository

import ru.alexsergeev.express.api.OrderApi
import ru.alexsergeev.express.dto.Order
import ru.alexsergeev.express.errors.UnknownError
import java.io.IOException

class OrderRepositoryImpl : OrderRepository {
    override suspend fun makeOrder(order: Order) {
        try {
            val response = OrderApi.retrofitService.makeOrder(order)
            if (!response.isExecuted) {
//                throw ApiError(response.code(), response.message())
                throw ru.alexsergeev.express.errors.NetworkError
            }
//            val body = response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: IOException) {
            throw ru.alexsergeev.express.errors.NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}