package com.hishamreffat.shoestore.database.users

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val userId: Long = 0L,
    @ColumnInfo("user_logged")
    val userLogged:Boolean= true
)