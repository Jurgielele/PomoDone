package com.example.taskpomodore.composables

import android.os.CountDownTimer
import android.util.Log
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskpomodore.model.Status
import com.example.taskpomodore.model.Status.FINISHED
import com.example.taskpomodore.utils.millisToMinSec
import com.example.taskpomodore.viewmodel.TimerViewModel
import kotlinx.coroutines.delay
import java.time.Duration

@Composable
fun Header(name: String, pomodoroActive:Boolean, onPomodoroButtonClicked: () -> Unit){
    val timerViewModel: TimerViewModel= TimerViewModel()
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)
    ) {
        if(!pomodoroActive){
            Divider(modifier = Modifier.heightIn(20.dp), color = Color.Transparent)
        }
        if(!pomodoroActive){
            WelcomeHeader(name){
                timerViewModel.startTime(Duration.ofSeconds(1500))
                onPomodoroButtonClicked()
            }
        }else{
            TimerHeader(
                timerViewModel = timerViewModel,
                pomodoroActive = pomodoroActive,
            ){
                timerViewModel.resetTimer()
                onPomodoroButtonClicked()
            }

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
    timerViewModel: TimerViewModel,
    pomodoroActive: Boolean,
    onPomodoroButtonClicked: () -> Unit
) {
    if(timerViewModel.viewState.value.status == Status.STARTED){
        timerViewModel.startTime(Duration.ofSeconds(1500))
    }
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Text(
                text = millisToMinSec(timerViewModel.viewState.value.remainingTime),
                fontWeight = FontWeight.Bold,
                fontSize = 64.sp,
                color = Color.White,
                lineHeight = 96.sp
            )
            Log.d("TimerCheck", millisToMinSec(timerViewModel.viewState.value.remainingTime))
            Button(
                onClick = {
                    onPomodoroButtonClicked()
                    if (pomodoroActive) timerViewModel.resetTimer()
                },
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