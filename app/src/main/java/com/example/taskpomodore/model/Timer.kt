package com.example.taskpomodore.model

import java.time.Duration

data class Timer(
    val timeDuration: Duration = Duration.ofSeconds(1500),
    val remainingTime: Long = timeDuration.toMillis(),
    val status: Status = Status.STARTED
)
enum class Status {
    STARTED,
    RUNNING,
    FINISHED
}