package com.example.clean_quiz.ui.adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import com.example.clean_quiz.R
import com.example.clean_quiz.databinding.FragmentScoreBinding
import com.example.clean_quiz.ui.views.Results.ScoreFragment
import com.example.clean_quiz.ui.views.Results.SearchScoreFragment
import com.example.clean_quiz.ui.views.Results.SearchScoreHostFragment
import com.example.clean_quiz.ui.views.Results.SearchScoreResults


private const val NUM_PAGES = 3

class ViewPagerAdapter(val fragment: FragmentManager, lifeCycle: Lifecycle) :
    FragmentStateAdapter(fragment, lifeCycle) {
    private var pastPosition: Int? = null

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0) {
            return ScoreFragment()
        }else if(position == 1){
            return ScoreFragment()
        }
        return SearchScoreResults()
    }
}



