package com.example.taskpomodore.utils

fun millisToMinSec(milliseconds: Long): String{
    val minutes = milliseconds/1000/60
    val seconds = milliseconds/1000 % 60
    val m = if (minutes < 10) "0$minutes" else minutes
    val s = if(seconds <10) "0$seconds" else seconds
    return("$m:$s")
}
