package ru.alexsergeev.express.repository

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.alexsergeev.express.api.OrderApi
import ru.alexsergeev.express.dto.Order

class OrderRepositoryImpl : OrderRepository {
    override fun makeOrder(order: Order, callback: MakeOrderCallback) {
//        try {
//            val response = OrderApi.retrofitService.makeOrder(order)
//            if (!response.isExecuted) {
////                throw ApiError(response.code(), response.message())
//                throw ru.alexsergeev.express.errors.NetworkError
//            }
////            val body = response.body() ?: throw ApiError(response.code(), response.message())
//        } catch (e: IOException) {
//            throw ru.alexsergeev.express.errors.NetworkError
//        } catch (e: Exception) {
//            throw UnknownError
//        }
        OrderApi.retrofitService.makeOrder(order)
            .enqueue(
                object : Callback<Unit?> {
//                    override fun onResponse(
//                        call: Call<Order?>?,
//                        response: Response<Order?>
//                    ) {
////                        if (!response.isSuccessful) {
////                            callback.onError(RuntimeException(response.message()))
////                            return
////                        }
//                        val responseBody = response.body() //?: throw Exception("body is null")
//
//                        if (responseBody != null) {
//                            callback.onSuccess(responseBody)
//                        }
//                    }
//
//                    override fun onFailure(call: Call<Order?>?, t: Throwable) {
//                        callback.onError(Exception(t))
//                    }

                    override fun onResponse(p0: Call<Unit?>, p1: Response<Unit?>) {
                        val responseBody = p1.body() //?: throw Exception("body is null")

                        if (responseBody != null) {
                            callback.onSuccess(responseBody)
                        }
                    }

                    override fun onFailure(p0: Call<Unit?>, p1: Throwable) {
                        callback.onError(Exception(p1))
                    }
                }
            )
    }
}