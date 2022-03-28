package com.example.taskpomodore.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.taskpomodore.model.Todo

@Composable
fun HomeScreen(navController: NavHostController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xff5F5FFF))) {
        Header(name = "Patryk") {

        }
    }
}

@Composable
fun ToDoList(todos: List<Todo>){
    Column() {

    }
}
@Composable
fun ToDoItem(todo: Todo){

}

@Composable
fun Header(name: String, onPomodoroStartClicked: () -> Unit){
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(14.dp)) {
        Icon(Icons.Default.Menu, "", tint = White )
        Divider(modifier = Modifier.heightIn(20.dp), color = Transparent)
        Text(
            "Welcome back, $name!",
            fontWeight = Bold,
            fontSize = 22.sp,
            color = White
        )
        Divider(modifier = Modifier.heightIn(15.dp), color = Transparent)
        //TODO timer
        Button(
            modifier = Modifier.padding(0.dp),
            onClick = onPomodoroStartClicked,
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
}


@Preview
@Composable
fun HeaderPreview(){
    Column() {
        Header(name = "Patryk") {

        }
    }
}