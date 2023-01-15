package com.hishamreffat.shoestore.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ShoeItemDatabaseDao {

    @Insert
    suspend fun insert(shoe: ShoeItem)

    @Update
    suspend fun update(shoe: ShoeItem)

    @Query("SELECT * FROM shoe_item_table WHERE shoeId = :key")
    suspend fun get(key: Long): ShoeItem?

    @Query("DELETE FROM shoe_item_table")
    suspend fun clear()

    @Query("SELECT * FROM shoe_item_table ORDER BY shoeId DESC")
    fun getShoes(): LiveData<List<ShoeItem?>>

    @Query("SELECT * FROM shoe_item_table ORDER BY shoeId DESC LIMIT 1")
    suspend fun getShoe(): ShoeItem?

}