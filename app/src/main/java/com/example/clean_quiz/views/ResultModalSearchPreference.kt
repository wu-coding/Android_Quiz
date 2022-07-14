package com.example.clean_quiz.views

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.clean_quiz.R
import com.example.clean_quiz.databinding.FragmentSearchUserModalBinding
import com.example.clean_quiz.viewmodel.ResultsViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultModalSearchPreference : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentSearchUserModalBinding

    private val resultsViewModel: ResultsViewModel by viewModels(
        ownerProducer = { requireParentFragment().requireParentFragment() }
    )

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialog?.setOnShowListener { dialog ->
            val d = dialog as BottomSheetDialog
            val bottomSheet =
                d.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet) as FrameLayout
            val bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        }
        return super.onCreateDialog(savedInstanceState) as BottomSheetDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_user_modal, container, false)
        binding.viewModel = resultsViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    companion object {
        const val TAG = "BottomSheetFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryItems = resources.getStringArray(R.array.category_array)
        val arrayAdapterCategory = ArrayAdapter(requireContext(), R.layout.list_item, categoryItems)
        binding.autoCompleteTextViewCategory.setAdapter(arrayAdapterCategory)

        val difficultyItems = resources.getStringArray(R.array.difficulty_array)
        val arrayAdapterDifficulty =
            ArrayAdapter(requireContext(), R.layout.list_item, difficultyItems)
        binding.autoCompleteTextViewDifficulty.setAdapter(arrayAdapterDifficulty)

        binding.submitSearchPref.setOnClickListener {
            lifecycleScope.launch(Dispatchers.IO) {
                resultsViewModel.loadSearchData()
            }
        }
    }
}