package com.example.clean_quiz.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.clean_quiz.R

import com.example.clean_quiz.databinding.QuizFragmentBinding
import com.example.clean_quiz.ui.adapter.QuizViewAdapter
import com.example.clean_quiz.ui.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class QuizFragment : Fragment() {
// make button xml background change on click

    //   private val startViewModel: StartViewModel by activityViewModels()
    private lateinit var binding: QuizFragmentBinding
    private val quizViewModel by viewModels<QuizViewModel>()
    val args: QuizFragmentArgs by navArgs()
    private lateinit var quizAdapter: QuizViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val apiParam:Long = args.passUserParam
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_fragment, container, false)
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


        val loadApi = viewLifecycleOwner.lifecycleScope.async(Dispatchers.IO) {
            quizViewModel.loadApiData(args.passUserParam)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            loadApi.await()
            quizAdapter = QuizViewAdapter(quizViewModel.storeUserInput)

            binding.quizRecyclerView.apply {
                layoutManager = LinearLayoutManager(
                    activity?.applicationContext,
                    LinearLayoutManager.VERTICAL,
                    false
                )
                adapter = quizAdapter
            }
            binding.linearProgressIndicator.max = quizViewModel.questionAmount.value!!
            binding.timeTaken.start()
            nextPage()
        }


        //  binding.timeTaken.start()
        quizViewModel.progress.observe(viewLifecycleOwner, Observer {
            binding.submitAnswers.isEnabled = true
            binding.submitAnswers.visibility = View.VISIBLE

            binding.nextQuestion.isEnabled = false
            binding.nextQuestion.visibility = View.INVISIBLE
        })


        // binding.questionText.text = quizViewModel.currentQuestion.value
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
                quizViewModel.currentScore.updateTime(binding.timeTaken.text)
                findNavController().navigate(QuizFragmentDirections.actionQuizFragmentToScoreFragment())
            }
        }
    }
}
