package com.example.a22_00

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.os.Bundle
import android.service.wallpaper.WallpaperService
import android.view.MotionEvent
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




                val kvadratas = Paint()
                kvadratas.style = Paint.Style.FILL
                kvadratas.color = Color.RED
                kvadratas.isAntiAlias = true

                // Set an offset value in pixels to draw rounded rectangle on canvas




                val rectF = RectF(
                        (Plotis/5).toFloat(),  // kaire x
                        (Aukstis - Aukstis*sekunde/60).toFloat(),  // virsus y
                        (Plotis/5*4).toFloat(),  // desine x
                        (Aukstis - Aukstis*sekunde/60 +Aukstis/5).toFloat() // apacia y
                )

                /*
                    public void drawRoundRect (RectF rect, float rx, float ry, Paint paint)
                        Draw the specified round-rect using the specified paint. The roundrect
                        will be filled or framed based on the Style in the paint.

                    Parameters
                        rect : The rectangular bounds of the roundRect to be drawn
                        rx : The x-radius of the oval used to round the corners
                        ry : The y-radius of the oval used to round the corners
                        paint : The paint used to draw the roundRect
                */

                // Define the corners radius of rounded rectangle

                /*
                    public void drawRoundRect (RectF rect, float rx, float ry, Paint paint)
                        Draw the specified round-rect using the specified paint. The roundrect
                        will be filled or framed based on the Style in the paint.

                    Parameters
                        rect : The rectangular bounds of the roundRect to be drawn
                        rx : The x-radius of the oval used to round the corners
                        ry : The y-radius of the oval used to round the corners
                        paint : The paint used to draw the roundRect
                */

                // Define the corners radius of rounded rectangle
                val cornersRadius = 50

                // Finally, draw the rounded corners rectangle object on the canvas

                // Finally, draw the rounded corners rectangle object on the canvas
                canvas.drawRoundRect(
                        rectF,  // rect
                        cornersRadius.toFloat(),  // rx
                        cornersRadius.toFloat(),  // ry
                        kvadratas // Paint
                )








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