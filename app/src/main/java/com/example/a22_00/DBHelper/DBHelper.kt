package com.example.a22_00.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.a22_00.Model.Activity
import com.example.a22_00.Model.Timetable
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DBHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VER) {
    companion object{
        private val DATABASE_VER = 1
        private val  DATABASE_NAME = "Schedules.db"

        //table timetables
        /*private  val TABLE_TIMETABLE="Timetables"
        private  val COL_ID="Id"
        private  val COL_NAME="Name"
        private  val COL_DESCRIPTION="Description"*/

        //table activities
        private  val TABLE_ACTIVITIES="Activities"
        private  val COL_ID="Id"
        private  val COL_NAME="Name"
        private  val COL_BEGINING="Begining"
        private  val COL_DURATION="Duration"
        private  val COL_COLOR="Color"
        private  val COL_DAY_OF_THE_WEEK="Day"

        /*//table activities to timetables
        private  val TABLE_ACTIVITIES_TO_TIMETABLES="ActivitiesToTimetables"
        private  val COL_TIMETABLE_ID="TimetableId"
        private  val COL_ACTIVITY_ID="ActivityId"*/
    }

    override fun onCreate(db: SQLiteDatabase?) {
        /*var CREATE_TABLE_QUERY:String = ("CREATE TABLE IF NOT EXISTS $TABLE_TIMETABLE ($COL_ID INTEGER PRIMARY KEY,$COL_NAME TEXT,$COL_DESCRIPTION TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY);*/

        val  CREATE_TABLE_QUERY = ("CREATE TABLE IF NOT EXISTS $TABLE_ACTIVITIES ($COL_ID INTEGER PRIMARY KEY,$COL_NAME TEXT,$COL_BEGINING TEXT, $COL_DURATION INTEGER, $COL_COLOR TEXT, $COL_DAY_OF_THE_WEEK INTEGER)")
        db!!.execSQL(CREATE_TABLE_QUERY);

        /*CREATE_TABLE_QUERY = ("CREATE TABLE IF NOT EXISTS $TABLE_ACTIVITIES_TO_TIMETABLES ($COL_TIMETABLE_ID INTEGER,$COL_ACTIVITY_ID INTEGER, FOREIGN KEY('$COL_TIMETABLE_ID') REFERENCES '$TABLE_TIMETABLE'('$COL_ID'),  FOREIGN KEY('$COL_ACTIVITY_ID') REFERENCES '$TABLE_ACTIVITIES'('$COL_ID') ) ")
        //	FOREIGN KEY("BId") REFERENCES "b2"("ID")
        db!!.execSQL(CREATE_TABLE_QUERY);*/
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //db!!.execSQL("DROP TABLE IF EXISTS $TABLE_TIMETABLE ")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_ACTIVITIES ")
        //db!!.execSQL("DROP TABLE IF EXISTS $TABLE_ACTIVITIES_TO_TIMETABLES ")
        onCreate(db!!)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    /*fun getAllTimetables(): ArrayList<Timetable> {
        val data = mutableMapOf<Int, Timetable>()
        val db = this.writableDatabase
        var cursor  = db.rawQuery("SELECT * FROM $TABLE_TIMETABLE",null)
        if(cursor.moveToFirst()){
            do{
                 val timetable = Timetable()
                timetable.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                timetable.description = cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION))
                data[cursor.getInt(cursor.getColumnIndex(COL_ID))]=timetable
            }while (cursor.moveToNext())
        }
        else{
            cursor.close()
            return arrayListOf(Timetable())
        }
        cursor.close()
        val activities = mutableMapOf<Int, com.example.a22_00.Model.Activity>()
        cursor  = db.rawQuery("SELECT * FROM $TABLE_ACTIVITIES ORDER BY $COL_BEGINING DESC",null)
        if(cursor.moveToFirst()) {
            do {
                val activity = com.example.a22_00.Model.Activity()
                //activity.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                activity.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                activity.begining = LocalTime.parse(cursor.getString(cursor.getColumnIndex(COL_BEGINING)), DateTimeFormatter.ISO_LOCAL_TIME)
                activity.duration = cursor.getInt(cursor.getColumnIndex(COL_DURATION)).toLong()
                activity.color = Color.valueOf((Color.parseColor(cursor.getString(cursor.getColumnIndex(COL_COLOR)))))
                activities[cursor.getInt(cursor.getColumnIndex(COL_ID))] = activity
            } while (cursor.moveToNext())
        }
        cursor.close()
        cursor  = db.rawQuery("SELECT * FROM $TABLE_ACTIVITIES_TO_TIMETABLES ",null)
        if(cursor.moveToFirst()){
            do{
                val idTimetable = cursor.getInt(cursor.getColumnIndex(COL_TIMETABLE_ID))
                val idActivity = cursor.getInt(cursor.getColumnIndex(COL_ACTIVITY_ID))
                /*val t = data.first(predicate = {it.id==idTimetable})
                (t.activities as ArrayList<com.example.a22_00.Model.Activity>).add(activities.first(predicate = {it.id==idActivity}))
                data[data.indexOfFirst { it.id==t.id }] = t*/
                (data[idTimetable]!!.activities as ArrayList<com.example.a22_00.Model.Activity>).add(activities[idActivity]!!)


            }while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        if(data.count()>1) return ((data.toList() as ArrayList<Pair<Int,Timetable>>).map{ it.second }.toList() as ArrayList<Timetable>)
        else return arrayListOf(Timetable())
    }*/

    fun getAllActivities(): ArrayList<com.example.a22_00.Model.Activity> {
        val data = ArrayList<com.example.a22_00.Model.Activity>()
        val db = this.writableDatabase
        //val activities = mutableMapOf<Int, com.example.a22_00.Model.Activity>()
        val cursor  = db.rawQuery("SELECT * FROM $TABLE_ACTIVITIES ORDER BY $COL_BEGINING DESC",null)
        if(cursor.count<1) return arrayListOf(Activity())
        if(cursor.moveToFirst()) {
            do {
                val activity = com.example.a22_00.Model.Activity()
                activity.id = cursor.getInt(cursor.getColumnIndex(COL_ID))
                activity.name = cursor.getString(cursor.getColumnIndex(COL_NAME))
                activity.begining = LocalTime.parse(cursor.getString(cursor.getColumnIndex(COL_BEGINING)), DateTimeFormatter.ISO_LOCAL_TIME)
                activity.duration = cursor.getInt(cursor.getColumnIndex(COL_DURATION)).toLong()
                activity.color = Color.valueOf((Color.parseColor(cursor.getString(cursor.getColumnIndex(COL_COLOR)))))
                activity.dayOfTheWeek = cursor.getInt(cursor.getColumnIndex(COL_DAY_OF_THE_WEEK)).toByte()
                //activities[cursor.getInt(cursor.getColumnIndex(COL_ID))] = activity
                data.add(activity)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        if(data.count()>0) return data
        else return arrayListOf(Activity())
    }


    /*@RequiresApi(Build.VERSION_CODES.O)
    fun insertTimetable(data:Timetable){
        val db = this.writableDatabase
        /*val query = "INSERT $TABLE_NAME VALUE("+data.id+", "+data.name+", "+data.description+") "
        db.execSQL(query)*/
        val values = ContentValues()
        //values.put(COL_ID,data.id)
        values.put(COL_NAME,data.name)
        values.put(COL_DESCRIPTION,data.description)
        val timetableID = db.insert(TABLE_TIMETABLE,null,values)
        println(data.name+" ikisinejam i DB")
        values.clear()
        data.activities.forEach({
            //values.put(COL_ID,null)
            values.put(COL_NAME,it.name)
            values.put(COL_BEGINING,it.begining.toString())
            values.put(COL_DURATION,it.duration)
            values.put(COL_COLOR, "#" + Integer.toHexString(it.color!!.toArgb()).substring(2))
            val activityID=db.insert(TABLE_ACTIVITIES,null,values)
            println("ikiSAU ${it.name}(${it.color}) i DB")
            values.clear()
            values.put(COL_TIMETABLE_ID,timetableID)
            values.put(COL_ACTIVITY_ID,activityID)
            db.insert(TABLE_ACTIVITIES_TO_TIMETABLES,null,values)
            println("ikiSAU ${it.name} ir jo tvarkarascio susiejima i DB")
            values.clear()
        })
        db.close()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateTimetable(data:Timetable){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME,data.name)
        values.put(COL_DESCRIPTION,data.description)
        db.update(TABLE_TIMETABLE,values,"$COL_NAME=?", arrayOf(data.name))
        values.clear()
        val c = db.rawQuery("SELECT ID FROM $TABLE_TIMETABLE WHERE $COL_NAME = ${data.name}",null)
        c.moveToFirst()
        val timetableID=c.getInt(0)
        c.close()
        data.activities.forEach({
                //values.put(COL_ID,it.id)
                values.put(COL_NAME,it.name)
                values.put(COL_BEGINING,it.begining.toString())
                values.put(COL_DURATION,it.duration)
                values.put(COL_COLOR,"#" + Integer.toHexString(it.color!!.toArgb()).substring(2))
                var activityID=db.insert(TABLE_ACTIVITIES,null,values)
                values.clear()
                values.put(COL_TIMETABLE_ID,timetableID)
                values.put(COL_ACTIVITY_ID,activityID)
                db.insert(TABLE_ACTIVITIES_TO_TIMETABLES,null,values)
                values.clear()

        })
        db.close()
    }*/

    @RequiresApi(Build.VERSION_CODES.O)
    fun insertActivity(data: Activity){
        val db = this.writableDatabase
        /*val query = "INSERT $TABLE_NAME VALUE("+data.id+", "+data.name+", "+data.description+") "
        db.execSQL(query)*/
        val values = ContentValues()

        values.put(COL_NAME,data.name)
        values.put(COL_BEGINING,data.begining.toString())
        values.put(COL_DURATION,data.duration)
        values.put(COL_COLOR, "#" + Integer.toHexString(data.color!!.toArgb()).substring(2))
        values.put(COL_DAY_OF_THE_WEEK,data.name)
        db.insert(TABLE_ACTIVITIES,null,values)
        println("sukisau veikla sarasan")
        values.clear()
        db.close()
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateActivity(data:Activity){
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NAME,data.name)
        values.put(COL_BEGINING,data.begining.toString())
        values.put(COL_DURATION,data.duration)
        values.put(COL_COLOR, "#" + Integer.toHexString(data.color!!.toArgb()).substring(2))
        values.put(COL_DAY_OF_THE_WEEK,data.name)
        if(db.update(TABLE_ACTIVITIES,values,"$COL_ID=?", arrayOf(data.id.toString()))==1)println("update'inau viena eilute")
        values.clear()
        db.close()
    }
    /*
    fun deleteTimetable(data:Timetable){
        val db = this.writableDatabase
        db.delete(TABLE_TIMETABLE,"$COL_NAME=?", arrayOf(data.name))
        val c = db.rawQuery("SELECT ID FROM $TABLE_TIMETABLE WHERE $COL_NAME = ${data.name}",null)
        c.moveToFirst()
        val timetableID=c.getInt(0)
        c.close()

        var qry = db.rawQuery("SELECT $COL_ACTIVITY_ID FROM $TABLE_TIMETABLE WHERE $COL_TIMETABLE_ID = $timetableID",null)
        if(qry.moveToFirst())
        do{
            db.execSQL("DELETE FROM $TABLE_ACTIVITIES WHERE $COL_ID = ${qry.getInt(0)}")
        }while (qry.moveToNext())
        qry.close()
        db.delete(TABLE_ACTIVITIES_TO_TIMETABLES,"$COL_TIMETABLE_ID=?", arrayOf(timetableID.toString()))
        //db.delete(TABLE_ACTIVITIES,"$COL_ID=?", arrayOf(data.activ))
        db.close()
    }
     */
    fun deleteActivity(data: Activity){
        val db = this.writableDatabase
        db.delete(TABLE_ACTIVITIES,"$COL_ID=?", arrayOf(data.id.toString()))
        db.close()
    }
}