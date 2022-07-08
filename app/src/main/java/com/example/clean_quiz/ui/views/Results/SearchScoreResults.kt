package com.example.clean_quiz.ui.views.Results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clean_quiz.data.models.FullRecord
import com.example.clean_quiz.databinding.FragmentSearchScoreResultsBinding
import com.example.clean_quiz.ui.adapter.ScoreViewAdapter
import com.example.clean_quiz.ui.viewmodel.ResultsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchScoreResults : Fragment() {
    private lateinit var binding: FragmentSearchScoreResultsBinding
    private val resultsViewModel: ResultsViewModel by viewModels<ResultsViewModel>(
        ownerProducer = { requireParentFragment() }
    )
    private lateinit var scoreAdapter: ScoreViewAdapter

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

        scoreAdapter = ScoreViewAdapter(arrayOf<FullRecord>())

        binding.searchResultsRecycler.apply {
            layoutManager = LinearLayoutManager(
                activity?.applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = scoreAdapter
        }

        val bottomSheetFragment = SearchUserModal()

        resultsViewModel.tabNumber.observe(viewLifecycleOwner, Observer { tabNum ->
            if (tabNum == 2) bottomSheetFragment.show(childFragmentManager, SearchUserModal.TAG)
        })

        binding.refreshSearch.setOnClickListener {
            bottomSheetFragment.show(childFragmentManager, SearchUserModal.TAG)
        }
        resultsViewModel.searchResultsList.observe(viewLifecycleOwner) {
            scoreAdapter.loadNewData(resultsViewModel.searchResultsList.value!!, null, null)
            bottomSheetFragment.dismiss()
        }
    }
}
