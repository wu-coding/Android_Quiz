package com.example.clean_quiz

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.clean_quiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
// val actionBar = supportActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //for main
        setContentView(binding.root)
        setSupportActionBar(binding.topToolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
       //binding only on layouts
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)

        val helpOptionId = menu?.findItem(R.id.helpOption)

        if (helpOptionId != null) {
            helpOptionId.setOnMenuItemClickListener {
               // dont need on clikc method as we are not using the button view
            DialogFragment
                val alert = AlertDialog.Builder(applicationContext)

                        alert.setMessage()

            }
        }
        return super.onCreateOptionsMenu(menu)
    }


}