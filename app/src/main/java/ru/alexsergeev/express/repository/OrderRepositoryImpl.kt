package ru.alexsergeev.express.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.alexsergeev.express.api.OrderApi
import ru.alexsergeev.express.dto.Order
import ru.alexsergeev.express.errors.ApiError
import ru.alexsergeev.express.errors.UnknownError
import java.io.IOException

class OrderRepositoryImpl : OrderRepository {
    override suspend fun makeOrder(order: Order) {
        try {
            val response = OrderApi.retrofitService.makeOrder(order)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
        } catch (e: IOException) {
            throw ru.alexsergeev.express.errors.NetworkError
        } catch (e: Exception) {
            throw UnknownError
        }
    }
}