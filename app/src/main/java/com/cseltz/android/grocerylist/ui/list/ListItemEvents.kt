package com.cseltz.android.grocerylist.ui.list

import com.cseltz.android.grocerylist.data.Item

// Used to send events from the Ui to the ViewModel
sealed class ListItemEvents {
    data class OnLongClick(val item: Item): ListItemEvents()
    data class OnClick(val item: Item): ListItemEvents()
    data class OnChecked(val item: Item, val isChecked: Boolean): ListItemEvents()
    object OnAddFabClicked: ListItemEvents()
    object OnDeleteAllClicked: ListItemEvents()
    object OnAboutClicked: ListItemEvents()
}
