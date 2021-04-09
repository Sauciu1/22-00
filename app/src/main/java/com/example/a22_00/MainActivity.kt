package com.example.a22_00

import android.app.WallpaperManager
import android.content.ComponentName
import android.content.Intent
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.service.wallpaper.WallpaperService
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.lang.Float.min
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




                fun PieskKvadrata(Eile: Int, Pradzia: Int, Ilgis: Int, Pavadinimas: String, spalva: String){
                        val kvadratas = Paint()
                        kvadratas.style = Paint.Style.FILL
                        kvadratas.color = Color.parseColor(spalva)
                        kvadratas.isAntiAlias = true



                        val KvadratoKoordinates = RectF(
                                (Plotis / 81 + (Eile * Plotis / 3)).toFloat(),  // desine x
                                (Aukstis * Pradzia / 60).toFloat(),  // virsus y
                                (Plotis * 26 / 81 + (Eile * Plotis / 3)).toFloat(),  // kaire x
                                (Aukstis * (Pradzia + Ilgis) / 60).toFloat() // apacia y
                        )

                        val KampuApvalumas = 50
                        canvas.drawRoundRect(
                                KvadratoKoordinates,  // rect
                                KampuApvalumas.toFloat(),  // rx
                                KampuApvalumas.toFloat(),  // ry
                                kvadratas // Paint
                        )




                        fun DalykoTekstas(Kartojimas:Int,Tekstas: String) : Float{
                            val TekstoSavybes = Paint()
                            val Ribos = Rect()


                            TekstoSavybes.typeface = Typeface.DEFAULT // your preference here
                            TekstoSavybes.textSize = 100f // have this the same as your text size
                            TekstoSavybes.textAlign = Paint.Align.CENTER

                            TekstoSavybes.getTextBounds(Tekstas, 0, Tekstas.length, Ribos)
                            var TekstoAukstis = Ribos.height()
                            var TekstoPlotis = Ribos.width()

                            println("pirmas")
                            println(Ilgis*Aukstis/60)
                            println(TekstoAukstis)
                            println(TekstoPlotis)


                            TekstoSavybes.textSize = (1f*Plotis/3.5f/TekstoPlotis*100)
                            if(Tekstas.length<5) TekstoSavybes.textSize = TekstoSavybes.textSize * Tekstas.length /5

                            TekstoSavybes.getTextBounds(Tekstas, 0, Tekstas.length, Ribos)
                            TekstoAukstis = Ribos.height()
                            TekstoPlotis = Ribos.width()

                            println("antras")
                            println(TekstoAukstis)
                            println(TekstoPlotis)


                            println(TekstoPlotis)
                            canvas.drawText(Tekstas, (Plotis / 6 + (Eile * Plotis / 3)).toFloat(), (TekstoAukstis * 2f *Kartojimas + Aukstis * Pradzia / 60).toFloat(), TekstoSavybes)
                            return TekstoAukstis.toFloat()
                        }
                        val TekstoKartojimas = DalykoTekstas(1,Pavadinimas)


                    var Kelintas = 1;

                    while (Kelintas *TekstoKartojimas < Ilgis*Plotis/60) {
                            DalykoTekstas( Kelintas, Pavadinimas)
                            Kelintas+=1
                        }

                    }




                PieskKvadrata(0, 0, 20, "lietuviu","#FF0000")
                PieskKvadrata(1, 10, 50, "Matematika", "#0000FF")
                PieskKvadrata(2, 14, 15, "IT","#00FF00")




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