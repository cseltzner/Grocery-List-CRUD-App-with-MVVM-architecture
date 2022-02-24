package com.cseltz.android.grocerylist.ui.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.cseltz.android.grocerylist.data.Item
import com.cseltz.android.grocerylist.data.ItemDao
import com.cseltz.android.grocerylist.di.Repository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ListViewModel(private val repository: Repository): ViewModel() {

    private val _itemEvent = Channel<UiEvents>()
    val itemEvent = _itemEvent.receiveAsFlow()

    /*
    * Public variable that can be observed from fragment
    * that will always have the up-to-date list from
    * the database
     */
    val items = repository.getAllItems().asLiveData()


    fun performEvent(event: ListItemEvents) {
        when (event) {
            is ListItemEvents.OnAddFabClicked -> {
                viewModelScope.launch {
                    _itemEvent.send(UiEvents.NavigateToAddScreen)
                }
            }
            is ListItemEvents.OnChecked -> {
                viewModelScope.launch {
                    val item = event.item
                    val updatedItem = item.copy(found = event.isChecked)
                    repository.updateItem(updatedItem)
                }
            }
            is ListItemEvents.OnClick -> {
                viewModelScope.launch {
                    _itemEvent.send(UiEvents.NavigateToEditScreen(event.item))
                }
            }

            is ListItemEvents.OnLongClick -> {
                viewModelScope.launch {
                    _itemEvent.send(UiEvents.ShowDeleteConfirmation(event.item))
                }
            }

            is ListItemEvents.OnDeleteAllClicked -> {
                viewModelScope.launch {
                    _itemEvent.send(UiEvents.ShowDeleteAllConfirmation)
                }
            }

            is ListItemEvents.OnAboutClicked -> {
                viewModelScope.launch {
                    _itemEvent.send(UiEvents.NavigateToAboutScreen)
                }
            }
        }
    }

}