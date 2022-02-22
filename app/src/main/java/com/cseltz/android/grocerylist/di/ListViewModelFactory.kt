package com.cseltz.android.grocerylist.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.cseltz.android.grocerylist.data.ItemDao
import com.cseltz.android.grocerylist.ui.list.ListViewModel

class ListViewModelFactory(private val itemDao: ItemDao): ViewModelProvider.Factory {



    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(ItemDao::class.java)
            .newInstance(itemDao)
    }
}