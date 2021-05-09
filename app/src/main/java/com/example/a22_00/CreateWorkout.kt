package com.example.a22_00

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class CreateWorkout : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_workout)


        val cancel = findViewById<Button>(R.id.CancelWorkout)
        cancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val savenext = findViewById<Button>(R.id.OpenActivities)
        val inputWN = findViewById<EditText>(R.id.InputOfWN)
        val inputWD = findViewById<EditText>(R.id.InputOfWD)
        savenext.setOnClickListener {
            val nameInput = inputWN.text.toString()
            val descInput = inputWD.text.toString()

            val intent = Intent(this, CreateActivities::class.java)
            startActivity(intent)
        }


    }
}