package com.cseltz.android.grocerylist.di

import android.content.Context
import androidx.room.Room
import com.cseltz.android.grocerylist.data.ItemDatabase

/* Class that is responsible for injecting dependencies
*
* Fragments and Activities can extract public variables from this class
* by calling (application as MainApplication).appContainer.*required variable*

 */
class AppContainer(context: Context) {

    private val database = Room.databaseBuilder(context, ItemDatabase::class.java, "item_db").build()
    val repository = Repository(database.itemDao())
    val viewModelFactory = ViewModelFactory(repository)
}