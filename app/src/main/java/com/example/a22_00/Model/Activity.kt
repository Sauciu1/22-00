package com.example.a22_00.Model

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import java.lang.Integer.min
import java.lang.Math.abs
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
        var week = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DAY_OF_WEEK)-1
        if (week==0) week=7
        val minutes = LocalTime.now().until(begining, ChronoUnit.MINUTES)
        var savaite = (dayOfTheWeek).toInt()
        var difference =7
        for (each in 1..7){
            if(savaite%2==1){
               if(difference>abs (week-each)) difference=abs(each-week)
                if(week==7 && each==1)difference=1
            }

            savaite/=2
        }
        //return week.toLong()
        var grazink = (LocalTime.now().until(begining, ChronoUnit.MINUTES) + difference*24*60)


        return grazink




    }
}