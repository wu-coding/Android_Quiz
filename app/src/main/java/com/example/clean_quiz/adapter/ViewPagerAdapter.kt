package com.example.clean_quiz.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.clean_quiz.views.ResultFragmentTop10
import com.example.clean_quiz.views.ResultFragmentUserScore


private const val NUM_PAGES = 3

class ViewPagerAdapter(val fragment: FragmentManager, lifeCycle: Lifecycle) :
    FragmentStateAdapter(fragment, lifeCycle) {
    private var pastPosition: Int? = null

    override fun getItemCount(): Int {
        return NUM_PAGES
    }

    override fun createFragment(position: Int): Fragment {
        if (position == 0 || position == 1) {
            return ResultFragmentTop10()
        }/*else if(position == 1){
            return ResultFragmentTop10()
        }*/
        return ResultFragmentUserScore()
    }
}



