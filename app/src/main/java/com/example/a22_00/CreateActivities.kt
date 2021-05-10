package com.example.a22_00

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.a22_00.DBHelper.DBHelper
import com.example.a22_00.Model.Activity
import com.example.a22_00.Model.TempData
import com.example.a22_00.Model.Timetable

class CreateActivities : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_activities)
        val veiklos:ArrayList<com.example.a22_00.Model.Activity> =ArrayList<com.example.a22_00.Model.Activity>()

        val done = findViewById<Button>(R.id.Done)

        done.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            if(veiklos.size<1){
                //error "Please add activities"
                return@setOnClickListener
            }
            DBHelper(this.applicationContext).insertTimetable(Timetable(TempData.Data.data["Name"].toString(),TempData.Data.data["Description"].toString(),veiklos))
            startActivity(intent)
        }
    }
}