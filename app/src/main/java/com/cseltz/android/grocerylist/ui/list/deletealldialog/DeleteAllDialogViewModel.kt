package com.cseltz.android.grocerylist.ui.list.deletealldialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cseltz.android.grocerylist.di.Repository
import kotlinx.coroutines.launch

class DeleteAllDialogViewModel(private val repository: Repository): ViewModel() {


    fun onConfirmDeleteAll() {
        viewModelScope.launch {
            repository.deleteAllItems()
        }
    }
}