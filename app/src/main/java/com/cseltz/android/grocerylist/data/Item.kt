package com.cseltz.android.grocerylist.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "items_table")
data class Item(
    val name: String,
    val quantity: Int = 1,
    val found: Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Long = 0
) : Parcelable {

}