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
import kotlin.collections.ArrayList

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
        val week = Calendar.getInstance(TimeZone.getDefault()).get(Calendar.DAY_OF_WEEK)-1 /*monday - zero*/ -1
        val minutes = LocalTime.now().until(begining, ChronoUnit.MINUTES)
        //return LocalTime.now().until(begining, ChronoUnit.MINUTES)

        //isrenkam veiklos dienas
        var days:ArrayList<Int> = ArrayList()
        var x:Int = 0
        while(x<7){
        if(dayOfTheWeek.toInt().and(255-Math.pow(2.toDouble(),x.toDouble()).toInt()).toByte()==255.toByte()) days.add(x)
            x+=1
        }
        x=0
        while(x<days.size){
            days[x]-=week
            if(days[x]<0) days[x]+=7
            x++
        }
        var daysMinutes: Int? = days.minOrNull()
        println("there are $daysMinutes days and $minutes minutes till $name starts")
        return minutes + ((if(daysMinutes==null)0 else daysMinutes) * 1440)
    }
}