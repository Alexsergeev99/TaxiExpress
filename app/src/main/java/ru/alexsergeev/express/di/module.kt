package ru.alexsergeev.express.di

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import ru.alexsergeev.express.repository.OrderRepository
import ru.alexsergeev.express.repository.OrderRepositoryImpl
import ru.alexsergeev.express.viewmodel.OrderViewModel

val module = module {

    viewModelOf(::OrderViewModel)
    singleOf(::OrderRepositoryImpl) bind OrderRepository::class
}