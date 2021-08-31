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
import com.example.a22_00.Model.TempData
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

        val openWallpaper =findViewById<Button>(R.id.OpenWallpaper)
        val openCreateW =findViewById<Button>(R.id.OpenCWallpaper)


        val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
        intent.putExtra(
            WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
            ComponentName(this, WallpaperRodymas::class.java)
        )






        val db = DBHelper(applicationContext)
//
//        val t = Timetable("Lenkai","Kai tragedija Lenkiškoji pareina, reikia gintis!",arrayListOf<Activity>(
//            Activity("Ant", LocalTime.of(15, 0), 120, Color.valueOf(Color.RED)),
//            Activity("Kalno", LocalTime.of(11, 9), 120, Color.valueOf(Color.GREEN)),
//            Activity("Murai", LocalTime.of(18, 12), 120, Color.valueOf(Color.YELLOW)),
//            Activity("Joja", LocalTime.of(19,0), 120, Color.valueOf(Color.BLUE)),
//            Activity("Lietuviai", LocalTime.of(21,0), 120, Color.valueOf(Color.RED))
//        ))
//        db.insertTimetable(t)

        val data = db.getAllActivities()

        // Čia reikia db data pversi į array ir tada galima sitas kodas veiks
        val listView = findViewById<ListView>(R.id.listView)  //formos elementas kuriame sis sarasas yra sukuriamas
        listView.adapter = MyListAdapter(this,data.toTypedArray())
        openCreateW.setOnClickListener {
            db.close()
            val prButton = Intent(this, CreateActivities::class.java)
            startActivity(prButton)
        }
        openWallpaper.setOnClickListener {
            db.close()
            //listView.adapter = MyListAdapter(this,db.getAllTimetables().toTypedArray())
            startActivity(intent)
        }




    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()
        val db = DBHelper(applicationContext)
        val data = db.getAllActivities()
        val listView = findViewById<ListView>(R.id.listView)  //formos elementas kuriame sis sarasas yra sukuriamas
        listView.adapter = MyListAdapter(this,data.toTypedArray())
        db.close()
    }
}