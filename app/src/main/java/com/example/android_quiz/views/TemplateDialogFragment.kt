package com.example.android_quiz.views

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle

import androidx.activity.result.ActivityResultCaller
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import androidx.savedstate.SavedStateRegistryOwner

import com.example.android_quiz.models.DialogType


class TemplateDialogFragment(private val dialogType: DialogType) : DialogFragment(), LifecycleOwner,
    ViewModelStoreOwner,
    HasDefaultViewModelProviderFactory, SavedStateRegistryOwner, ActivityResultCaller {


    /*   override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
           return inflater.inflate(R.layout.dialog_about, container, false)
       }*/

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            return AlertDialog.Builder(requireContext())
                .setTitle(dialogType.title)
                .setMessage(dialogType.msg)
                .create()
        }
    }