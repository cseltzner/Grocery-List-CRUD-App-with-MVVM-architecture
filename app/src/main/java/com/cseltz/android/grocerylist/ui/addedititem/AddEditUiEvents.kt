package com.cseltz.android.grocerylist.ui.addedititem

sealed class AddEditUiEvents {
    data class ShowSnackbar(val message: String): AddEditUiEvents()
    object PopBackStack: AddEditUiEvents()
}
