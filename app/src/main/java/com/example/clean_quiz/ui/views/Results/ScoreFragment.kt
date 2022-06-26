package com.example.clean_quiz.ui.views.Results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clean_quiz.databinding.FragmentScoreBinding
import com.example.clean_quiz.ui.adapter.ScoreViewAdapter
import com.example.clean_quiz.ui.viewmodel.ResultsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ScoreFragment : Fragment() {
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
// not acivating 1
            resultsViewModel.tabNumber.observe(viewLifecycleOwner, Observer { tabNum ->
                when (tabNum) {
                    0 -> adapter.loadNewData(resultsViewModel.top10RecordList, null, null)
                    1 -> {
                        adapter.loadNewData(
                            resultsViewModel.userRecordList,
                            resultsViewModel.getUserId(),
                            resultsViewModel.getRecordId()
                        )
                   //     binding.scoreList.scrollToPosition(resultsViewModel.getRecordId())
                    }
                }
            })
        }
    }
}
