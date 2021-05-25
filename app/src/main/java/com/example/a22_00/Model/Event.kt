package com.example.a22_00.Model

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.function.Consumer
import java.util.HashSet;
import java.util.Set;

class Event {
    private val listeners: MutableSet<Consumer<Any>> = HashSet()

    fun addListener(listener: Consumer<Any>) {
        listeners.add(listener)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun broadcast(args: Any) {
        listeners.forEach(Consumer<Consumer<Any>> { x: Consumer<Any> -> x.accept(args) })
    }
}