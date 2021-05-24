package com.example.a22_00.Model

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.a22_00.R
import yuku.ambilwarna.AmbilWarnaDialog

//class CreateActivityListAdapter(private val context: Activity, private val activities: Array<com.example.a22_00.Model.Activity>): ArrayAdapter<String>(context, R.layout.row, activities.mapNotNull { it.name }.toTypedArray()) {
class CreateActivityListAdapter(private val context: Activity, private val activities: ArrayList<com.example.a22_00.Model.Activity>): ArrayAdapter<com.example.a22_00.Model.Activity>(context, R.layout.create_activity_row, activities.toTypedArray()) {
//class CreateActivityListAdapter(private val context: Activity, private val activities: List<com.example.a22_00.Model.Activity>): ListAdapter {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.create_activity_row, null, true)

        val txtName = rowView.findViewById<EditText>(R.id.editTextActivityName)
        txtName.setText(activities[position].name)// = activities[position].name
        txtName.onFocusChangeListener = View.OnFocusChangeListener{v, hasFocus -> if(!hasFocus)activities[position].name = (v as EditText).text.toString() else (v as EditText).setText(activities[position].name)}
        val txtTime = rowView.findViewById<EditText>(R.id.editTextActivityTime)
        txtTime.isEnabled = true
        val txtDuration = rowView.findViewById<EditText>(R.id.editTextActivityDuration)
        val colorPickerButton  = rowView.findViewById(R.id.colorPicker) as Button
        colorPickerButton.setOnClickListener{
            val cpd = AmbilWarnaDialog(context, activities[position].color!!.toArgb(), CPickerListener(colorPickerButton,position))
            cpd.show()
        }

        return rowView
    }
    fun addActivity(activity: com.example.a22_00.Model.Activity){
        activities.add(activity)
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
        (TempData.Data.data["Timetable"] as Timetable).activities[i].color = Color.valueOf(color)
    }

}