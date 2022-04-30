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
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.clean_quiz.R
import com.example.clean_quiz.databinding.FragmentStartBinding
import com.example.clean_quiz.ui.viewmodel.StartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class StartFragment : Fragment() {
    // TODO: Rename and change types of parameters
   // val startViewModel: StartViewModel by activityViewModels()

    private val startViewModel:StartViewModel by viewModels()
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
            viewLifecycleOwner.lifecycleScope.launch(){
             //   lateinit var writeDb:Deferred<Object>
                var input: Long = 0
                val temp = async (Dispatchers.IO) {
                        input = startViewModel.writeDatabase() as Long
                    }
                temp.await()
                findNavController().navigate(StartFragmentDirections.nextQuiz(input))
                }


            }
        }


        }

}

