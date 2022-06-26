package com.example.clean_quiz.ui.views.Results

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.clean_quiz.R
import com.example.clean_quiz.ui.viewmodel.ResultsViewModel
import dagger.hilt.android.AndroidEntryPoint


class SearchScoreHostFragment : Fragment() {
/*    private val resultsViewModel:ResultsViewModel by viewModels(
        ownerProducer = {requireParentFragment()}
    )*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_score_host, container, false)
    }


}