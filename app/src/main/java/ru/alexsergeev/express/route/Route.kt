package ru.alexsergeev.express.route

sealed class Route(val route: String) {
    object Registration : Route(route = "registration")
    object MainPage : Route(route = "main_screen/{name}") {
        fun passName(name: String): String {
            return this.route.replace(name, "Sasha")
        }
    }
    object LeftMenu : Route(route = "left_menu/{name}") {
        fun passName(name: String): String {
            return this.route.replace(name, "Sasha")
        }
    }
    object Loading : Route(route = "splash_screen")
}