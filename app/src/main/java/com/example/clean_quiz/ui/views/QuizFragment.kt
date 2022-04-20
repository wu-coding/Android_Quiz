package com.example.clean_quiz.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.clean_quiz.R

import com.example.clean_quiz.databinding.QuizFragmentBinding
import com.example.clean_quiz.ui.adapter.QuizViewAdapter
import com.example.clean_quiz.ui.viewmodel.QuizViewModel
import com.example.clean_quiz.ui.viewmodel.QuizViewModelFactory
import com.example.clean_quiz.ui.viewmodel.StartViewModel
import kotlinx.coroutines.*
import java.util.HashMap
import kotlin.math.max

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
        binding = DataBindingUtil.inflate(inflater, R.layout.quiz_fragment, container, false)
        //    QuizFragmentBinding.inflate(inflater)
        binding.viewModel = quizViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            viewLifecycleOwner.lifecycleScope.launch {
                withContext(Dispatchers.IO) { quizViewModel.getApiData() }
                quizViewModel.loadData()

                     binding.quizRecyclerView.apply {
                    layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
                    adapter = QuizViewAdapter (quizViewModel.currentAnswerSet,quizViewModel.backgroundColors ,quizViewModel.imageType, quizViewModel.getUserInput)
                }
                binding.quizProgress.max = quizViewModel.quizDataList.size
                binding.timeTaken.start()
                quizViewModel.backgroundColors.observe(viewLifecycleOwner){
                    binding.quizRecyclerView.adapter?.notifyItemChanged(quizViewModel.updatePos)
                }
            }

           // binding.questionText.text = quizViewModel.currentQuestion.value


        binding.answerSubmit.setOnCheckedChangeListener { view, isChecked ->
            val temp = binding.questionText.text
            val temp2 = quizViewModel.currentQuestion
                if (isChecked) {
                    quizViewModel.checkAnswers()
                    binding.quizRecyclerView.adapter?.notifyDataSetChanged()

                } else {
                    quizViewModel.clearData()
                    quizViewModel.loadData()
                    binding.quizRecyclerView.adapter?.notifyDataSetChanged()
                    binding.quizProgress.incrementProgressBy(1)
                }

            }
        }

    }


