package com.example.a22_00

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.annotation.RequiresApi
import com.example.a22_00.DBHelper.DBHelper
import com.example.a22_00.Model.Activity
import com.example.a22_00.Model.CreateActivityListAdapter
import com.example.a22_00.Model.TempData
import com.example.a22_00.Model.Timetable
import java.time.LocalTime

class CreateActivities : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_activities)
        //val veiklos:ArrayList<com.example.a22_00.Model.Activity> =ArrayList<com.example.a22_00.Model.Activity>()
        (TempData.Data.data["Timetable"] as Timetable).activities = ArrayList<com.example.a22_00.Model.Activity>(
            arrayListOf(Activity("Activity 1", LocalTime.now(),60, Color.valueOf(Color.BLUE))))
        val adapter = CreateActivityListAdapter(this,(TempData.Data.data["Timetable"] as Timetable).activities as ArrayList<com.example.a22_00.Model.Activity>)
        val listView = findViewById<ListView>(R.id.CreateActivitiesListView)
        listView.adapter = adapter

        val addActivity = findViewById<Button>(R.id.AddActivity)
        addActivity.setOnClickListener {
            //((TempData.Data.data["Timetable"] as Timetable).activities as ArrayList).add(com.example.a22_00.Model.Activity())
            adapter.addActivity(com.example.a22_00.Model.Activity())
            print("the 'ADD ACTIVITY' button was clicked, hence, there are now ${(TempData.Data.data["Timetable"] as Timetable).activities.size}(${adapter.count}) activities for editing.\n")
            adapter.notifyDataSetChanged()
        }
        val done = findViewById<Button>(R.id.Done)
        done.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            if((TempData.Data.data["Timetable"] as Timetable).activities.size<1){
                //error "Please add activities"
                return@setOnClickListener
            }
            DBHelper(this.applicationContext).insertTimetable(TempData.Data.data["Timetable"] as Timetable)
            startActivity(intent)
        }
    }
}