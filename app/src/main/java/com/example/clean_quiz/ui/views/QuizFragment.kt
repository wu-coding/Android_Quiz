package com.example.clean_quiz.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.clean_quiz.databinding.QuizFragmentBinding
import com.example.clean_quiz.ui.adapter.QuizViewAdapter
import com.example.clean_quiz.ui.viewmodel.QuizViewModel
import com.example.clean_quiz.ui.viewmodel.QuizViewModelFactory
import com.example.clean_quiz.ui.viewmodel.StartViewModel
import kotlinx.coroutines.*
import java.util.HashMap

class QuizFragment : Fragment() {
// make button xml background change on click

 //   private val startViewModel: StartViewModel by activityViewModels()
    private lateinit var binding:QuizFragmentBinding
    private lateinit var quizViewModel:QuizViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args:QuizFragmentArgs by navArgs()
        quizViewModel = ViewModelProvider(this, QuizViewModelFactory(requireActivity().application,
            args.passUserParam as HashMap<String, String?>
        ))[QuizViewModel::class.java]

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = QuizFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            viewLifecycleOwner.lifecycleScope.launch {
                withContext(Dispatchers.IO) { quizViewModel.getApiData() }
                quizViewModel.loadData()

                     binding.quizRecyclerView.apply {
                    layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
                    adapter = QuizViewAdapter (quizViewModel.currentAnswerSet) { answerCheck: Int ->
                        quizViewModel.checkAnswer(
                            answerCheck
                        )
                    }
                }
            }

        binding.answerSubmit.setOnClickListener(quizViewModel.checkAnswer())



        }
    }



/*        displayQuestion = binding.questionText


        viewModel.apply {
            loadAnswers()
            loadQuestion(displayQuestion)
        }
        recyclerView = view?.findViewById(R.id.quiz_recyclerView) ?: throw Exception("recyclerview findview error")
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
            adapter = QuizViewAdapter (viewModel.currentQuestion) { answerCheck: Int ->
                viewModel.checkAnswer(
                    answerCheck
                )
            }
        }

        viewModel.currentQuestion.observe(viewLifecycleOwner) {
            recyclerView.adapter?.notifyDataSetChanged()
            viewModel.loadQuestion(displayQuestion)
            // Navigation


    }
    }*/

