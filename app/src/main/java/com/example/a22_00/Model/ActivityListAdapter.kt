package com.example.a22_00.Model

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import com.example.a22_00.R

class ActivityListAdapter(private val context: Activity, private val activities: Array<com.example.a22_00.Model.Activity>): ArrayAdapter<String>(context, R.layout.row, activities.mapNotNull { it.name }.toTypedArray()) {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.activity_row, null, true)

        val titleText = rowView.findViewById(R.id.activityname) as TextView
        val subtitleText = rowView.findViewById(R.id.activitydescription) as TextView
        val activityContainer = rowView.findViewById(R.id.cntactivity) as LinearLayout

        titleText.text = activities[position].name
        subtitleText.text =
            "${activities[position].begining} (${activities[position].duration} mins)"
        activityContainer.setBackgroundColor(activities[position].color!!.toArgb())




        return rowView
    }
}