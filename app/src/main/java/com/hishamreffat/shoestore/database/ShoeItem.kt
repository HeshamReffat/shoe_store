package com.hishamreffat.shoestore.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("shoe_item_table")
data class ShoeItem(
    @PrimaryKey(autoGenerate = true)
    val shoeId: Long = 0L,
    @ColumnInfo("shoe_name")
    val shoeName:String="shoe1"
)