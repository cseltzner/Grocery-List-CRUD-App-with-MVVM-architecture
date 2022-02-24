package com.cseltz.android.grocerylist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.cseltz.android.grocerylist.data.ItemDao
import com.cseltz.android.grocerylist.ui.list.ListViewModel

class ViewModelFactory(private val repository: Repository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Repository::class.java)
            .newInstance(repository)
    }
}