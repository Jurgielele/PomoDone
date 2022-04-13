package com.example.taskpomodore

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.taskpomodore.navigation.SetupNavGraph
import com.example.taskpomodore.ui.theme.TaskPomodoreTheme
import com.example.taskpomodore.viewmodel.SplashViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalAnimationApi
@ExperimentalPagerApi
@OptIn(ExperimentalFoundationApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().setKeepOnScreenCondition{
            !splashViewModel.isLoading.value
        }
        setContent {
            TaskPomodoreTheme {
                val navController = rememberNavController()
                SetupNavGraph(
                    navController = navController,
                    startDestination = splashViewModel.startDestination.value
                )
            }
        }
    }

    @Inject
    lateinit var splashViewModel: SplashViewModel
}

