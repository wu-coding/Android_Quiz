package com.example.clean_quiz

import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.example.clean_quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //for main
        setContentView(binding.root)
        setSupportActionBar(binding.topToolbar)
      //  val actionBar = supportActionBar

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       //binding only on layouts
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)

        val helpOptionId = menu?.findItem(R.id.helpOption)

        helpOptionId?.setOnMenuItemClickListener {
            val dialogHelp = HelpDialogFragment()
            dialogHelp.show(supportFragmentManager, "HelpAlertDialog")
       //     HelpDialogFragment().show(supportFragmentManager, "HelpAlertDialog")
            true
        }
        return super.onCreateOptionsMenu(menu)
    }


}

