package com.example.taskpomodore.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.taskpomodore.navigation.Screen
import com.example.taskpomodore.utils.OnBoardingPage
import com.example.taskpomodore.viewmodel.OnBoardingViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun OnBoardingScreen(
    navController: NavHostController,
    onBoardingViewModel : OnBoardingViewModel = hiltViewModel()){

    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
    )

    val pagerState = rememberPagerState()


    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xFF4040CA))) {
        HorizontalPager (
            modifier = Modifier.weight(10f),
            count = 2,
            state = pagerState,
            verticalAlignment = Alignment.Top
            ){position ->
            PagerScreen(
                onBoardingPage = pages[position],
                name = onBoardingViewModel.name.value,
                onTextChange = {onBoardingViewModel.name.value = it},
                onNextClicked = {
                    onBoardingViewModel.saveOnBoardingState(true)
                    onBoardingViewModel.saveUsername(name = onBoardingViewModel.name.value)
                    navController.popBackStack()
                    navController.navigate(Screen.Home.route)}
            )
        }

        HorizontalPagerIndicator(
            modifier = Modifier
                .align(CenterHorizontally)
                .weight(1f),
                pagerState = pagerState,
            )
    }
}

//PAGES
@Composable
fun PagerScreen(
    onBoardingPage: OnBoardingPage,
    name: String,
    onTextChange: (String) -> Unit,
    onNextClicked: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF4040CA)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 20.dp),
            text = onBoardingPage.title,
            fontSize = 40.sp,
            color = (if(onBoardingPage.description.isEmpty()) Color.White else Color.White.copy(alpha = 0.4f, )),
            fontWeight = FontWeight.Bold
        )

        if(onBoardingPage.description.isNotEmpty()){
            Column(modifier = Modifier.weight(7f)) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 40.dp)
                        .padding(top = 20.dp),
                    text = onBoardingPage.description,
                    fontSize = 40.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                TextField(
                    value = name,
                    onValueChange = onTextChange,
                    shape = RoundedCornerShape(20.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    maxLines = 1,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                )
                AnimatedVisibility(visible = name.isNotEmpty()) {
                    Column() {
                        Divider(modifier = Modifier.fillMaxHeight(0.2f), color = Color.Transparent)
                        Button(
                            onClick = onNextClicked,
                            shape = CircleShape,
                            modifier = Modifier
                                .clip(CircleShape)
                                .align(CenterHorizontally),
                            colors = ButtonDefaults.buttonColors(
                                backgroundColor = Color.White
                            ),
                        ) {
                            Text(
                                text = ">",
                                color = Color(0xFF4040CA),
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        }
                    }
                }
            }
        }
    }
}
//@Composable
//@Preview(showBackground = true)
//fun FirstOnBoardingScreenPreview(){
//    Column(modifier = Modifier.fillMaxSize()) {
//        PagerScreen(onBoardingPage = OnBoardingPage.First)
//    }
//}
//@Composable
//@Preview(showBackground = true)
//fun SecondOnBoardingScreenPreview(){
//    Column(modifier = Modifier.fillMaxSize()) {
//        PagerScreen(onBoardingPage = OnBoardingPage.Second)
//    }
//}