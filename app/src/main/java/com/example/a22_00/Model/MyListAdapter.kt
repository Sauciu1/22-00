package com.example.a22_00.Model

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.a22_00.R

class MyListAdapter(private val context: Activity, private val title: Array<String>, private val description: Array<String>)
    : ArrayAdapter<String>(context, R.layout.row, title) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.row, null, true)

        val titleText = rowView.findViewById(R.id.title) as TextView
        val subtitleText = rowView.findViewById(R.id.description) as TextView

        titleText.text = title[position]
        subtitleText.text = description[position]

        return rowView
    }
}