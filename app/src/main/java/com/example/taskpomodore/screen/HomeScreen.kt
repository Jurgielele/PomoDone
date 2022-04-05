package com.example.taskpomodore.screen

import android.text.format.Time
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.taskpomodore.model.Todo
import com.example.taskpomodore.utils.millisToMinSec
import kotlinx.coroutines.delay
import java.util.*
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun HomeScreen(navController: NavHostController){
    var pomodoroActive by remember{ mutableStateOf(false)}
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xff5F5FFF))) {
        Header(name = "Patryk", pomodoroActive = pomodoroActive) {
            pomodoroActive = !pomodoroActive
        }
    }
}



@Composable
fun ToDoItem(todo: Todo){

}

@Composable
fun Header(name: String, pomodoroActive:Boolean, onPomodoroButtonClicked: () -> Unit){
    var pomodoroTime by remember{ mutableStateOf(0L)}
    val updateTime: (Boolean) -> Unit = remember(pomodoroActive){{
        if(!it){
            pomodoroTime = 1_500_000L
        }
    }}
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)
        ) {
        Icon(Icons.Default.Menu, "", tint = White )
        if(!pomodoroActive){
            Divider(modifier = Modifier.heightIn(20.dp), color = Transparent)
        }
        updateTime(pomodoroActive)
        if(!pomodoroActive){
            WelcomeHeader(name, onPomodoroButtonClicked)
        }else{
            TimerHeader(pomodoroTime, pomodoroActive, onTick = {
                                                               pomodoroTime-= 1000
            }, onPomodoroButtonClicked)
        }
    }
}
@Composable
private fun WelcomeHeader(name: String, onPomodoroButtonClicked: () -> Unit) {
    Text(
        "Welcome back, $name!",
        fontWeight = Bold,
        fontSize = 22.sp,
        color = White
    )
    Divider(modifier = Modifier.heightIn(15.dp), color = Transparent)
    Button(
        modifier = Modifier.padding(0.dp),
        onClick = onPomodoroButtonClicked,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = White)
    ) {
        Text(
            "Start pomodoro",
            fontWeight = Medium,
            fontSize = 18.sp,
            color = Color(
                0xff5F5FFF
            )
        )
    }
}

@Composable
private fun TimerHeader(
    pomodoroTime: Long,
    pomodoroActive: Boolean,
    onTick: ()-> Unit,
    onPomodoroButtonClicked: () -> Unit
) {
    LaunchedEffect(key1 = pomodoroTime, key2 = pomodoroActive) {
        if (pomodoroTime > 0 && pomodoroActive) {
            delay(1000)
            onTick()
        }
    }
    Column(modifier= Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = millisToMinSec(pomodoroTime),
            fontWeight = Bold,
            fontSize = 64.sp,
            color = White,
            lineHeight = 96.sp
        )
        Button(
            onClick = onPomodoroButtonClicked,
            shape = RoundedCornerShape(20.dp),
            colors =
            ButtonDefaults.buttonColors(backgroundColor = White)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(15.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(Color(0xffFF8383))
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    "Stop pomodoro",
                    fontWeight = Medium,
                    fontSize = 18.sp,
                    color = Color(
                        0xff5F5FFF
                    )
                )
            }
        }
    }
}


@Preview
@Composable
fun HeaderPreview(){
    Column() {
        Header(name = "Patryk", true) {

        }
    }
}