package com.example.taskpomodore.screen

import android.text.format.Time
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.taskpomodore.composables.Header
import com.example.taskpomodore.composables.ToDoList
import com.example.taskpomodore.model.Todo
import com.example.taskpomodore.utils.millisToMinSec
import com.example.taskpomodore.viewmodel.TodosViewModel
import kotlinx.coroutines.delay
import java.util.*
import kotlin.time.Duration.Companion.milliseconds

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavHostController, todosViewModel: TodosViewModel = hiltViewModel()){
    var pomodoroActive by remember{ mutableStateOf(false)}
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        backgroundColor = (Color(0xff5F5FFF)),
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    todosViewModel.addTodoRowActive.value = !todosViewModel.addTodoRowActive.value},
                backgroundColor = Color(0xff5F5FFF),
                contentColor = White
            ) {
                Text("+", fontSize = 30.sp, fontWeight = Bold)
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column() {
            Header(name = "Patryk", pomodoroActive = pomodoroActive) {
                pomodoroActive = !pomodoroActive
            }
            Box(modifier = Modifier
                .padding(bottom = 12.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(White)
                .fillMaxSize()
            ){
                Column() {
                    ToDoList(todosViewModel.todoList.collectAsState().value)
                }
            }
        }
    }
}


