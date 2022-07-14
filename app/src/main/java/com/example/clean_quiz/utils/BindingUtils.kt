package com.example.clean_quiz.utils

import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.google.android.material.textfield.TextInputEditText


class BindingUtils {

        @BindingAdapter("android:textToString")
        fun TextInputEditText.setText(value: Int) {
            if (this.text.toString().toInt() != value)
                setText( "" + value)
        }

        @InverseBindingAdapter(attribute = "textToString")
        fun getText(value: Int): Int {
            return value
        }
    }


fun addQueryParam(paramName:String, paramValue:String?):String?{
    if (paramValue == null){
        return null
    }
    return "$paramName == \"$paramValue\""
}