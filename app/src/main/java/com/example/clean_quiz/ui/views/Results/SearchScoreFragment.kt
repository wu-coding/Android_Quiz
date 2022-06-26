package com.example.clean_quiz.ui.views.Results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.*
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.clean_quiz.R
import com.example.clean_quiz.databinding.FragmentSearchScoreBinding
import com.example.clean_quiz.ui.viewmodel.ResultsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchScoreFragment: Fragment(){

    private lateinit var binding: FragmentSearchScoreBinding
    private val resultsViewModel:ResultsViewModel by viewModels<ResultsViewModel>(
        ownerProducer = {requireParentFragment().requireParentFragment()}
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchScoreBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryItems =  resources.getStringArray(R.array.category_array)
        val arrayAdapterCategory = ArrayAdapter(requireContext(), R.layout.list_item, categoryItems)
        binding.autoCompleteTextViewCategory.setAdapter(arrayAdapterCategory)

        val difficultyItems =  resources.getStringArray(R.array.difficulty_array)
        val arrayAdapterDifficulty = ArrayAdapter(requireContext(), R.layout.list_item, difficultyItems)
        binding.autoCompleteTextViewCategory.setAdapter(arrayAdapterDifficulty)


        binding.submitSearchPref.setOnClickListener {
                parentFragmentManager.commit {
                    setReorderingAllowed(true)
                    replace(R.id.search_fragment_host, SearchScoreResults())
                }

        }
    }
}