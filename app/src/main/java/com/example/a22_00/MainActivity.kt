package com.example.a22_00

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.graphics.*
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.a22_00.DBHelper.DBHelper
import com.example.a22_00.Model.MyListAdapter
import com.example.a22_00.Model.Timetable
import org.w3c.dom.Text
import java.io.IOException
import java.io.InputStream
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    //internal lateinit var db:DBHelper




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


        // Čia reikia db data pversi į array ir tada galima sitas kodas veiks
        val listView = findViewById<ListView>(R.id.listView)
        val language =arrayOf<String>("C","C++","Java",".Net","Kotlin","Ruby","Rails","Python","Java Script","Php","Ajax","Perl","Hadoop")
        val description = arrayOf<String>("C programming is considered as the base for other programming languages",
                "C++ is an object-oriented programming language.",
                "Java is a programming language and a platform.",
                ".NET is a framework which is used to develop software applications.",
                "Kotlin is a open-source programming language, used to develop Android apps and much more.",
                "Ruby is an open-source and fully object-oriented programming language.",
                "Ruby on Rails is a server-side web application development framework written in Ruby language.",
                "Python is interpreted scripting  and object-oriented programming language.",
                "JavaScript is an object-based scripting language.",
                "PHP is an interpreted language, i.e., there is no need for compilation.",
                "AJAX allows you to send and receive data asynchronously without reloading the web page.",
                "Perl is a cross-platform environment used to create network and server-side applications.",
                "Hadoop is an open source framework from Apache written in Java."  )

        val myListAdapter = MyListAdapter(this,language,description)
        listView.adapter = myListAdapter

        listView.setOnItemClickListener(){adapterView, view, position, id ->
            val itemAtPos = adapterView.getItemAtPosition(position)
            val itemIdAtPos = adapterView.getItemIdAtPosition(position)
            Toast.makeText(this, "Click on item at $itemAtPos its item id $itemIdAtPos", Toast.LENGTH_LONG).show()
        }
        //val db = DBHelper(this.applicationContext)
        //db.onUpgrade(db.writableDatabase,0,1)
        //db.insertTimetable(Timetable())

//        val textView = findViewById<TextView>(R.id.textView)
//        val data = readFromAsset()
//        textView.setText(data)
    }
}