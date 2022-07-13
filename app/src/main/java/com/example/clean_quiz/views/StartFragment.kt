package com.example.clean_quiz.views

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.clean_quiz.R
import com.example.clean_quiz.databinding.FragmentStartBinding
import com.example.clean_quiz.viewmodel.StartViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


@AndroidEntryPoint
class StartFragment : Fragment() {

    private val startViewModel: StartViewModel by viewModels()
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

        val items = resources.getStringArray(R.array.category_array)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item, items)
        binding.autoCompleteTextView.setAdapter(arrayAdapter)

        binding.difficultyRadiogroup.setOnCheckedChangeListener() { group, checkedID ->
            val RadioId = group.findViewById<RadioButton>(checkedID)
            startViewModel.currentPreferences.difficulty = RadioId.text.toString()
        }


        binding.startButton.setOnClickListener() {
            var errorFlag = false
            if (binding.firstName.editText?.text.toString() == "") {
                binding.firstName.error = "Enter First Name"
                errorFlag = true
            }
            if (binding.lastName.editText?.text.toString() == "") {
                binding.lastName.error = "Enter Last Name"
                errorFlag = true
            }
            if (binding.categorySpinner.editText?.text.toString() == "") {
                binding.categorySpinner.error = "Enter Category"
                errorFlag = true
            }
            if (binding.difficultyRadiogroup.checkedRadioButtonId == -1) {
                binding.Difficulty.error = "Enter Difficulty"
                errorFlag = true
            }

            val questionNum = binding.limitText.editText?.text.toString()
            if (questionNum == "null") {
                binding.limitText.error = "Enter Question #"
                errorFlag = true
            } else if (questionNum.toInt() !in 1..10) {
                binding.limitText.error = "Question Amount must be between 1-10"
                errorFlag = true
            }

            if (!errorFlag) {
                viewLifecycleOwner.lifecycleScope.launch() {
                    val writeDB = async(Dispatchers.IO) {
                        startViewModel.writeToDatabase()
                    }
                    writeDB.await()
                    findNavController().navigate(R.id.next_page_quiz)
                }
            }
        }

        //Clear Database Button not implemented
        /*  binding.clearDatabase.setOnClickListener {
              viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                  startViewModel.clearDatabase()
              }
          }*/

    }

}

