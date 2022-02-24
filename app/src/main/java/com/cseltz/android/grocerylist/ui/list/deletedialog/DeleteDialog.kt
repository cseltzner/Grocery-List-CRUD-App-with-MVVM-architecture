package com.cseltz.android.grocerylist.ui.list.deletedialog

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.navArgs
import com.cseltz.android.grocerylist.R
import com.cseltz.android.grocerylist.di.MainApplication

class DeleteDialog: DialogFragment() {

    private lateinit var viewModel: DeleteDialogViewModel
    private val args: DeleteDialogArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = (activity?.application as MainApplication).appContainer.viewModelFactory.create(DeleteDialogViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.dialog_delete_confirmation)
            .setMessage(R.string.dialog_delete_message)
            .setNegativeButton(R.string.dialog_cancel, null)
            .setPositiveButton(R.string.dialog_confirm) { _, _ ->
                val item = args.item
                viewModel.onConfirmDelete(item)
            }.create()
}