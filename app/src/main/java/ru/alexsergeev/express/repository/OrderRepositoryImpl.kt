package ru.alexsergeev.express.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.alexsergeev.express.api.OrderApi
import ru.alexsergeev.express.dto.Options
import ru.alexsergeev.express.dto.Order
import ru.alexsergeev.express.dto.User
import ru.alexsergeev.express.errors.ApiError
import ru.alexsergeev.express.errors.UnknownError
import java.io.IOException

class OrderRepositoryImpl : OrderRepository {

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
    override fun getOrder(): Flow<Order> = flow {
        val order = orderMutable.value
        emit(order)
    }

    override suspend fun setOrder(order: Order) {
        orderMutable.update { order }
    }

    override suspend fun makeOrder(order: Order) {
        try {
            val response = OrderApi.retrofitService.makeOrder(order)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
        } catch (e: IOException) {
            throw ru.alexsergeev.express.errors.NetworkError
        } catch (e: Exception) {
            e.printStackTrace()
            throw UnknownError
        }
    }
}