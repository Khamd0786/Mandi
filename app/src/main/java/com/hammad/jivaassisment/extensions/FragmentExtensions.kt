package com.hammad.jivaassisment.extensions

import androidx.fragment.app.Fragment
import com.hammad.jivaassisment.utility.Debouncer

fun Fragment.executeDebounced(timeInMills: Long = 300, function: () -> Unit) {
    Debouncer.executeDebounced(lifecycle, timeInMills, function)
}