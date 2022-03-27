package com.example.taskpomodore.utils


sealed class OnBoardingPage(
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        title = "Hi, it’s looks like you new here!",
        description = ""
    )

    object Second : OnBoardingPage(
        title = "Hi, it’s looks like you new here!",
        description = "To start the configuration we only need your name"
    )

    object Third : OnBoardingPage(
        title = "Hi, ",
        description = "Welcome to Pomotask"
    )
}