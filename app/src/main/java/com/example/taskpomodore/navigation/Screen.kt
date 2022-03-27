package com.example.taskpomodore.navigation

sealed class Screen (val route: String){
    object Boarding: Screen(route = "boarding_screen")
    object Home: Screen(route = "home_screen")
}