package com.example.a22_00

import android.widget.Toast
import android.widget.CompoundButton
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.a22_00.DBHelper.DBHelper
import com.example.a22_00.Model.Activity
import com.example.a22_00.Model.CreateActivityListAdapter
import com.example.a22_00.Model.TempData
import com.example.a22_00.Model.Timetable
import java.time.LocalTime






class CreateActivities : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = DBHelper(applicationContext)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_activities)
        //val veiklos:ArrayList<com.example.a22_00.Model.Activity> =ArrayList<com.example.a22_00.Model.Activity>()
        //(TempData.Data.data["Timetable"] as Timetable).activities = ArrayList<com.example.a22_00.Model.Activity>(
          //  arrayListOf(Activity("Activity 1", LocalTime.now(),60, Color.valueOf(Color.BLUE)),Activity("Activity 2", LocalTime.now(),60, Color.valueOf(Color.BLUE))))
        TempData.Data.data["Activities"] = ArrayList<com.example.a22_00.Model.Activity>()

        //TempData.Data.data.put("ActivityCreation",ArrayList<HashMap<String,View>>())
        //variantas

        //var adapter = CreateActivityListAdapter(this,(TempData.Data.data["Timetable"] as Timetable).activities as ArrayList<com.example.a22_00.Model.Activity>)
        val listView = findViewById<ListView>(R.id.CreateActivitiesListView)
        //listView.adapter = adapter
        listView.adapter = CreateActivityListAdapter(this,TempData.Data.data["Activities"] as ArrayList<com.example.a22_00.Model.Activity>)


        ((TempData.Data.data["Activities"]  as ArrayList<com.example.a22_00.Model.Activity>).add(Activity()))
        listView.adapter = CreateActivityListAdapter(this,TempData.Data.data["Activities"] as ArrayList<com.example.a22_00.Model.Activity>)











        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            //Toast.makeText(applicationContext,
               // android.R.string.no, Toast.LENGTH_SHORT).show()
        }
            val done = findViewById<Button>(R.id.Done)
        done.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            if((TempData.Data.data["Activities"] as ArrayList<com.example.a22_00.Model.Activity>).size<1){
                //error "Please add activities"
                return@setOnClickListener
            }
            val t = TempData.Data.data["Activities"] as ArrayList<com.example.a22_00.Model.Activity>
            if(t.find { it.name==null||it.name=="" }==null){
                t.forEach({
                    if(it.id==Int.MAX_VALUE)db.insertActivity(it)
                })
                db.close()
                startActivity(intent)
            }
            else{
                db.close()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error")
                builder.setMessage("All activities should be named")
                builder.setPositiveButton("OK",positiveButtonClick)
                builder.show()
            }
        }
        val cancel = findViewById<Button>(R.id.cancel)
        cancel.setOnClickListener {
            //val intent = this.parentActivityIntent//Intent(this, CreateWorkout::class.java)
            TempData.Data.data["Activities"]=null
            db.close()
            this.finish()
            //startActivity(intent)
        }
        }
    /*override fun onPause() {

        // hide the keyboard in order to avoid getTextBeforeCursor on inactive InputConnection
        val inputMethodManager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow((TempData.Data.data["durationEdit"] as EditText).getWindowToken(), 0)
        super.onPause()
    }*/
}