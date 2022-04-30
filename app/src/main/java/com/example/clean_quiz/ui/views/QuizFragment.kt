package com.example.clean_quiz.ui.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.clean_quiz.R

import com.example.clean_quiz.databinding.QuizFragmentBinding
import com.example.clean_quiz.ui.viewmodel.QuizViewModel

class QuizFragment : Fragment() {
// make button xml background change on click

 //   private val startViewModel: StartViewModel by activityViewModels()
    private lateinit var binding:QuizFragmentBinding
    private lateinit var quizViewModel:QuizViewModel

    val args:QuizFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temp:Long = args.passUserParam
    /*    val args:QuizFragmentArgs by navArgs()
        quizViewModel = ViewModelProvider(this, QuizViewModelFactory(requireActivity().application,
            args.passUserParam as HashMap<String, String?>
        ))[QuizViewModel::class.java]*/

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
/*
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

            }*/
        }

    }


