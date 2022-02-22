package com.cseltz.android.grocerylist.di

import android.content.Context
import androidx.room.Room
import com.cseltz.android.grocerylist.data.ItemDatabase

// Local and remote databases go here
class Repository(context: Context) {

        val database = Room.databaseBuilder(context, ItemDatabase::class.java, "item_db")
            .build()

}