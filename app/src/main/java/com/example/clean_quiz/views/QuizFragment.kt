package com.example.clean_quiz.views

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clean_quiz.R
import com.example.clean_quiz.models.ApiStatus
import com.example.clean_quiz.models.QuestionAnswerSet
import com.example.clean_quiz.databinding.FragmentQuizBinding
import com.example.clean_quiz.adapter.QuizViewAdapter
import com.example.clean_quiz.viewmodel.QuizViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.logging.Level.INFO

@AndroidEntryPoint
class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private val quizViewModel by viewModels<QuizViewModel>()
    private lateinit var quizAdapter: QuizViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.back_to_start)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
        binding.viewModel = quizViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fun nextPage() {
            quizViewModel.loadNextQuestionSet()
            quizAdapter.nextAnswerSet(
                quizViewModel.currentAnswerList,
                quizViewModel.cardUiStateList
            )
        }

        fun DisplayAlert(_errorMsg: String) {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Error")
                .setMessage(_errorMsg)
                .setPositiveButton("Accept") { dialog, which ->
                    findNavController().navigate(R.id.back_to_start)
                }
                .show()
        }

        quizViewModel.loadApiData()

        quizAdapter = QuizViewAdapter(quizViewModel.storeUserInput)

        binding.quizRecyclerView.apply {
            layoutManager = LinearLayoutManager(
                activity?.applicationContext,
                LinearLayoutManager.VERTICAL,
                false
            )
            adapter = quizAdapter
        }

        quizViewModel.apiResponse.observe(viewLifecycleOwner) { result ->
            when (result.status) {
                ApiStatus.SUCCESS -> {
                    quizViewModel.apiData = result.data as MutableList<QuestionAnswerSet>
                    quizViewModel.questionAmount.setValue(quizViewModel.apiData.size)
                    binding.loadingCircle.visibility = View.INVISIBLE
                    binding.submitAnswers.isEnabled = true


                    nextPage()
                    binding.linearProgressIndicator.max = quizViewModel.questionAmount.value!!
                    binding.timeTaken.start()
                }
                ApiStatus.ERROR -> {
                    DisplayAlert(result.message!!)
                }
                ApiStatus.LOADING -> {
                    Log.i("QuizFragment", "Loading")
                }
            }
        }


        quizViewModel.progress.observe(viewLifecycleOwner, Observer {
            binding.submitAnswers.isEnabled = true
            binding.submitAnswers.visibility = View.VISIBLE

            binding.nextQuestion.isEnabled = false
            binding.nextQuestion.visibility = View.INVISIBLE
        })


        binding.submitAnswers.setOnClickListener {
            quizViewModel.submitAnswers()
            quizAdapter.notifyDataSetChanged()
            binding.submitAnswers.isEnabled = false
            binding.submitAnswers.visibility = View.INVISIBLE

            binding.nextQuestion.isEnabled = true
            binding.nextQuestion.visibility = View.VISIBLE

        }
        binding.nextQuestion.setOnClickListener {
            if (quizViewModel.questionAmount.value!! > 0) {
                quizViewModel.clearQuestionSet()
                nextPage()
            } else {
                binding.timeTaken.stop()
                quizViewModel.currentScore.updateTime(binding.timeTaken.text.toString())
                viewLifecycleOwner.lifecycleScope.launch(Dispatchers.IO) {
                    quizViewModel.writeToDatabase()
                }
                findNavController().navigate(R.id.next_page_results)
            }
        }
    }

}
