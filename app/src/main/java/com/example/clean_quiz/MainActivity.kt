package com.example.clean_quiz

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.clean_quiz.databinding.ActivityMainBinding
import com.example.clean_quiz.models.DialogType
import com.example.clean_quiz.views.TemplateDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.aboutOption -> {
                    TemplateDialogFragment(DialogType.ABOUT).show(
                        supportFragmentManager,
                        "AboutAlertDialog"
                    )
                    true
                }
                R.id.helpQuizOption -> {
                    TemplateDialogFragment(DialogType.QUIZ).show(
                        supportFragmentManager,
                        "HelpQuizAlertDialog"
                    )
                    true
                }
                R.id.helpRecordsOption -> {
                    TemplateDialogFragment(DialogType.RECORDS).show(
                        supportFragmentManager,
                        "HelpRecordsAlertDialog"
                    )
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}


