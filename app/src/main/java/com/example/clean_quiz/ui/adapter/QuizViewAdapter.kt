package com.example.clean_quiz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.clean_quiz.Question_Answers
import com.example.clean_quiz.R


class QuizViewAdapter(private val quizData: MutableLiveData<Question_Answers>, private val OnClickCallback: (position: Int) -> Unit) : RecyclerView.Adapter<QuizViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quiz_fragment_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val answerBox = quizData.value?.answerList?.get(position)
        holder.answer.text = answerBox?.answer
        holder.bind(position)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val answer: TextView = itemView.findViewById<TextView>(R.id.itemAnswer)

        fun bind(position: Int) {
            answer.setOnClickListener {
                OnClickCallback(position)
            }
        }
    }

    override fun getItemCount(): Int {
        val testing = quizData.value?.getSize()!!

        try {
            if(quizData.value?.getSize() !== null){
                return quizData.value?.getSize()!!
            }else{
                throw Exception("null in count")
            }}catch(exception: Exception ){
            return 4
        }
    }
}

