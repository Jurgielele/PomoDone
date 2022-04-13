package com.example.taskpomodore.screen

import android.graphics.Paint
import android.graphics.drawable.Icon
import android.text.format.Time
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.semantics.SemanticsProperties.ImeAction
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.font.FontWeight.Companion.Medium
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.taskpomodore.composables.Header
import com.example.taskpomodore.composables.ToDoList
import com.example.taskpomodore.model.Todo
import com.example.taskpomodore.navigation.Screen
import com.example.taskpomodore.utils.millisToMinSec
import com.example.taskpomodore.viewmodel.TodosViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants
import java.util.*
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.milliseconds

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(navController: NavHostController, todosViewModel: TodosViewModel = hiltViewModel()){
    var pomodoroActive by remember{ mutableStateOf(false)}
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val configuration = LocalConfiguration.current
    val screenDensity = configuration.densityDpi / 160f
    val screenHeight = (configuration.screenHeightDp.toFloat() * screenDensity).toInt()
    val screenWidth = (configuration.screenWidthDp.toFloat() * screenDensity * 0.6).toInt()
    var activeItem by remember { mutableStateOf("")}
    var infoSelected by remember{ mutableStateOf(false)}
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState,
        backgroundColor = if(activeItem == "Info") White else Color(0xff5F5FFF),
        drawerContent = { DrawerContent(
            "Patryk Jurgielewicz",
            activeItem,
            onActiveItemChange = { activeItem = it },
            scaffoldState
        )},
        drawerShape = customShape(screenWidth, screenHeight),
        floatingActionButton = {
            if(activeItem == "List" || activeItem.isEmpty()){
                FloatingActionButton(
                    onClick = {
                        todosViewModel.addTodoRowActive.value = !todosViewModel.addTodoRowActive.value},
                    backgroundColor = Color(0xff5F5FFF),
                    contentColor = White
                ) {
                    Text("+", fontSize = 30.sp, fontWeight = Bold)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) {
        Column() {
            Icon(
                Icons.Default.Menu,
                "",
                tint = if(activeItem == "Info") Black else White,
                modifier = Modifier
                    .padding(start = 12.dp, top = 12.dp)
                    .clickable {
                        scope.launch {
                            scaffoldState.drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }
            )
            if(activeItem == "List" || activeItem.isEmpty()){
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
            }else{
                InfoScreen(navController = navController)
            }
        }
    }
}

@Composable
fun DrawerContent(username: String = "Patryk Jurgielewicz",activeItem: String, onActiveItemChange: (String) -> Unit, scaffoldState: ScaffoldState){
    val name = username.split("\\s".toRegex()).toTypedArray()
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color(0xff5F5FFF))
        .padding(24.dp)
    ) {
        Spacer(modifier = Modifier.fillMaxHeight(0.04f))
        Row(modifier = Modifier.fillMaxWidth(0.6f), horizontalArrangement = Arrangement.End){
            Icon(Icons.Default.ArrowBack, "", tint = White)
        }
        name.forEach {
            Text(text = it, color = White, fontSize = 30.sp, lineHeight = 40.sp, fontWeight = Bold,)
        }
        DrawerItem(
            name = "List",
            isActive = (activeItem == "List") || activeItem.isEmpty(),
            icon = Icons.Default.Check,
        ) {
            scope.launch {
                scaffoldState.drawerState.apply {
                    if (isClosed) open() else close()
                }
            }
            onActiveItemChange("List")
        }
        DrawerItem(
            name = "Info",
            isActive = (activeItem == "Info"),
            icon = Icons.Default.Info
        ) {
            scope.launch {
                scaffoldState.drawerState.apply {
                    if(isOpen) close()
                }
            }
            onActiveItemChange("Info")
        }
    }
}
@Composable
fun DrawerItem(name: String, icon: ImageVector, isActive:Boolean, onClick: () -> Unit){
    Surface(shape = RoundedCornerShape(40), modifier = Modifier.background(Transparent), color = Transparent) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(
                if (isActive) White else Transparent
            )
            .padding(vertical = 8.dp)
            .padding(end = 10.dp)
            .clickable {
                onClick()
            },
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = icon,
                "",
                modifier = Modifier.padding(10.dp),
                tint = if(isActive) Color(0xff5F5FFF) else White
            )
            Text(name, fontSize = 16.sp, lineHeight = 23.sp, fontWeight = Medium,
                color = if(isActive) Color(0xff5F5FFF) else White)
        }
    }
}

fun customShape(screenWidth: Int, screenHeight: Int) =  object : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Rectangle(Rect(0f,0f,screenWidth.toFloat(), screenHeight.toFloat()))
    }
}