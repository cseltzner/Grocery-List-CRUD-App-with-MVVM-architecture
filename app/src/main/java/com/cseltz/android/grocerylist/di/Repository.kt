package com.cseltz.android.grocerylist.di

import com.cseltz.android.grocerylist.data.Item
import com.cseltz.android.grocerylist.data.ItemDao

class Repository(private val itemDao: ItemDao) {


    // Dao functions
    suspend fun insertItem(item: Item) = itemDao.insertItem(item)

    suspend fun insertItemList(items: List<Item>) = itemDao.insertItemList(items)

    suspend fun deleteItem(item: Item) = itemDao.deleteItem(item)

    suspend fun updateItem(item: Item) = itemDao.updateItem(item)

    fun getAllItems() = itemDao.getAllItems()

    suspend fun deleteAllItems() = itemDao.deleteAllItems()
}