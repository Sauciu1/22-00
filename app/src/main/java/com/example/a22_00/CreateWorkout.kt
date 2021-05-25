package com.example.a22_00

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import com.example.a22_00.DBHelper.DBHelper
import com.example.a22_00.Model.TempData
import com.example.a22_00.Model.Timetable

class CreateWorkout : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_workout)


        val cancel = findViewById<Button>(R.id.CancelWorkout)
        cancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        val names/*: ArrayList<String>*/ = DBHelper(this.applicationContext).getAllTimetables().map {it.name}
        val savenext = findViewById<Button>(R.id.OpenActivities)
        val inputWN = findViewById<EditText>(R.id.InputOfWN)
        val inputWD = findViewById<EditText>(R.id.InputOfWD)
        savenext.setOnClickListener {
            if(inputWN.text.isNullOrEmpty()){
                //error that name cannot be empty
                return@setOnClickListener
            }
            if(names.contains(inputWN.text)){
                //error that such name was already taken
                return@setOnClickListener
            }
            val nameInput = inputWN.text.toString()
            val descInput = inputWD.text.toString()
            /*TempData.Data.data.clear()
            TempData.Data.data.put("Name",nameInput)
            TempData.Data.data.put("Description",descInput)*/
            val tb = Timetable()
            tb.name = nameInput
            tb.description = descInput
            TempData.Data.data.clear()
            TempData.Data.data.put("Timetable",tb)
            val intent = Intent(this, CreateActivities::class.java)
            startActivity(intent)
        }


    }
}