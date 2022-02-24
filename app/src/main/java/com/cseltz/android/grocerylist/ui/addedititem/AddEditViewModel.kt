package com.cseltz.android.grocerylist.ui.addedititem

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cseltz.android.grocerylist.data.Item
import com.cseltz.android.grocerylist.di.Repository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditViewModel(private val repository: Repository): ViewModel() {

    var name: String? = null
    var quantity: String? = null
    var currentItem: Item? = null

    private val _itemEvent = Channel<AddEditUiEvents>()
    val itemEvent = _itemEvent.receiveAsFlow()

    fun performEvent(event: AddEditEvents) {
        when (event) {
            is AddEditEvents.OnAddEditFabClicked -> {
                if (name.isNullOrBlank()) {
                    viewModelScope.launch {
                        _itemEvent.send(AddEditUiEvents.ShowSnackbar("Name cannot be empty!"))
                    }
                } else {
                    if (currentItem == null) {
                        viewModelScope.launch {
                            val newItem = Item(
                                name = name.toString(),
                                quantity = quantity?.toInt() ?: 1
                            )
                            repository.insertItem(newItem)
                            _itemEvent.send(AddEditUiEvents.PopBackStack)
                        }
                    } else if (currentItem != null) {
                        viewModelScope.launch {
                            val updatedItem = currentItem?.copy(
                                name = name.toString(),
                                quantity = quantity?.toInt() ?: 1
                            )
                            repository.updateItem(updatedItem!!)
                            _itemEvent.send(AddEditUiEvents.PopBackStack)
                        }
                    }
                }
            }
        }
    }

    fun initializeEdit(item: Item) {
        name = item.name
        quantity = item.quantity.toString()
        currentItem = item
    }
}
