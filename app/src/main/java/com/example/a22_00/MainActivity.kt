package com.example.a22_00

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import android.graphics.*

import android.os.Build
import android.os.Bundle
import android.service.wallpaper.WallpaperService
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import com.example.a22_00.DBHelper.DBHelper

import java.lang.Float.min

import java.time.LocalTime
import java.time.Period
import kotlin.concurrent.timer
import kotlin.random.Random




class MainActivity : AppCompatActivity() {
    //internal lateinit var db:DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val yra =findViewById<Button>(R.id.mygtukas)
        val myTxt = findViewById<TextView>(R.id.myTextView)*/
        //db = DBHelper(this)


        val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
        intent.putExtra(
                WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                ComponentName(this, WallpaperRodymas::class.java)
        )
        startActivity(intent)


    }
}