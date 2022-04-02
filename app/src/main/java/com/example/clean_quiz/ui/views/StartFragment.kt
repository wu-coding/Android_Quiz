package com.example.clean_quiz.ui.views

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.clean_quiz.R
import com.example.clean_quiz.databinding.FragmentStartBinding
import com.example.clean_quiz.ui.viewmodel.StartViewModel



class StartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private val startViewModel: StartViewModel by activityViewModels()


    private lateinit var binding: FragmentStartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)
        binding.viewModel = startViewModel
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.category_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.categorySpinner.adapter = adapter
        }


        // should use setError() https://stackoverflow.com/questions/64141542/data-binding-and-input-field-validation-and-manipulation-activity-fragment-nav
        binding.startButton.setOnClickListener() {
            if (startViewModel.validateResponse()){
                val sendUserParams = startViewModel.convertUserChoice()
                findNavController().navigate(StartFragmentDirections.nextQuiz(sendUserParams))
            }
        }

    }
}
