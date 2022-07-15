package com.example.android_quiz.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android_quiz.databinding.FragmentScoreBinding
import com.example.android_quiz.adapter.ScoreViewAdapter
import com.example.android_quiz.viewmodel.ResultsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ResultFragmentTop10 : Fragment() {
    private lateinit var binding: FragmentScoreBinding
    private val resultsViewModel:ResultsViewModel by viewModels(
        ownerProducer = {requireParentFragment()}
    )
    private lateinit var adapter: ScoreViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val loadData = viewLifecycleOwner.lifecycleScope.async(Dispatchers.IO) {
            resultsViewModel.loadScoreData()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            loadData.await()
            adapter = ScoreViewAdapter(resultsViewModel.top10RecordList)
            binding.scoreList.adapter = adapter

            binding.scoreList.apply {
                layoutManager = LinearLayoutManager(
                    activity?.applicationContext,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }

            resultsViewModel.tabNumber.observe(viewLifecycleOwner, Observer { tabNum ->
                when (tabNum) {
                    0 -> adapter.loadNewData(resultsViewModel.top10RecordList, resultsViewModel.getUserId(),
                        resultsViewModel.getRecordId())
                    1 -> {
                        adapter.loadNewData(
                            resultsViewModel.userRecordList,null,null
                        )
                    }
                }
            })
        }
    }
}
