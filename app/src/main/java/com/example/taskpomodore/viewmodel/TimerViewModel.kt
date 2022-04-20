package com.example.taskpomodore.viewmodel

import android.os.Build
import android.os.CountDownTimer
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.taskpomodore.model.Status
import com.example.taskpomodore.model.Timer
import java.time.Duration
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf


class TimerViewModel: ViewModel() {
    private val _viewState: MutableState<Timer> = mutableStateOf<Timer>(Timer())
    val viewState: State<Timer> = _viewState

    var countDown: CountDownTimer? = null

    @RequiresApi(Build.VERSION_CODES.O)
    fun startTime(duration: Duration) {
        countDown = object : CountDownTimer(duration.toMillis(), 10) {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onTick(seconds: Long) {
                _viewState.value = Timer(
                    timeDuration = Duration.ofMillis(seconds),
                    remainingTime = seconds,
                    status = Status.RUNNING,
                )
            }

            @RequiresApi(Build.VERSION_CODES.O)
            override fun onFinish() {
                _viewState.value = _viewState.value.copy(
                    timeDuration = Duration.ZERO,
                    status = Status.FINISHED,
                )
            }

        }
        countDown?.start()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun resetTimer() {
        countDown?.cancel()
        _viewState.value = _viewState.value!!.copy(
            status = Status.STARTED,
            timeDuration = Duration.ofMillis(30000),
        )
    }
}