package com.cseltz.android.grocerylist.ui.addedititem

import com.cseltz.android.grocerylist.data.Item

sealed class AddEditEvents {
    object OnAddEditFabClicked: AddEditEvents()
}