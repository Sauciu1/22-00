package com.example.a22_00.Model

import android.app.Activity
import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.a22_00.R
import yuku.ambilwarna.AmbilWarnaDialog
import java.time.LocalTime
import java.util.function.Consumer

//class CreateActivityListAdapter(private val context: Activity, private val activities: Array<com.example.a22_00.Model.Activity>): ArrayAdapter<String>(context, R.layout.row, activities.mapNotNull { it.name }.toTypedArray()) {
class CreateActivityListAdapter(private val context: Activity, private val activities: ArrayList<com.example.a22_00.Model.Activity>): ArrayAdapter<com.example.a22_00.Model.Activity>(context, R.layout.create_activity_row, activities.toTypedArray()) {
//class CreateActivityListAdapter(private val context: Activity, private val activities: List<com.example.a22_00.Model.Activity>): ListAdapter {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.create_activity_row, null, true)

        //Debugging
        println("sukuriau vieneta")

        //EditText Name
        val txtName = rowView.findViewById<EditText>(R.id.editTextActivityName)
        txtName.setText(activities[position].name)
        txtName.onFocusChangeListener = View.OnFocusChangeListener{v, hasFocus -> if(!hasFocus)activities[position].name = (v as EditText).text.toString()}// .setText(activities[position].name)}

        //Button setTime
        val btnTime = rowView.findViewById<Button>(R.id.editTextActivityTime)
        btnTime.setText(activities[position].begining.toString())
        btnTime.setOnClickListener {
            val timePickerDialog = TimePickerDialog(context, TimePickerDialog.OnTimeSetListener({view,hourOfDay,minute ->activities[position].begining = LocalTime.of(hourOfDay, minute); btnTime.setText(activities[position].begining.toString())}),activities[position].begining.hour,activities[position].begining.second,true)
            timePickerDialog.show()
        }

        //Button Duration
        val numDuration = rowView.findViewById<NumberPicker>(R.id.editTextActivityDuration)
        numDuration.minValue=1
        numDuration.maxValue=1440
        numDuration.setValue(activities[position].duration.toInt())
        numDuration.setOnValueChangedListener { picker, oldVal, newVal ->  activities[position].duration = newVal.toLong()}
        //numDuration.min=0
        //numDuration.max=1440
        //numDuration.handler.addListener(Consumer { activities[position].duration= (it as Int).toLong() })// { v, hasFocus -> if(!hasFocus)activities[position].duration = ((v as NumberPickerElement).getValue().toLong()) }
        /*numDuration.setOnClickListener {
            val numberPickerDialog = NumberPicker(context,AttributeSet())
        }*/
        /*
        //txtTime.onFocusChangeListener = View.OnFocusChangeListener{v, hasFocus -> if(!hasFocus)(TempData.Data.data["ActivityCreation"] as ArrayList<HashMap<String,View>>)[position]["txtTime"] = v else (v as EditText).setText(((TempData.Data.data["ActivityCreation"] as ArrayList<HashMap<String,View>>)[position]["txtTime"] as EditText).text)}// .setText(activities[position].name)}
        val txtDuration = rowView.findViewById<EditText>(R.id.editTextActivityDuration)
        //TempData.Data.data.put("durationEdit",txtDuration)
        */


        var savaite = booleanArrayOf(false,false,false,false,false,false,false,false)

        var Diena1 = rowView.findViewById<ToggleButton>(R.id.WeekDay1)
        Diena1.setOnCheckedChangeListener { buttonView, isChecked ->
            savaite[0]=isChecked

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
            val cpd = AmbilWarnaDialog(context, activities[position].color!!.toArgb(), CPickerListener(colorPickerButton,position))
            cpd.show()
        }
        colorPickerButton.setBackgroundColor(activities[position].color!!.toArgb())

        //Button deleteThisActivity
        val deleteActivityButton  = rowView.findViewById(R.id.deleteThisActivity) as Button
        deleteActivityButton.setOnClickListener{
            txtName.onFocusChangeListener.onFocusChange(txtName,txtName.hasFocus())
            txtName.onFocusChangeListener = null
            activities.removeAt(position);
            (it.parent.parent.parent.parent.parent as ListView).adapter = CreateActivityListAdapter(context,activities = activities)
            //((it.parent.parent.parent.parent.parent as ListView).adapter as ArrayAdapter<com.example.a22_00.Model.Activity>).remove(activities[position])
            //activities.removeAt(position);
        }

        return rowView
    }
    fun saveDOW(savaite:BooleanArray,position: Int){
        var dayOfWeek = 0.toByte()
        for(it in savaite.reversed()) {
            dayOfWeek = dayOfWeek.toInt().shl(1).toByte()
            dayOfWeek = dayOfWeek.toInt().or(if (it) 1 else 0).toByte()
            println(dayOfWeek)
        }
        activities[position].dayOfTheWeek = dayOfWeek
        println("issisaugojau: ${activities[position].dayOfTheWeek}")
    }
    fun addActivity(activity: com.example.a22_00.Model.Activity){
        activities.add(activity)
        add(activity)
    }
}
class CPickerListener: AmbilWarnaDialog.OnAmbilWarnaListener {
    constructor(btn:Button,pos:Int){
        button = btn
        i=pos
    }
    val button:Button
    val i:Int
    override fun onCancel(dialog: AmbilWarnaDialog?) {

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onOk(dialog: AmbilWarnaDialog?, color: Int) {
        button.setBackgroundColor(color)
        (TempData.Data.data["Activities"] as ArrayList<com.example.a22_00.Model.Activity>)[i].color = Color.valueOf(color)
    }

}