package com.example.clean_quiz

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.clean_quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val viewModel: QuizViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.topToolbar)

        val navHostId = supportFragmentManager.findFragmentById(R.id.nav_graph)
      //  val actionBar = supportActionBar
/*

  private val productClickCallback = ProductClickCallback { product ->
            (requireActivity() as MainActivity).navigateToProductDetail(product.id)
    }
 */


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       //binding only on layouts
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)

        val helpOptionId = menu?.findItem(R.id.helpOption)

        helpOptionId?.setOnMenuItemClickListener {
         //   val dialogHelp = HelpDialogFragment()
       //     dialogHelp.show(supportFragmentManager, "HelpAlertDialog")
            HelpDialogFragment().show(supportFragmentManager, "HelpAlertDialog")
            true
        }
        return super.onCreateOptionsMenu(menu)
    }

    fun nextPage()
    {
        findNavController()
    }
}

