package com.example.a22_00

import android.content.Context
import android.graphics.*
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.service.wallpaper.WallpaperService
import android.view.MotionEvent
import androidx.annotation.RequiresApi
import com.example.a22_00.DBHelper.DBHelper
import com.example.a22_00.Model.Activity
import com.example.a22_00.Model.Timetable
import java.time.LocalTime
import kotlin.concurrent.fixedRateTimer
import kotlin.random.Random
import java.util.Queue
import java.util.LinkedList

class WallpaperRodymas : WallpaperService() {
    override fun onCreateEngine(): Engine = WallpaperEngine()

    private inner class WallpaperEngine : WallpaperService.Engine() {

        val LaikoDydis=360
        @RequiresApi(Build.VERSION_CODES.Q)
        var db:DBHelper = DBHelper(context = baseContext)

        @RequiresApi(Build.VERSION_CODES.O)

        var pradeta = 1
       @RequiresApi(Build.VERSION_CODES.O)
       override fun onTouchEvent(event: MotionEvent?) {
           val timetables = db.getAllTimetables()

          // if (event?.action == MotionEvent.ACTION_DOWN) {
        if(pradeta == 1){
            pradeta =0






            @RequiresApi(Build.VERSION_CODES.O)
            fun Keitimas() {
                       val canvas = surfaceHolder?.lockCanvas() ?: return
                       val Plotis = canvas.width
                       val Aukstis = canvas.height
                       val laikas = LocalTime.now()
                       val sekunde = laikas.getSecond()


                       fun FonoSpalva() {
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


                fun PieskKvadrata( Eile: Int, Pradzia: Int, Ilgis: Int, Pavadinimas: String, spalva: Color) {
                           val kvadratas = Paint()
                           kvadratas.style = Paint.Style.FILL
                           kvadratas.color = spalva.toArgb()
                           kvadratas.isAntiAlias = true


                           val KvadratoKoordinates = RectF(
                                   (Plotis / 81 + (Eile * Plotis / 3)).toFloat(),  // desine x
                                   (Aukstis * (Pradzia) / LaikoDydis ).toFloat(),  // virsus y
                                   (Plotis * 26 / 81 + (Eile * Plotis / 3)).toFloat(),  // kaire x
                                   (Aukstis * (Pradzia + Ilgis) / LaikoDydis).toFloat() // apacia y
                           )

                           val KampuApvalumas = 50
                           canvas.drawRoundRect(
                                   KvadratoKoordinates,  // rect
                                   KampuApvalumas.toFloat(),  // rx
                                   KampuApvalumas.toFloat(),  // ry
                                   kvadratas // Paint
                           )



                           //var
                           fun DalykoTekstas(Kartojimas: Int, Tekstas: String): Float {
                               val TekstoSavybes = Paint()
                               val Ribos = Rect()


                               TekstoSavybes.typeface = Typeface.DEFAULT // your preference here
                               TekstoSavybes.textSize = 100f // have this the same as your text size
                               TekstoSavybes.textAlign = Paint.Align.CENTER

                               TekstoSavybes.getTextBounds(Tekstas, 0, Tekstas.length, Ribos)
                               var TekstoAukstis = Ribos.height()
                               var TekstoPlotis = Ribos.width()


                               TekstoSavybes.textSize = (1f * Plotis / 3.5f / TekstoPlotis * TekstoSavybes.textSize)
                               if (Tekstas.length < 5) TekstoSavybes.textSize = TekstoSavybes.textSize * Tekstas.length / 6

                               TekstoSavybes.getTextBounds(Tekstas, 0, Tekstas.length, Ribos)
                               TekstoAukstis = Ribos.height()

                               if(TekstoAukstis *2 > Aukstis/ LaikoDydis * Ilgis) TekstoSavybes.textSize = TekstoSavybes.textSize * Aukstis /LaikoDydis *Ilgis/ TekstoAukstis /3

                               TekstoSavybes.getTextBounds(Tekstas, 0, Tekstas.length, Ribos)
                               TekstoAukstis = Ribos.height()
                               TekstoPlotis = Ribos.width()



                               canvas.drawText(Tekstas, (Plotis / 6 + (Eile * Plotis / 3)).toFloat(), (TekstoAukstis * 2f * Kartojimas + Aukstis * Pradzia / LaikoDydis), TekstoSavybes)
                               return TekstoAukstis.toFloat()
                           }

                           val TekstoKartojimas = DalykoTekstas(1, Pavadinimas)


                           var Kelintas = 1

                           while ((Kelintas) * TekstoKartojimas < Ilgis * Plotis / LaikoDydis) {
                               DalykoTekstas(Kelintas, Pavadinimas)
                               Kelintas += 1
                           }

                       }
                fun PieskSkale(Kaire:Int, Desine:Int, Pradzia: Int, Ilgis: Int, spalva: String) {
                    val Pradzia2 = Pradzia - Ilgis
                    val kvadratas = Paint()
                    kvadratas.style = Paint.Style.FILL
                    kvadratas.color = Color.parseColor(spalva)
                    kvadratas.isAntiAlias = true


                    val KvadratoKoordinates = RectF(
                            Desine.toFloat(),  // desine x
                            (Aukstis * Pradzia2 / LaikoDydis).toFloat(),  // virsus y
                            Kaire.toFloat(),  // kaire x
                            (Aukstis * (Pradzia2 + Ilgis) / LaikoDydis).toFloat() // apacia y
                    )

                    val KampuApvalumas = 50
                    canvas.drawRoundRect(
                            KvadratoKoordinates,  // rect
                            KampuApvalumas.toFloat(),  // rx
                            KampuApvalumas.toFloat(),  // ry
                            kvadratas // Paint
                    )



                }
                fun Skale(kartok : Int) {
                    PieskSkale(-10, Plotis + 10, 33, 2, "#000000")
                    PieskSkale(-10, Plotis + 10, 31, 1, "#ffffff")
                    PieskSkale(-10, Plotis + 10, 29, 1, "#ffffff")
                    PieskSkale(-10, Plotis + 10, 28, 2, "#000000")

                    for (i in 1..kartok){
                        PieskSkale(-10, Plotis / 16, 60*i + 31, 3, "#000000")
                        PieskSkale(-10, Plotis / 17, 60*i + 30, 1, "#ffffff")
                        PieskSkale(Plotis / 16 * 15, Plotis + 10, 60*i + 31, 3, "#000000")
                        PieskSkale(Plotis / 17 * 16, Plotis + 10, 60*i + 30, 1, "#ffffff")
                    }
                }
                //// Nuo čia piešiame kvadratus ir visą kitą



                fun PateikKvadrata( Eile: Int, Pradzia: Int, Ilgis: Int, Pavadinimas: String, spalva: String){

                    //PieskKvadrata(Eile, LaikoDydis - sekunde*6, 20, "lietuviu kalba man nepatinka", "#FF0000")

                }

                PateikKvadrata(0, 1, 20, "lietuviu kalba man nepatinka", "#FF0000")
                //vaziuojam - eile
/*
                class ArrayListQueue<T> : Queue<T> {
                    private val list = arrayListOf<T>()
                }
                val eile1 = ArrayListQueue <Int>
*/

                //minutes till start

                val data = db.getAllTimetables()
                var nulis=-500
                var vienas=-500
                var du=-500
                var kuris=-1
                 timetables.forEach{
                    // println(it.name+":");
                     it.activities.forEach{
                         //println("   "+it.name+"||||"+it.begining.toString()+"||||"+it.minutesTillStart()+"||||"+it.duration+"||||"+it.color)

                         if(it.minutesTillStart() + it.duration >-30 && it.minutesTillStart() <360){
                            /// reikia pasirinkti kokiu budu rinktis ka rodyti
/*
                            var kuris=0
                             if(nulis <= it.minutesTillStart()){
                                 nulis = (it.minutesTillStart() + it.duration).toInt()
                             }
                             else{
                                 if(vienas <= it.minutesTillStart()){
                                     vienas = (it.minutesTillStart() + it.duration).toInt()
                                     kuris=1
                                 }
                                 else {
                                     du = (it.minutesTillStart() + it.duration).toInt()
                                     kuris=2
                                    }
                                 }
                             */


                             //println("vertes " + nulis.toString() + " | " + vienas.toString()+ " | " + du.toString())
                             kuris=(kuris+1)%3
                             PieskKvadrata(kuris, (it.minutesTillStart() +30).toInt(), it.duration.toInt(),it.name, it.color!!)
                             }




                         }
                     }
                    println("baigta")
                //println(("tvarkarasciu yra"+db.getAllTimetables().size.toString()))


                data.forEach{
                    it.activities.forEach{
                        println(it)
                    }
                }













                //

                        Skale(6,)
                       surfaceHolder.unlockCanvasAndPost(canvas)
                   }



                Keitimas()

                val Kartojimas = Handler(Looper.getMainLooper())

            Kartojimas.post(object : Runnable {
                       override fun run() {
                           Keitimas()
                           Kartojimas.postDelayed(this, 1000*2)
                       }
                   })


                    //



           }
        }
    }
}