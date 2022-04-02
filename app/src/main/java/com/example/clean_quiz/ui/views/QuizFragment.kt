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
import androidx.recyclerview.widget.RecyclerView
import com.example.clean_quiz.QuizData
import com.example.clean_quiz.data.repository.QuizDataRepository
import com.example.clean_quiz.databinding.QuizFragmentBinding
import com.example.clean_quiz.ui.viewmodel.QuizViewModel
import com.example.clean_quiz.ui.viewmodel.QuizViewModelFactory
import com.example.clean_quiz.ui.viewmodel.StartViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.HashMap

class QuizFragment : Fragment() {
// make button xml background change on click

    private val startViewModel: StartViewModel by activityViewModels()
    private lateinit var recyclerView:RecyclerView
    private lateinit var binding:QuizFragmentBinding

    lateinit var sudo: Deferred<List<QuizData>>



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args: StartFragmentDirections.NextQuiz by navArgs()
        val quizViewModel = ViewModelProvider(this, QuizViewModelFactory(this,
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

       lifecycleScope.launch(Dispatchers.IO) {
           // test = async(Dispatchers.IO) {QuizDataRepository.apiService.getQuizData(sendParams as Map<String, String>) }
         sudo = async { QuizDataRepository.apiService.getQuizData(sendParams as Map<String, String>)}
           val test = sudo.await()
           val something = test.get(1)

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
 }

}

