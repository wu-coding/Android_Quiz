package com.example.clean_quiz

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.clean_quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //for main
        setContentView(binding.root)
        setSupportActionBar(binding.topToolbar)

        val navHostId = supportFragmentManager.findFragmentById(R.id.nav_graph)
      //  val actionBar = supportActionBar

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


}

