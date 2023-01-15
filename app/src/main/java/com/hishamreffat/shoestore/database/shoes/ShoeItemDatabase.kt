package com.hishamreffat.shoestore.database.shoes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ShoeItem::class], version = 1, exportSchema = false)
abstract class ShoeItemDatabase : RoomDatabase() {
    abstract val shoeItemDao: ShoeItemDatabaseDao

    companion object {
        @Volatile
        private var dbInstance: ShoeItemDatabase? = null

        fun getInstance(context: Context): ShoeItemDatabase {
            synchronized(this) {
                var inst = dbInstance
                if (inst == null) {
                    inst = Room.databaseBuilder(
                        context.applicationContext,
                        ShoeItemDatabase::class.java,
                        "shoe_object_item_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    dbInstance = inst
                }
                return inst
            }

        }
    }
}