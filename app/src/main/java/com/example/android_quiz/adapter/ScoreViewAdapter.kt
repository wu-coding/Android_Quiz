package com.example.android_quiz.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_quiz.models.FullRecord
import com.example.android_quiz.databinding.ScoreRowBinding


class ScoreViewAdapter(
    var recordList: Array<FullRecord>,
) : RecyclerView.Adapter<ScoreViewAdapter.ViewHolder>() {
    var userIDValue: Int? = null
    var recordIDValue: Int? = null

    fun loadNewData(recordListValue:Array<FullRecord>, userID: Int?, recordID: Int?){
        recordList = recordListValue
        userIDValue = userID
        recordIDValue = recordID
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ScoreRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val scoreRow = recordList[position]
        holder.bind(scoreRow)
    }

    inner class ViewHolder(private val binding: ScoreRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recordValue:FullRecord) {
            binding.userNameInput.text = recordValue.first_name + " " + recordValue.last_name
            binding.categoryInput.text = recordValue.category
            binding.difficultyInput.text = recordValue.difficulty
            binding.questionAmountInput.text = recordValue.question_amount.toString()

            // 0 if null
            val totalScore = recordValue.total_score
            if(totalScore == null){
                binding.scoreInput.text = "0"
            }else{
                binding.scoreInput.text = totalScore.toString()
            }


            if(recordValue.user_id != null && recordValue.user_id == userIDValue){
                binding.root.setBackgroundColor(Color.GREEN)
            }
            if(recordValue.record_id != null && recordValue.record_id == recordIDValue){
                binding.root.setBackgroundColor(Color.BLUE)
            }
        }
    }


    override fun getItemCount(): Int {
        return recordList.size
    }
}