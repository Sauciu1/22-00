package com.example.a22_00

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.service.wallpaper.WallpaperService
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.ViewManager
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalTime
import kotlin.random.Random






class MyWallpaperService : WallpaperService() {
    override fun onCreateEngine(): Engine = WallpaperEngine()

    private inner class WallpaperEngine : WallpaperService.Engine() {



        @RequiresApi(Build.VERSION_CODES.O)
        override fun onTouchEvent(event: MotionEvent?) {


            if (event?.action == MotionEvent.ACTION_DOWN) {
                val canvas = surfaceHolder?.lockCanvas() ?: return

                val paint = Paint().apply {
                    val randomColor = Random.nextInt(16_777_216)
                            .toString(16)
                            .padStart(6, '0')
                    color = Color.parseColor("#$randomColor")
                    style = Paint.Style.FILL
                }

                //Ekrano Plotis, aukstis ir laikas
                canvas.drawPaint(paint)
                var Plotis = canvas.width
                var Aukstis = canvas.height
                val laikas = LocalTime.now()
                val sekunde = laikas.getSecond()








                val ratas = Paint().apply {
                    color = Color.RED
                    style = Paint.Style.STROKE
                    strokeWidth = 100f
                }













                canvas.drawCircle(400f * sekunde / 60,  1f*(Aukstis - Aukstis * sekunde / 60), 10f, ratas)
                surfaceHolder.unlockCanvasAndPost(canvas)








                //



            }
        }
    }
}




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
        intent.putExtra(
                WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                ComponentName(this, MyWallpaperService::class.java)
        )
        startActivity(intent)


    }
}