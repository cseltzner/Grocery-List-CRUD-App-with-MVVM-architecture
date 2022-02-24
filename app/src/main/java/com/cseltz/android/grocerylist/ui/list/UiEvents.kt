package com.cseltz.android.grocerylist.ui.list

import com.cseltz.android.grocerylist.data.Item

// Used to send events back to the Ui
sealed class UiEvents {
    data class ShowDeleteConfirmation(val item: Item): UiEvents()
    object ShowDeleteAllConfirmation: UiEvents()
    object NavigateToAddScreen: UiEvents()
    object NavigateToAboutScreen: UiEvents()
    data class NavigateToEditScreen(val item: Item): UiEvents()
}
