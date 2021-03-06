package com.cseltz.android.grocerylist.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: Item)

    @Insert
    suspend fun insertItemList(items: List<Item>)

    @Delete
    suspend fun deleteItem(item: Item)

    @Update
    suspend fun updateItem(item: Item)

    @Query("SELECT * FROM items_table")
    fun getAllItems(): Flow<List<Item>>

    @Query("DELETE FROM items_table")
    suspend fun deleteAllItems()
}