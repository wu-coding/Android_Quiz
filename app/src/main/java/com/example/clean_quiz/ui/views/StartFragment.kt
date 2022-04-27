package com.example.clean_quiz.ui.views

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.clean_quiz.R
import com.example.clean_quiz.databinding.FragmentStartBinding
import com.example.clean_quiz.ui.viewmodel.StartViewModel



class StartFragment : Fragment() {
    // TODO: Rename and change types of parameters
   // val startViewModel: StartViewModel by activityViewModels()

    val startViewModel =  StartViewModel()
    lateinit var binding: FragmentStartBinding

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

        val items =  resources.getStringArray(R.array.category_array)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)


        // should use setError() https://stackoverflow.com/questions/64141542/data-binding-and-input-field-validation-and-manipulation-activity-fragment-nav
        binding.startButton.setOnClickListener() {
        if (startViewModel.errorOutput.length > 0){
            Toast.makeText(requireContext(), startViewModel.errorOutput, Toast.LENGTH_SHORT).show()
        }else{
         //   findNavController().navigate(StartFragmentDirections.nextQuiz(sendUserParams))
        }
       /*     if (startViewModel.validateResponse()){
            //    val sendUserParams = startViewModel.convertUserChoice()

            }*/
            startViewModel.test()
            val sendUserParams = hashMapOf( "category" to "Linux",
                "difficulty" to "easy",
                "limit" to 10,
                "apiKey" to "hcUZqLCh8uTaXt121DQd5IQ7wv5GFIVA5YlaPxy4")

            findNavController().navigate(StartFragmentDirections.nextQuiz(sendUserParams))
        }

    }
}
