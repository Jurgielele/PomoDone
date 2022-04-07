package com.example.taskpomodore.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskpomodore.utils.millisToMinSec
import kotlinx.coroutines.delay

@Composable
fun Header(name: String, pomodoroActive:Boolean, onPomodoroButtonClicked: () -> Unit){
    var pomodoroTime by remember{ mutableStateOf(0L) }
    val updateTime: (Boolean) -> Unit = remember(pomodoroActive){{
        if(!it){
            pomodoroTime = 1_500_000L
        }
    }}
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)
    ) {
        if(!pomodoroActive){
            Divider(modifier = Modifier.heightIn(20.dp), color = Color.Transparent)
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
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        color = Color.White
    )
    Divider(modifier = Modifier.heightIn(15.dp), color = Color.Transparent)
    Button(
        modifier = Modifier.padding(0.dp),
        onClick = onPomodoroButtonClicked,
        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    ) {
        Text(
            "Start pomodoro",
            fontWeight = FontWeight.Medium,
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
            fontWeight = FontWeight.Bold,
            fontSize = 64.sp,
            color = Color.White,
            lineHeight = 96.sp
        )
        Button(
            onClick = onPomodoroButtonClicked,
            shape = RoundedCornerShape(20.dp),
            colors =
            ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp),
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
                    fontWeight = FontWeight.Medium,
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