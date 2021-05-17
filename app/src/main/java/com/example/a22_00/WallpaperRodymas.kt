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

class WallpaperRodymas : WallpaperService() {
    override fun onCreateEngine(): Engine = WallpaperEngine()

    private inner class WallpaperEngine : WallpaperService.Engine() {

        val LaikoDydis=360
        @RequiresApi(Build.VERSION_CODES.Q)
        var db:DBHelper = DBHelper(context = baseContext)

        @RequiresApi(Build.VERSION_CODES.O)

        var pradeta = 0
       @RequiresApi(Build.VERSION_CODES.O)
       override fun onTouchEvent(event: MotionEvent?) {
           val timetables = db.getAllTimetables()
           timetables.forEach{ println(it.name+":"); it.activities.forEach{ println("   "+it.name+"||||"+it.begining.toString()+"||||"+it.minutesTillStart())}}
        //println(("tvarkarasciu yra"+db.getAllTimetables().size.toString()))
          // if (event?.action == MotionEvent.ACTION_DOWN) {
        if(pradeta == 0){
            pradeta =1

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


                fun PieskKvadrata( Eile: Int, Pradzia: Int, Ilgis: Int, Pavadinimas: String, spalva: String) {
                           val kvadratas = Paint()
                           kvadratas.style = Paint.Style.FILL
                           kvadratas.color = Color.parseColor(spalva)
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

                               println("pirmas")
                               println(Ilgis * Aukstis / LaikoDydis)
                               println(TekstoAukstis)
                               println(TekstoPlotis)


                               TekstoSavybes.textSize = (1f * Plotis / 3.5f / TekstoPlotis * TekstoSavybes.textSize)
                               if (Tekstas.length < 5) TekstoSavybes.textSize = TekstoSavybes.textSize * Tekstas.length / 6

                               TekstoSavybes.getTextBounds(Tekstas, 0, Tekstas.length, Ribos)
                               TekstoAukstis = Ribos.height()

                               if(TekstoAukstis *2 > Aukstis/ LaikoDydis * Ilgis) TekstoSavybes.textSize = TekstoSavybes.textSize * Aukstis /LaikoDydis *Ilgis/ TekstoAukstis /3

                               TekstoSavybes.getTextBounds(Tekstas, 0, Tekstas.length, Ribos)
                               TekstoAukstis = Ribos.height()
                               TekstoPlotis = Ribos.width()

                               println("antras")
                               println(TekstoAukstis)
                               println(TekstoPlotis)


                               println(TekstoPlotis)
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
                    var Pradzia2 = Pradzia - Ilgis
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
                fun Skale(kartok : Int, Spalva: String) {
                    PieskSkale(-10, Plotis + 10, 30, 2, Spalva)
                    for (i in 1..kartok){
                        PieskSkale(-10, Plotis / 8, 60*i + 30, 1, Spalva)
                        PieskSkale(Plotis / 8 * 7, Plotis + 10, 60*i + 30, 1, Spalva)
                    }
                }
                //// Nuo čia piešiame kvadratus ir visą kitą



                fun PateikKvadrata( Eile: Int, Pradzia: Int, Ilgis: Int, Pavadinimas: String, spalva: String){

                    PieskKvadrata(0, LaikoDydis - sekunde*6, 20, "lietuviu kalba man nepatinka", "#FF0000")

                }

                PateikKvadrata(0, 1, 20, "lietuviu kalba man nepatinka", "#FF0000")



                        Skale(6,"#000000")
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