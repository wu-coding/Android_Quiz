package com.example.clean_quiz.ui.views

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.clean_quiz.R
import com.example.clean_quiz.databinding.ActivityMainBinding
import com.example.clean_quiz.ui.viewmodel.StartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val startViewModel: StartViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    //    val navHostId = supportFragmentManager.findFragmentById(R.id.nav_graph)

    }

   /* override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)

        val helpOptionId = menu?.findItem(R.id.helpOption)

        helpOptionId?.setOnMenuItemClickListener {
            HelpDialogFragment().show(supportFragmentManager, "HelpAlertDialog")
            true
        }
        return super.onCreateOptionsMenu(menu)
    }*/

}


//  val actionBar = supportActionBar
/*
  private val productClickCallback = ProductClickCallback { product ->
            (requireActivity() as MainActivity).navigateToProductDetail(product.id)
   }


     fun nextPage()
    {
        findNavController()
    }
 */
