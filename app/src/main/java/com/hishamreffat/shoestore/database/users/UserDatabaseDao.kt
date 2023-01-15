package com.hishamreffat.shoestore.database.users

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDatabaseDao {

    @Insert
    suspend fun insert(user: User)

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM user_table WHERE userId = :key")
    suspend fun get(key: Long): User?

    @Query("DELETE FROM user_table")
    suspend fun clear()

    @Query("SELECT * FROM user_table LIMIT 1")
    suspend fun getUser(): User?

//    @Query("SELECT * FROM shoe_item_table ORDER BY shoeId DESC LIMIT 1")
//    suspend fun getShoe(): ShoeItem?

}