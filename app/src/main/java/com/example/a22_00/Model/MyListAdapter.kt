package com.example.a22_00.Model

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.a22_00.R

class MyListAdapter(private val context: Activity, private val timetables: Array<Timetable>)
    : ArrayAdapter<String>(context, R.layout.row, timetables.mapNotNull { it.name }.toTypedArray()) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.row, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val subtitleText = rowView.findViewById(R.id.description) as TextView
        val listView = rowView.findViewById(R.id.activitylist) as ListView

        //val myListAdapter = MyListAdapter(this,data.toTypedArray())
        listView.adapter = ActivityListAdapter(context, timetables[position].activities.toTypedArray())

        titleText.text = timetables[position].name
        subtitleText.text = timetables[position].description

        return rowView
    }

}