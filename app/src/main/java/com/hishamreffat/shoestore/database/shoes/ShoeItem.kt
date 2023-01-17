package com.hishamreffat.shoestore.database.shoes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("shoe_item_table")
data class ShoeItem(
    @PrimaryKey(autoGenerate = true)
    val shoeId: Long = 0L,
    @ColumnInfo("shoe_name")
    var shoeName: String = "Shoe",
    @ColumnInfo("shoe_company")
    var shoeCompany: String = "Nike",
    @ColumnInfo("shoe_size")
    var shoeSize: Int = 30,
    @ColumnInfo("shoe_description")
    var shoeDescription: String = "A new designed shoe with great material for football players ",
)