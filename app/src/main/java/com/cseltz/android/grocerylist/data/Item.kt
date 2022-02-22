package com.cseltz.android.grocerylist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items_table")
data class Item(
    val name: String,
    val quantity: Int = 1,
    val found: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
)