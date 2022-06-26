package com.example.clean_quiz.ui.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.res.ResourcesCompat.getColor
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView

import com.example.clean_quiz.R
import com.example.clean_quiz.data.models.AnswerCheckStatus
import com.example.clean_quiz.ui.viewmodel.CardUiState
import java.util.ArrayList

// should be immutable?
class QuizViewAdapter(
    private val storeInput: (Int) -> Unit
) : RecyclerView.Adapter<QuizViewAdapter.ViewHolder>() {

    private var answerList = listOf<String>()
    //private lateinit var cardUiStateList: List<CardUiState>
    private var cardUiStateList = listOf<CardUiState>()

    fun nextAnswerSet(answer: List<String>, cardUI:List<CardUiState> ){
        answerList = answer
        cardUiStateList = cardUI
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.quiz_fragment_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardUiState = cardUiStateList[position]
        holder.cardText.text = answerList[position]

      //  holder.cardAnswer.visibility = View.VISIBLE

        holder.initalizeCardBG(cardUiState)
        holder.initalizeCardImage(cardUiState)

        holder.cardAnswer.setOnClickListener {
            storeInput(position)

            if (!cardUiState.answerSelected) {
                cardUiState.answerSelected = true
                holder.cardAnswer.setBackgroundResource(R.color.light_blue_600)
            } else {
                holder.cardAnswer.setBackgroundColor(Color.WHITE)
            }
           // notifyItemChanged(position)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // negative one
      //  val itemPosition = absoluteAdapterPosition
        val cardAnswer: CardView = itemView.findViewById(R.id.item_card)
        val cardText: TextView = itemView.findViewById(R.id.item_answer)
        val cardCheck: ImageView = itemView.findViewById(R.id.item_check)



        fun initalizeCardImage( cardUiState:CardUiState) {
            if (!cardUiState.answerUpdated) {
                cardCheck.setImageResource(0)
                cardCheck.visibility = View.INVISIBLE
            } else {
                updateCardImage(cardUiState)
                cardCheck.visibility = View.VISIBLE
            }
        }

        fun updateCardImage(cardUiState:CardUiState) {
            when (cardUiState.answerStatus!!) {
                AnswerCheckStatus.CORRECT -> cardCheck.setImageResource(R.drawable.correct_answer)
                AnswerCheckStatus.WRONG -> cardCheck.setImageResource(R.drawable.wrong_answer)
                AnswerCheckStatus.NOTSELECTED -> cardCheck.setImageResource(R.drawable.not_selected)
            }
        }

        fun initalizeCardBG(cardUiState:CardUiState) {
                if (!cardUiState.answerUpdated) {
                    cardAnswer.setBackgroundColor(Color.WHITE)
                } else {
                    updateCardBG(cardUiState)
                }
        }


        fun updateCardBG(cardUiState:CardUiState) {
            when (cardUiState.answerStatus!!) {
                AnswerCheckStatus.CORRECT -> cardAnswer.setBackgroundResource(R.color.green)
                AnswerCheckStatus.WRONG -> cardAnswer.setBackgroundResource(R.color.red)
                AnswerCheckStatus.NOTSELECTED -> cardAnswer.setBackgroundResource(R.color.yellow)
            }
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

