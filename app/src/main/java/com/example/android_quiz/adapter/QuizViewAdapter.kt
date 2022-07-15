package com.example.android_quiz.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

import com.example.android_quiz.R
import com.example.android_quiz.models.AnswerCheckStatus
import com.example.android_quiz.viewmodel.CardUiState

class QuizViewAdapter(
    private val storeInput: (Int) -> Unit
) : RecyclerView.Adapter<QuizViewAdapter.ViewHolder>() {

    private var answerList = listOf<String>()
    private var cardUiStateList = listOf<CardUiState>()

    fun nextAnswerSet(answer: List<String>, cardUI:List<CardUiState> ){
        answerList = answer
        cardUiStateList = cardUI
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.fragment_quiz_item, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardUiState = cardUiStateList[position]
        holder.cardText.text = answerList[position]

        holder.initalizeCardBG(cardUiState)
        holder.initalizeCardImage(cardUiState)

        holder.cardAnswer.setOnClickListener {
            storeInput(position)

            if (!cardUiState.answerSelected) {
                cardUiState.answerSelected = true
                holder.cardAnswer.setBackgroundColor(Color.BLUE)
            } else {
                cardUiState.answerSelected = false
                holder.cardAnswer.setBackgroundColor(Color.WHITE)
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
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
                AnswerCheckStatus.CORRECT -> cardAnswer.setBackgroundColor(Color.GREEN)
                AnswerCheckStatus.WRONG -> cardAnswer.setBackgroundColor(Color.RED)
                AnswerCheckStatus.NOTSELECTED -> cardAnswer.setBackgroundColor(Color.YELLOW)

            }
        }

    }

    override fun getItemCount(): Int {
        return answerList.size
    }
}

