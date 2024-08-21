package ru.alexsergeev.express.route

sealed class Route(val route: String) {
    data object Registration : Route(route = "registration")
    data object MainPage : Route(route = "main_screen/{name}") {
        fun passName(name: String): String {
            return this.route.replace(name, "Sasha")
        }
    }
    data object LeftMenu : Route(route = "left_menu/{name}") {
        fun passName(name: String): String {
            return this.route.replace(name, "Sasha")
        }
    }
    data object Loading : Route(route = "splash_screen")
}