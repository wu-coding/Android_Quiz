package com.example.clean_quiz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

import com.example.clean_quiz.R
import com.example.clean_quiz.data.models.QuizData
import com.google.android.material.internal.ContextUtils.getActivity
import kotlinx.coroutines.NonDisposableHandle.parent
import kotlin.coroutines.coroutineContext

// should be immutable?
class QuizViewAdapter(private val answerList:MutableLiveData<List<String>>,
                      private val getUserChoice: (pos:Int, flip:Boolean) -> Boolean ,
                      private val positionCheck: MutableLiveData<Int>) : RecyclerView.Adapter<QuizViewAdapter.ViewHolder>(){
// we dont need to pass position check we can just call it within viewmodel?
    // or we could simple declare quizData here and have another public function insert Data
        // just send 2 lambda

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quiz_fragment_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // holder.answer.text = answerList.value?.get(position)
        // could have lambda ?
        holder.cardText.text = answerList.value?.get(position)

        answerList.observeForever {
            holder.cardAnswer.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.white))
            holder.cardCheck.visibility = View.INVISIBLE
        }

        holder.cardAnswer.setOnClickListener{
            if (getUserChoice(position,true)){
             holder.cardAnswer.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.light_blue_600))
         }else{
             holder.cardAnswer.setBackgroundColor(ContextCompat.getColor(holder.itemView.context,R.color.white))
         }}

// Doesnt take into account all false answers
        positionCheck.observeForever {
            if (positionCheck.value == position) {
                    holder.cardCheck.setImageResource(R.drawable.correct_answer)
                }else{
                    holder.cardCheck.setImageResource(R.drawable.wrong_answer)
                }
                    holder.cardCheck.visibility = View.VISIBLE
            }

        }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardAnswer: CardView = itemView.findViewById(R.id.item_card)
        val cardText: TextView = itemView.findViewById(R.id.item_answer)
        val cardCheck: ImageView = itemView.findViewById(R.id.item_check)
    }

    override fun getItemCount(): Int {
        // catch exception here?
        return answerList.value!!.size

        /*val testing = quizData.value?.getSize()!!
        try {
            if(quizData.value?.getSize() !== null){
                return quizData.value?.getSize()!!
            }else{
                throw Exception("null in count")
            }}catch(exception: Exception ){
            return 4
        }*/
    }
}

