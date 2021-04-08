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









class WallpaperRodymas : WallpaperService() {
    override fun onCreateEngine(): Engine = WallpaperEngine()

    private inner class WallpaperEngine : WallpaperService.Engine() {



        @RequiresApi(Build.VERSION_CODES.O)
        override fun onTouchEvent(event: MotionEvent?) {


            if (event?.action == MotionEvent.ACTION_DOWN) {
                val canvas = surfaceHolder?.lockCanvas() ?: return
                val Plotis = canvas.width
                val Aukstis = canvas.height
                val laikas = LocalTime.now()
                val sekunde = laikas.getSecond()



                fun FonoSpalva(){
                    val paint = Paint().apply {
                        val randomColor = Random.nextInt(16_777_216)
                                .toString(16)
                                .padStart(6, '0')
                        color = Color.parseColor("#$randomColor")
                        style = Paint.Style.FILL
                    }
                    canvas.drawPaint(paint)
                }

                FonoSpalva()
                //Ekrano Plotis, aukstis ir laikas




                fun PieskKvadrata(Eile: Int, spalva: String){
                        val kvadratas = Paint()
                        kvadratas.style = Paint.Style.FILL
                        kvadratas.color = Color.parseColor(spalva)
                        kvadratas.isAntiAlias = true



                        val KvadratoKoordinates = RectF(
                                (Plotis / 5 * Eile).toFloat(),  // kaire x
                                (Aukstis - Aukstis * sekunde / 60).toFloat(),  // virsus y
                                (Plotis / 5 * 4 * Eile).toFloat(),  // desine x
                                (Aukstis - Aukstis * sekunde / 60 + Aukstis / 5).toFloat() // apacia y
                        )

                        val KampuApvalumas = 100
                        canvas.drawRoundRect(
                                KvadratoKoordinates,  // rect
                                KampuApvalumas.toFloat(),  // rx
                                KampuApvalumas.toFloat(),  // ry
                                kvadratas // Paint
                        )


                    }
                PieskKvadrata(1, "#0000FF")
                PieskKvadrata(2, "#EE82EE")

                fun PieskTeksta(Tekstas:String) {
                    val TekstoSavybes = Paint()
                    TekstoSavybes.color = Color.WHITE
                    TekstoSavybes.style = Paint.Style.FILL
                    // canvas.drawPaint(tekstas)

                    TekstoSavybes.color = Color.BLACK
                    TekstoSavybes.textSize = 100f
                    canvas.drawText(Tekstas, 200f, 400f, TekstoSavybes)
                }
                PieskTeksta("Labas")



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
                ComponentName(this, WallpaperRodymas::class.java)
        )
        startActivity(intent)


    }
}