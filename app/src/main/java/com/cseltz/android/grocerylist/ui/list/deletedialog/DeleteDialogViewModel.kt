package com.cseltz.android.grocerylist.ui.list.deletedialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cseltz.android.grocerylist.data.Item
import com.cseltz.android.grocerylist.di.Repository
import kotlinx.coroutines.launch

class DeleteDialogViewModel(private val repository: Repository): ViewModel() {


    fun onConfirmDelete(item: Item) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }
}