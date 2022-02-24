package com.cseltz.android.grocerylist.ui.list.deletealldialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.cseltz.android.grocerylist.R
import com.cseltz.android.grocerylist.di.MainApplication
import com.google.android.material.snackbar.Snackbar

class DeleteAllDialog: DialogFragment() {

    private lateinit var viewModel: DeleteAllDialogViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity?.application as MainApplication).appContainer.viewModelFactory.create(DeleteAllDialogViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?) =
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_delete_all_title)
            .setMessage(R.string.dialog_delete_message)
            .setNegativeButton(R.string.dialog_delete_all_cancel, null)
            .setPositiveButton(R.string.dialog_delete_all_confirm) { _, _ ->
                viewModel.onConfirmDeleteAll()
            }.create()
}