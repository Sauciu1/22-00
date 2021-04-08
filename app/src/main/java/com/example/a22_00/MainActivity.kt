package com.example.a22_00

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.os.Bundle
import android.service.wallpaper.WallpaperService
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.time.LocalTime
import java.time.Period
import kotlin.concurrent.timer
import kotlin.random.Random



class MyWallpaperService : WallpaperService() {
    override fun onCreateEngine(): Engine = WallpaperEngine()

    private inner class WallpaperEngine : WallpaperService.Engine() {
        /*val canvas: Canvas = if(surfaceHolder?.lockCanvas()!= null) surfaceHolder?.lockCanvas()!!else{
            Canvas()
        }*/
        var running: Boolean = false
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onTouchEvent(event: MotionEvent?) {
            if (event?.action == MotionEvent.ACTION_DOWN) {

                /*val paint = Paint().apply {
                    val randomColor = Random.nextInt(16_777_216)
                            .toString(16)
                            .padStart(6, '0')
                    color = Color.parseColor("#$randomColor")
                    style = Paint.Style.FILL
                }
                canvas.drawPaint(paint)

                //surfaceHolder.unlockCanvasAndPost(canvas)


                //



                val laikas = LocalTime.now()
                val sekunde = laikas.getSecond()
                println(sekunde)




                val ratas = Paint().apply {
                    color = Color.RED
                    style = Paint.Style.STROKE
                    strokeWidth = 10f
                }




/*
                val displayMetrics = DisplayMetrics()
                windowManager.defaultDisplay.getMetrics(displayMetrics)

                var width = displayMetrics.widthPixels
                var height = displayMetrics.heightPixels
*/


                canvas.drawCircle(400f * sekunde / 60, 400f * sekunde / 60, 100f, ratas)
                surfaceHolder.unlockCanvasAndPost(canvas)







                //
            */
                println("me not really started!")
                if(!running) {
                    startPeriodically(1000)
                    running=true
                }
            }
        }

        fun startPeriodically(period: Long){
            val canvas: Canvas = surfaceHolder?.lockCanvas()!!
            val t = timer(
                    null, false, 10, period,
             {
                val paint = Paint().apply {
                    val randomColor = Random.nextInt(16_777_216)
                            .toString(16)
                            .padStart(6, '0')
                    color = Color.parseColor("#$randomColor")
                    style = Paint.Style.FILL
                }
                canvas.drawPaint(paint)
                surfaceHolder.unlockCanvasAndPost(canvas)
                println("me started!")
            })

            println("me not really started!")
        }
    }
}




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*val yra =findViewById<Button>(R.id.mygtukas)
        val myTxt = findViewById<TextView>(R.id.myTextView)*/



        val intent = Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER)
        intent.putExtra(
                WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                ComponentName(this, MyWallpaperService::class.java)
        )
        startActivity(intent)


    }
}