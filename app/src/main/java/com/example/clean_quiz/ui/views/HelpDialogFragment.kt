package com.example.clean_quiz.ui.views

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
        return AlertDialog.Builder(requireContext())
                .setTitle("Help")
                .setMessage("This is a basic quiz application. Select from multiple questions. Score will be totaled ")
        //        .setView(layoutInflater.inflate(R.layout.help_dialog_layout, activity?.findViewById(R.id.frametest) ,true))
                //Figure out why FrameLayout in Main activity has parent? or use Remove View
                .create()

      //  return super.onCreateDialog(savedInstanceState)
    }
}