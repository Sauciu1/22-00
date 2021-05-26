package com.example.a22_00

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.a22_00.DBHelper.DBHelper
import com.example.a22_00.Model.Activity
import com.example.a22_00.Model.MyListAdapter
import com.example.a22_00.Model.Timetable
import org.w3c.dom.Text
import java.io.IOException
import java.io.InputStream
import java.lang.Exception
import java.time.LocalTime


class MainActivity : AppCompatActivity() {
    //internal lateinit var db:DBHelper




    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val yra =findViewById<Button>(R.id.mygtukas)
        val myTxt = findViewById<TextView>(R.id.myTextView)*/
        //db = DBHelper(this)
        val openWallpaper =findViewById<Button>(R.id.OpenWallpaper)
        val openCreateW =findViewById<Button>(R.id.OpenCWallpaper)


        val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
        intent.putExtra(
            WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
            ComponentName(this, WallpaperRodymas::class.java)
        )


        openWallpaper.setOnClickListener {
            startActivity(intent)
        }
        openCreateW.setOnClickListener {
            val prButton = Intent(this, CreateWorkout::class.java)
            startActivity(prButton)
        }


        val db = DBHelper(this.applicationContext)
        db.insertTimetable(Timetable())
        val data = db.getAllTimetables()
        // Čia reikia db data pversi į array ir tada galima sitas kodas veiks
        val listView = findViewById<ListView>(R.id.listView)  //formos elementas kuriame sis sarasas yra sukuriamas
        /*val names = data.mapNotNull{it.name}.toTypedArray()
        val descriptions = data.mapNotNull { it.description+"\n${it.activities.size}" }.toTypedArray()*/
        val myListAdapter = MyListAdapter(this,data.toTypedArray())
        listView.adapter = myListAdapter




        // informacija kodui, reikes pasalinti:
       // db.onUpgrade(db.readableDatabase 10 10)

        val t = Timetable("Lenkai","Kai tragedija Lenkiškoji pareina, reikia gintis!",arrayListOf<Activity>(
            Activity("Ant", LocalTime.of(18, 0), 120, Color.valueOf(Color.RED)),
            Activity("Kalno", LocalTime.of(18, 9), 120, Color.valueOf(Color.GREEN)),
            Activity("Murai", LocalTime.of(18, 12), 120, Color.valueOf(Color.YELLOW)),
            Activity("Joja", LocalTime.of(19,0), 120, Color.valueOf(Color.BLUE)),
            Activity("Lietuviai", LocalTime.of(21,0), 120, Color.valueOf(Color.RED))
        ))
        db.insertTimetable(t)


        /*listView.setOnItemClickListener(){adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
            Toast.makeText(this, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
        }*/
        //val db = DBHelper(this.applicationContext)
        //db.onUpgrade(db.writableDatabase,1,2)
        //db.insertTimetable(Timetable())

        /*data.forEach {
            it.activities.forEach {
            it
        } }*/

//        val textView = findViewById<TextView>(R.id.textView)
//        val data = readFromAsset()
//        textView.setText(data)
    }
}