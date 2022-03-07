package com.example.clean_quiz

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.activity.result.ActivityResultCaller
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner

class HelpDialogFragment : DialogFragment(R.layout.help_dialog_layout), LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, ActivityResultCaller{

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Builder pattern
        AlertDialog.Builder(context)
                .setTitle("Help")
                .setMessage("This is a basic quiz application. Select from multiple questions. Score will be totaled ")
        return super.onCreateDialog(savedInstanceState)
    }
}