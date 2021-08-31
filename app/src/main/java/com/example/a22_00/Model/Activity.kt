package com.example.a22_00.Model

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import java.sql.Time
import java.time.LocalTime
import java.time.temporal.ChronoUnit
import java.time.temporal.Temporal
import java.time.temporal.TemporalUnit
import java.util.*

class Activity {
    var  id: Int = Int.MAX_VALUE
    var name: String = ""
    @RequiresApi(Build.VERSION_CODES.O)
    var begining: LocalTime = LocalTime.now()
    var duration: Long = 60
    @RequiresApi(Build.VERSION_CODES.O)
    var color: Color? = Color.valueOf(0.5f,0.5f,0.5f)
    var dayOfTheWeek: Byte = 0.toByte()

    constructor(){}
    constructor( name: String,begining: LocalTime, durationInMinutes: Long, color: Color, dayOfTheWeek: Byte){
        this.id = id
        this.name = name
        this.begining = begining
        this.duration = durationInMinutes
        this.color = color
        this.dayOfTheWeek = dayOfTheWeek
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun minutesTillStart(): Long{
        val week = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DAY_OF_WEEK)-1
        val minutes = LocalTime.now().until(begining, ChronoUnit.MINUTES)
        dayOfTheWeek.toInt()

    }
}