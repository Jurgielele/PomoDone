package com.example.taskpomodore.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.taskpomodore.model.Todo
import com.example.taskpomodore.viewmodel.TodosViewModel

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun ToDoList(todos: List<Todo>, todosViewModel: TodosViewModel = hiltViewModel()){
    Column(modifier = Modifier.padding(start = 24.dp, top = 16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "To-do List",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                lineHeight = 27.sp,
                fontSize = 18.sp
            )
            Text(
                text = "Clear completed tasks!",
                color = Color.Gray,
                lineHeight = 27.sp,
                fontSize = 14.sp,
                modifier = Modifier.padding(end = 8.dp).clickable {
                    todos.forEach { it ->
                        if(it.completed) {
                            todosViewModel.deleteTodo(it)
                        }
                    }
                }
            )
        }
        if(todosViewModel.addTodoRowActive.value){
            TodoAddRow(showKeyboard = todosViewModel.addTodoRowActive.value){
                todosViewModel.addTodo(Todo(task = it))
                todosViewModel.addTodoRowActive.value = false
            }
        }
            LazyColumn{
                items(todos.sortedBy { it.entryDate }){todo ->
                    ToDoItemRow(todo = todo, onTodoDelete = {todosViewModel.deleteTodo(todo)},
                        onTodoUpdate = { todosViewModel.updateTodo(it)})
                }
            }
    }
}
@ExperimentalComposeUiApi
@Composable
fun TodoAddRow(keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current, showKeyboard: Boolean, onItemAdd:(String) -> Unit){
    var todo by remember{ mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }


    LaunchedEffect(showKeyboard) {
        focusRequester.requestFocus()
    }
    OutlinedTextField(
        value = todo,
        onValueChange = { todo = it },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            backgroundColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        ),
        placeholder = { Text("Your todo") },
        keyboardOptions = KeyboardOptions.Default
            .copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            onItemAdd(todo)
            keyboardController?.hide()
        }),
        modifier = Modifier.focusRequester(focusRequester = focusRequester)
    )
}

@OptIn(ExperimentalMaterialApi::class)
@ExperimentalFoundationApi
@Composable
fun ToDoItemRow(todo: Todo, onTodoDelete:(Todo) -> Unit,
                onTodoUpdate:(Todo)-> Unit){
    var checked by remember {
        mutableStateOf(todo.completed)
    }
    Row(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .combinedClickable(
                onClick = {},
                onLongClick = {
                    onTodoDelete(todo)
                },
            ),
        verticalAlignment = Alignment.CenterVertically) {
        CompositionLocalProvider(LocalMinimumTouchTargetEnforcement provides false) {
            Checkbox(checked = todo.completed,
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xff5F5FFF),
                    uncheckedColor = Color(0xffD9D9D9)
                ),
                onCheckedChange = {
                    checked = !checked
                    onTodoUpdate(todo.copy(completed = checked))} )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = todo.task,
            fontSize = 16.sp,
            lineHeight = 23.sp,
            color = if (checked) Color.Gray else Color.Black,
            textDecoration = if(checked) TextDecoration.LineThrough else TextDecoration.None
        )
    }
}
