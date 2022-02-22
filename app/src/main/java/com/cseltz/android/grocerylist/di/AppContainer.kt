package com.cseltz.android.grocerylist.di

import android.content.Context

class AppContainer(context: Context) {

    private val repository = Repository(context)
    val listViewModelFactory = ListViewModelFactory(repository.database.itemDao())
}