package com.example.clean_quiz.utils

import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import com.google.android.material.textfield.TextInputEditText
import java.util.*


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


fun String.addQuotes(string: String?):String{
    return this.plus("\"" + string + "\"")
}

/*fun Queue<String>.addQueryParam(queryString:String, queryParam:String?){
    if (queryParam != ""){
        this.add(queryString)
    }
}*/

fun addQueryParam(paramName:String, paramValue:String?):String?{
    if (paramValue == null){
        return null
    }
    return "$paramName == \"$paramValue\""
}