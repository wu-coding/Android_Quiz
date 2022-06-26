package com.example.clean_quiz.ui.views.Results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.clean_quiz.databinding.FragmentSearchScoreResultsBinding
import com.example.clean_quiz.ui.adapter.ScoreViewAdapter
import com.example.clean_quiz.ui.viewmodel.ResultsViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchScoreResults:Fragment() {
    private lateinit var binding:FragmentSearchScoreResultsBinding
    private val resultsViewModel:ResultsViewModel by viewModels<ResultsViewModel>(
        ownerProducer = {requireParentFragment().requireParentFragment()}
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchScoreResultsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val loadApi = viewLifecycleOwner.lifecycleScope.async(Dispatchers.IO) {
            resultsViewModel.loadSearchData()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            loadApi.await()

            binding.searchResultsRecycler.apply {
                layoutManager = LinearLayoutManager(
                    activity?.applicationContext,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = ScoreViewAdapter(resultsViewModel.searchResultsList)
            }
        }
    }
}