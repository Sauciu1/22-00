package com.example.a22_00

import android.app.TimePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet
import com.example.a22_00.DBHelper.DBHelper
import com.example.a22_00.Model.*
import yuku.ambilwarna.AmbilWarnaDialog
import java.time.LocalTime
import java.time.format.DateTimeFormatter


class CreateActivities : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        val db = DBHelper(applicationContext)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_activities)
        TempData.Data.data["Activities"] = ArrayList<Activity>()
        var position:Int = 0
        var activities = TempData.Data.data["Activities"] as ArrayList<Activity>
        activities.add(Activity())


        ///MYGTUKU LOGIKA
       val rowView = findViewById<View>(R.id.activityRow)

        var txtName = rowView.findViewById<EditText>(R.id.editTextActivityName)
        txtName.setText(activities[position].name)

        //txtName.setOnClickListener(this);



        txtName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                activities[position].name = s.toString()
                println(activities[position].name)


            }
        })


        // txtName.append(activities[position].name)

        //Button setTime
        val btnTime = rowView.findViewById<Button>(R.id.editTextActivityTime)
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        btnTime.setText((LocalTime.now().format(formatter)).toString())

        btnTime.setOnClickListener {
            val timePickerDialog = TimePickerDialog(this.applicationContext, TimePickerDialog.OnTimeSetListener({ view, hourOfDay, minute ->activities[position].begining = LocalTime.of(hourOfDay, minute); btnTime.setText(activities[position].begining.toString())}),activities[position].begining.hour,activities[position].begining.second,true)
            timePickerDialog.show()
        }

        //Button Duration
        val numDuration = rowView.findViewById<NumberPicker>(R.id.editTextActivityDuration)
        numDuration.minValue=1
        numDuration.maxValue=1440
        numDuration.setValue(activities[position].duration.toInt())
        numDuration.setOnValueChangedListener { picker, oldVal, newVal ->  activities[position].duration = newVal.toLong()}



        var savaite = booleanArrayOf(false,false,false,false,false,false,false,false)

        var Diena1 = rowView.findViewById<ToggleButton>(R.id.WeekDay1)
        Diena1.setOnCheckedChangeListener { buttonView, isChecked ->
            savaite[0]=isChecked
            saveDOW(savaite,position)
        }
        var Diena2 = rowView.findViewById<ToggleButton>(R.id.WeekDay2)
        Diena2.setOnCheckedChangeListener { buttonView, isChecked ->
            savaite[1]=isChecked
            saveDOW(savaite,position)
        }
        var Diena3 = rowView.findViewById<ToggleButton>(R.id.WeekDay3)
        Diena3.setOnCheckedChangeListener { buttonView, isChecked ->
            savaite[2]=isChecked
            saveDOW(savaite,position)
        }
        var Diena4 = rowView.findViewById<ToggleButton>(R.id.WeekDay4)
        Diena4.setOnCheckedChangeListener { buttonView, isChecked ->
            savaite[3]=isChecked
            saveDOW(savaite,position)
        }
        var Diena5 = rowView.findViewById<ToggleButton>(R.id.WeekDay5)
        Diena5.setOnCheckedChangeListener { buttonView, isChecked ->
            savaite[4]=isChecked
            saveDOW(savaite,position)
        }
        var Diena6 = rowView.findViewById<ToggleButton>(R.id.WeekDay6)
        Diena6.setOnCheckedChangeListener { buttonView, isChecked ->
            savaite[5]=isChecked
            saveDOW(savaite,position)
        }
        var Diena7 = rowView.findViewById<ToggleButton>(R.id.WeekDay7)
        Diena7.setOnCheckedChangeListener { buttonView, isChecked ->
            savaite[6]=isChecked
            saveDOW(savaite,position)
        }


        //Button Change color
        val colorPickerButton  = rowView.findViewById(R.id.colorPicker) as Button
        colorPickerButton.setOnClickListener{
            val cpd = AmbilWarnaDialog(this.applicationContext, activities[position].color!!.toArgb(), CPickerListener(colorPickerButton,position))
            cpd.show()
        }
        colorPickerButton.setBackgroundColor(activities[position].color!!.toArgb())

        //Button deleteThisActivity
        val deleteActivityButton  = rowView.findViewById(R.id.deleteThisActivity) as Button
        deleteActivityButton.setOnClickListener{
            findViewById<Button>(R.id.cancel).performClick()
            /*activities.removeAt(position);
            (it.parent.parent.parent.parent.parent as ListView).adapter = CreateActivityListAdapter(this.applicationContext,activities = activities)
            //((it.parent.parent.parent.parent.parent as ListView).adapter as ArrayAdapter<com.example.a22_00.Model.Activity>).remove(activities[position])
            //activities.removeAt(position);*/

        }
        println("Labas " + activities[position].name)

        ///MYGTUKU LOGIKA









        val positiveButtonClick = { dialog: DialogInterface, which: Int ->
            //Toast.makeText(applicationContext,
               // android.R.string.no, Toast.LENGTH_SHORT).show()
        }
            val done = findViewById<Button>(R.id.Done)
                done.setOnClickListener {

            val intent = Intent(this, MainActivity::class.java)
            if((TempData.Data.data["Activities"] as ArrayList<com.example.a22_00.Model.Activity>).size<1){
                //error "Please add activities"
                return@setOnClickListener
            }

            /*var x:Int=0
            while (x<(TempData.Data.data["Names"] as ArrayList<EditText>).size){
                (TempData.Data.data["Activities"] as ArrayList<com.example.a22_00.Model.Activity>)[x].name= (TempData.Data.data["Names"] as ArrayList<EditText>)[x].text.toString()
                x++
            }*/


            val t = TempData.Data.data["Activities"] as ArrayList<com.example.a22_00.Model.Activity>
            if(t.find { it.name==null||it.name=="" }==null){
                t.forEach({
                    if(it.id==Int.MAX_VALUE)db.insertActivity(it)
                })
                db.close()
                startActivity(intent)
            }
            else{
                db.close()
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Error")
                builder.setMessage("All activities should be named")
                builder.setPositiveButton("OK",positiveButtonClick)
                builder.show()
            }
        }
        val cancel = findViewById<Button>(R.id.cancel)
        cancel.setOnClickListener {
            //val intent = this.parentActivityIntent//Intent(this, CreateWorkout::class.java)
            TempData.Data.data["Activities"]=null
            db.close()
            this.finish()
            //startActivity(intent)
        }
        }
    fun saveDOW(savaite:BooleanArray,position: Int){
        var position:Int = 0
        var activities = TempData.Data.data["Activities"] as ArrayList<Activity>
        var dayOfWeek = 0.toByte()
        for(it in savaite.reversed()) {
            dayOfWeek = dayOfWeek.toInt().shl(1).toByte()
            dayOfWeek = dayOfWeek.toInt().or(if (it) 1 else 0).toByte()
            println(dayOfWeek)
        }
        activities[position].dayOfTheWeek = dayOfWeek
        println("issisaugojau: ${activities[position].dayOfTheWeek}")
    }
    /*override fun onPause() {

        // hide the keyboard in order to avoid getTextBeforeCursor on inactive InputConnection
        val inputMethodManager: InputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow((TempData.Data.data["durationEdit"] as EditText).getWindowToken(), 0)
        super.onPause()
    }*/
}