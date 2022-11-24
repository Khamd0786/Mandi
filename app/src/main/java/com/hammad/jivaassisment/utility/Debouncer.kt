package com.hammad.jivaassisment.utility

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object Debouncer {
    private var job: Job? = null

    fun executeDebounced(
        lifecycle: Lifecycle,
        timeInMills: Long,
        function: () -> Unit
    ) {
        job?.cancel()
        job = lifecycle.coroutineScope.launch {
            delay(timeInMills)
            function()
        }
    }
}