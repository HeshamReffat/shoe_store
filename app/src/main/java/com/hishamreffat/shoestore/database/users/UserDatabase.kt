package com.hishamreffat.shoestore.database.users

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDatabaseDao

    companion object {
        @Volatile
        private var dbInstance: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase {
            synchronized(this) {
                var inst = dbInstance
                if (inst == null) {
                    inst = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
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