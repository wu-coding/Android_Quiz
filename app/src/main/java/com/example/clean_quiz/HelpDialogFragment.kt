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

class HelpDialogFragment() : DialogFragment(), LifecycleOwner, ViewModelStoreOwner, HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, ActivityResultCaller{

    /*
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.help_dialog_layout, container, false)
    }
*/
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Builder pattern
   //     val inflater = requireActivity().layoutInflater

        AlertDialog.Builder(requireContext())
                .setTitle("Help")
                .setMessage("This is a basic quiz application. Select from multiple questions. Score will be totaled ")
         //       .setView(layoutInflater.inflate(R.layout.help_dialog_layout, view?.parent,false))
                .create()
        return super.onCreateDialog(savedInstanceState)
    }
}