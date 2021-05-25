package com.example.a22_00.Model

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import com.example.a22_00.R
import java.util.*

class NumberPickerElement : LinearLayout {
    val et_number: EditText
    public var min:Int=0
    public var max:Int=1440
    public var handler: Event = Event()

    constructor(context:Context, attrs: AttributeSet?):super(context, attrs) {
        //super(context, attrs);

        inflate(context, R.layout.number_picker_element, this);

        et_number = findViewById(R.id.et_number);

        val btn_less: Button = findViewById(R.id.btn_less);
        btn_less.setOnClickListener(AddHandler(-1,this));

        val btn_more:Button  = findViewById(R.id.btn_more);
        btn_more.setOnClickListener(AddHandler(1,this));
    }
    /***
     * HANDLERS
     **/

    private class AddHandler(private val diff:Int, private val source:NumberPickerElement) : OnClickListener {


        @RequiresApi(Build.VERSION_CODES.N)
        override fun onClick(v: View) {
            var newValue:Int = source.getValue() + diff;
            println("==========$newValue========== Min = ${source.min}; Max = ${source.max}")
            if (newValue < source.min) {
                newValue = source.min
            } else if (newValue > source.max) {
                newValue = source.max
            }
            println("${source.getValue()} + $diff => ${newValue}")
            source.setValue(newValue)
            source.handler.broadcast(newValue)
        }
    }

    /***
     * GETTERS & SETTERS
     */

    fun getValue():Int {
        if (et_number != null) {
            try {
                val value:String = et_number.getText().toString();
                return Integer.parseInt(value);
            } catch (ex:NumberFormatException) {
                Log.e("HorizontalNumberPicker", ex.toString());
            }
        }
        return 0;
    }

    fun setValue(value:Int) {
        if (et_number != null) {
            et_number.setText(value.toString());
        }
    }
}