package com.example.a22_00.Model

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.a22_00.R

class MyListAdapter(private val context: Activity, private val activities: Array<com.example.a22_00.Model.Activity>)
    : ArrayAdapter<String>(context, R.layout.row, activities.mapNotNull { it.name }.toTypedArray()) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.activity_row, null, true)



        //val listView = rowView.findViewById(R.id.activitylist) as ListView

        //val myListAdapter = MyListAdapter(this,data.toTypedArray())
        //listView.adapter = ActivityListAdapter(context, timetables[position].activities.toTypedArray())


        return rowView
    }

}