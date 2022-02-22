package com.cseltz.android.grocerylist.ui.list

import android.util.Log
import androidx.lifecycle.ViewModel
import com.cseltz.android.grocerylist.data.ItemDao
import kotlinx.coroutines.flow.*

class ListViewModel(private val dao: ItemDao): ViewModel() {

    fun log() {
        Log.d("ListViewModel", "dao $dao")
    }


}