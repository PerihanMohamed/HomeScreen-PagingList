package com.example.homescreen.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.homescreen.model.User


@Database(
    entities = [User::class , RemoteKeys::class],
    version = 2,
    exportSchema = false
)
 abstract class UserDatabase : RoomDatabase() {

    abstract fun usersDao(): UserDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {

        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context: Context): UserDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                UserDatabase::class.java, "Github.db")
                .fallbackToDestructiveMigration()
                .build()
    }


}