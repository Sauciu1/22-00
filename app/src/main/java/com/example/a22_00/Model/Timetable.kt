package com.example.a22_00.Model

import android.media.audiofx.AudioEffect
import java.io.FileDescriptor

class Timetable {
    var  id: Int = 0
    var name: String? = null
    var description: String? = null
    var activities: List<com.example.a22_00.Model.Activity> = ArrayList<com.example.a22_00.Model.Activity>()
    constructor(){}
    constructor(id:Int, name: String,description:String,activities: List<com.example.a22_00.Model.Activity>){
        this.id = id
        this.name = name
        this.description = description
        this.activities = activities
    }
}