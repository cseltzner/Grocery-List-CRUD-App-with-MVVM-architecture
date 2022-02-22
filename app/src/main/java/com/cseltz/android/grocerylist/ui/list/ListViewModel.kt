package com.cseltz.android.grocerylist.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cseltz.android.grocerylist.data.Item
import com.cseltz.android.grocerylist.data.ItemDao
import com.cseltz.android.grocerylist.di.Repository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ListViewModel(private val repository: Repository): ViewModel() {

    /*
    * Public variable that can be observed from fragment
    * that will always have the up-to-date list from
    * the database
     */
    val items = repository.getAllItems().asLiveData()



    // Remove when unneeded
    fun setFakeData() {
        val list = createTempList(100)
        viewModelScope.launch {
            repository.insertItemList(list)
        }
    }

    // Remove when unneeded
    private fun createTempList(size: Int): List<Item> {
        val list = mutableListOf<Item>()
        for (i in 0 until size) {
            list.add(Item(name = "Item $i"))
        }
        return list
    }


}