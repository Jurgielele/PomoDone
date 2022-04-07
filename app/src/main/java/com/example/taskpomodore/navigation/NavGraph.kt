package com.example.taskpomodore.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.taskpomodore.screen.HomeScreen
import com.example.taskpomodore.screen.InfoScreen
import com.example.taskpomodore.screen.OnBoardingScreen
import com.google.accompanist.pager.ExperimentalPagerApi


@ExperimentalFoundationApi
@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(navController: NavHostController, startDestination: String){
    NavHost(navController = navController,
        startDestination = startDestination
    ){
        composable(route = Screen.Boarding.route){
            OnBoardingScreen(navController = navController)
        }
        composable(route = Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Info.route){
            InfoScreen(navController = navController)
        }
    }
}