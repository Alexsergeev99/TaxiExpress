package ru.alexsergeev.express.dto

data class Order(
    val from: String,
    val to: String,
    val time: String,
    val tariff: String,
    var countPassengers: Int = 1,
    val comment: String? = null,
    var user: User,
    val options: Options
)

data class User(
    val name: String,
    val number: String,
)

data class Options(
    val babySeat: Boolean = false,
    val withAnimal: Boolean = false
)

