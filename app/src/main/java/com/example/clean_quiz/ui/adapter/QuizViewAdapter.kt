package com.example.clean_quiz.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

import com.example.clean_quiz.R
import java.util.ArrayList

// should be immutable?
class QuizViewAdapter( val answerList:ArrayList<String>,
                       val backgroundColor:MutableLiveData<Array<Int>>,
                       val imageType:ArrayList<Int?>,
                      private val getUserChoice: (pos:Int) -> Unit
) : RecyclerView.Adapter<QuizViewAdapter.ViewHolder>(){

/*    fun updateData(answerParam: List<String>, backgroundParam: MutableLiveData<Array<Int>>){
        answerList = answerParam
        backgroundColor = backgroundParam,
//
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.quiz_fragment_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.cardText.text = answerList[position]
        holder.setCardBG(position)
        holder.setCardImage(position)

        holder.cardAnswer.setOnClickListener {
            getUserChoice(position)
       // switch stuff?
        // notifyitemchanged?

        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardAnswer: CardView = itemView.findViewById(R.id.item_card)
        val cardText: TextView = itemView.findViewById(R.id.item_answer)
        val cardCheck: ImageView = itemView.findViewById(R.id.item_check)

        fun setCardImage(pos: Int){
            if(imageType[pos] == null){
                cardCheck.setImageResource(0)
                cardCheck.visibility = View.INVISIBLE
            }
            else{
                cardCheck.setImageResource(imageType[pos]!!)
                cardCheck.visibility = View.VISIBLE
            }
        }

        fun setCardBG(pos: Int){

            // color are consistent
            val test = backgroundColor.value?.get(pos)!!
            cardAnswer.setBackgroundResource(backgroundColor.value?.get(pos)!!)
       //     cardAnswer.setCardBackgroundColor(backgroundColor.value?.get(pos)!!)
        }
    }

    override fun getItemCount(): Int {
        // catch exception here?
        return answerList.size

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

